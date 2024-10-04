package com.rh.management.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rh.management.models.Ponto;

public class PontoController {

    private List<Ponto> pontos;
    private final String diretorio = "Data";
    private final String arquivoPontos = diretorio + "/pontos.txt";

    // Construtor
    public PontoController() {
        this.pontos = new ArrayList<>();
        criarDiretorioEArquivo();  // Cria diretório e arquivo se não existirem
        carregarPontosDoArquivo(); // Carrega os pontos salvos no arquivo
    }

    // Cria o diretório "Data" e o arquivo "pontos.txt" se não existirem
    private void criarDiretorioEArquivo() {
        File dir = new File(diretorio);
        if (!dir.exists()) {
            dir.mkdirs(); // Cria o diretório
        }

        File arquivo = new File(arquivoPontos);
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile(); // Cria o arquivo se ele não existir
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Salva os pontos no arquivo
    private void salvarPontosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoPontos))) {
            for (Ponto ponto : pontos) {
                writer.write(pontoToCSV(ponto));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega os pontos a partir do arquivo
    private void carregarPontosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoPontos))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Ponto ponto = csvToPonto(linha);
                pontos.add(ponto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Converte um ponto para uma linha CSV
    private String pontoToCSV(Ponto ponto) {
        return String.join(";",
                String.valueOf(ponto.getId()), String.valueOf(ponto.getFuncionarioId()), 
                ponto.getEntrada().toString(), 
                ponto.getSaida() != null ? ponto.getSaida().toString() : "null");
    }

    // Converte uma linha CSV em um objeto Ponto
    private Ponto csvToPonto(String linha) {
        String[] campos = linha.split(";");
        Ponto ponto = new Ponto(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]));
    
        // Converter a entrada de String para LocalDateTime
        ponto.setEntrada(LocalDateTime.parse(campos[2])); // Convertendo para LocalDateTime
        if (!"null".equals(campos[3])) {
            ponto.setSaida(LocalDateTime.parse(campos[3])); // Convertendo para LocalDateTime
        }
        return ponto;
    }

    // Método para registrar entrada do funcionário
    public void registrarEntrada(int funcionarioId) {
        int novoId = pontos.size() + 1; // Gera um ID incremental para o ponto
        Ponto novoPonto = new Ponto(novoId, funcionarioId);
        pontos.add(novoPonto);
        salvarPontosNoArquivo(); // Salva os pontos no arquivo
        System.out.println("Entrada registrada para o funcionário ID: " + funcionarioId);
    }

    // Método para registrar saída do funcionário
    public void registrarSaida(int funcionarioId) {
        Ponto pontoAtual = buscarPontoAberto(funcionarioId);

        if (pontoAtual != null) {
            pontoAtual.registrarSaida();
            salvarPontosNoArquivo(); // Atualiza o arquivo com a saída
            System.out.println("Saída registrada para o funcionário ID: " + funcionarioId);
        } else {
            System.out.println("Nenhum ponto aberto encontrado para o funcionário ID: " + funcionarioId);
        }
    }

    // Método para buscar o último ponto aberto (sem saída registrada) para um funcionário
    private Ponto buscarPontoAberto(int funcionarioId) {
        for (Ponto ponto : pontos) {
            if (ponto.getFuncionarioId() == funcionarioId && ponto.getSaida() == null) {
                return ponto; // Retorna o último ponto com entrada registrada e sem saída
            }
        }
        return null;
    }

    // Método para listar todos os pontos de um funcionário
    public List<Ponto> listarPontosPorFuncionario(int funcionarioId) {
        List<Ponto> pontosFuncionario = new ArrayList<>();
        for (Ponto ponto : pontos) {
            if (ponto.getFuncionarioId() == funcionarioId) {
                pontosFuncionario.add(ponto);
            }
        }
        return pontosFuncionario;
    }

    // Exibir todos os pontos de um funcionário
    public void exibirPontosPorFuncionario(int funcionarioId) {
        List<Ponto> pontosFuncionario = listarPontosPorFuncionario(funcionarioId);
        if (pontosFuncionario.isEmpty()) {
            System.out.println("Nenhum ponto encontrado para o funcionário ID: " + funcionarioId);
        } else {
            for (Ponto ponto : pontosFuncionario) {
                ponto.exibirDetalhesPonto();
                System.out.println("-----------------------------");
            }
        }
    }

    // Método para calcular horas totais trabalhadas por um funcionário
    public long calcularHorasTotaisTrabalhadas(int funcionarioId) {
        List<Ponto> pontosFuncionario = listarPontosPorFuncionario(funcionarioId);
        long horasTotais = 0;

        for (Ponto ponto : pontosFuncionario) {
            if (ponto.getSaida() != null) {
                horasTotais += ponto.calcularHorasTrabalhadas();
            }
        }

        return horasTotais;
    }

    // Exibir horas totais trabalhadas de um funcionário
    public void exibirHorasTrabalhadas(int funcionarioId) {
        long horasTotais = calcularHorasTotaisTrabalhadas(funcionarioId);
        System.out.println("Horas totais trabalhadas pelo funcionário ID: " + funcionarioId + " = " + horasTotais + " horas");
    }

    public List<Ponto> listarPontos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
