package com.rh.management.Controllers;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rh.management.models.Usuario;

public class UsuarioController {

    private List<Usuario> usuarios;
    private final String diretorio = "Data";
    private final String arquivoUsuarios = diretorio + "/usuarios.txt";

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
        criarDiretorioEArquivo();  // Cria diretório e arquivo se não existirem
        carregarUsuariosDoArquivo(); // Carrega os usuários salvos no arquivo
    }

    // Cria o diretório "Data" e o arquivo "usuarios.txt" se não existirem
    private void criarDiretorioEArquivo() {
        File dir = new File(diretorio);
        if (!dir.exists()) {
            dir.mkdirs(); // Cria o diretório
        }

        File arquivo = new File(arquivoUsuarios);
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile(); // Cria o arquivo se ele não existir
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Salva os usuários no arquivo
    private void salvarUsuariosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoUsuarios))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuarioToCSV(usuario));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega os usuários a partir do arquivo
    private void carregarUsuariosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoUsuarios))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Usuario usuario = csvToUsuario(linha);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Converte um usuário para uma linha CSV
    private String usuarioToCSV(Usuario usuario) {
        return String.join(";",
                String.valueOf(usuario.getId()), usuario.getNome(), usuario.getCpf(), usuario.getEndereco(),
                usuario.getTelefone(), usuario.getCargo(), usuario.getDepartamento(),
                String.valueOf(usuario.getSalario()), usuario.getDataAdmissao(), usuario.getContaBancaria(),
                usuario.getBeneficios(), String.valueOf(usuario.isAtivo()), usuario.getUsername(), usuario.getPassword());
    }

    // Converte uma linha CSV em um objeto Usuario
    private Usuario csvToUsuario(String linha) {
        String[] campos = linha.split(";");
        return new Usuario(
                Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5], campos[6],
                Double.parseDouble(campos[7]), campos[8], campos[9], campos[10], Boolean.parseBoolean(campos[11]),
                campos[12], campos[13]
        );
    }

    // Criar novo usuário
    public void cadastrarUsuario(int id, String nome, String cpf, String endereco, String telefone, String cargo,
                                 String departamento, double salario, String dataAdmissao, String contaBancaria,
                                 String beneficios, String username, String password) {
        Usuario usuario = new Usuario(id, nome, cpf, endereco, telefone, cargo, departamento, salario,
                                      dataAdmissao, contaBancaria, beneficios, true, username, password);
        usuarios.add(usuario);
        salvarUsuariosNoArquivo(); // Salva o usuário no arquivo
        System.out.println("Usuário cadastrado com sucesso: " + nome);
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // Buscar usuário por ID
    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        System.out.println("Usuário não encontrado com o ID: " + id);
        return null;
    }

    // Atualizar informações de um usuário
    public void atualizarUsuario(int id, String endereco, String telefone, String cargo, String departamento,
                                 double salario, String contaBancaria, String beneficios) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.atualizarInformacoes(endereco, telefone, cargo, departamento, salario, contaBancaria, beneficios);
            System.out.println("Usuário atualizado com sucesso: " + usuario.getNome());
        }
    }

    // Desativar usuário
    public void desativarUsuario(int id) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.desativarFuncionario();
            System.out.println("Usuário desativado: " + usuario.getNome());
        }
    }

    // Ativar usuário
    public void ativarUsuario(int id) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.ativarFuncionario();
            System.out.println("Usuário ativado: " + usuario.getNome());
        }
    }

    // Remover usuário do sistema
    public void removerUsuario(int id) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario != null) {
            usuarios.remove(usuario);
            System.out.println("Usuário removido: " + usuario.getNome());
        }
    }

    // Filtrar funcionários por departamento
    public List<Usuario> listarPorDepartamento(String departamento) {
        return usuarios.stream()
                .filter(usuario -> usuario.getDepartamento().equalsIgnoreCase(departamento))
                .collect(Collectors.toList());
    }

    // Filtrar funcionários por cargo
    public List<Usuario> listarPorCargo(String cargo) {
        return usuarios.stream()
                .filter(usuario -> usuario.getCargo().equalsIgnoreCase(cargo))
                .collect(Collectors.toList());
    }

    // Filtrar funcionários por tempo de serviço
    public List<Usuario> listarPorTempoDeServico(int anos) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return usuarios.stream()
                .filter(usuario -> {
                    LocalDate dataAdmissao = LocalDate.parse(usuario.getDataAdmissao(), formatter);
                    return dataAtual.minusYears(anos).isAfter(dataAdmissao);
                })
                .collect(Collectors.toList());
    }
}
