package com.rh.management;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.rh.management.Controllers.FolhaPagamentoController;
import com.rh.management.Controllers.PontoController;
import com.rh.management.Controllers.RelatorioController;
import com.rh.management.Controllers.UsuarioController;
import com.rh.management.models.Usuario;

public class RHManagementSystem extends JFrame {

    private UsuarioController usuarioController;
    private PontoController pontoController;
    private FolhaPagamentoController folhaPagamentoController;
    private RelatorioController relatorioController;

    public RHManagementSystem() {
        usuarioController = new UsuarioController();
        pontoController = new PontoController();
        folhaPagamentoController = new FolhaPagamentoController();
        relatorioController = new RelatorioController();

        // Configuração da janela principal
        setTitle("Sistema de Gestão de RH");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Exibe diretamente o painel de controle
        showControlPanel();
    }

    // Método para exibir o painel de controle
    private void showControlPanel() {
        getContentPane().removeAll();
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2));

        JButton cadastrarUsuarioButton = new JButton("Cadastrar Funcionário");
        JButton registrarPontoButton = new JButton("Registrar Ponto");
        JButton folhaPagamentoButton = new JButton("Gerar Folha de Pagamento");
        JButton listarUsuariosButton = new JButton("Listar Funcionários");
        JButton gerarRelatorioButton = new JButton("Gerar Relatório");
        JButton sairButton = new JButton("Sair");

        controlPanel.add(cadastrarUsuarioButton);
        controlPanel.add(registrarPontoButton);
        controlPanel.add(folhaPagamentoButton);
        controlPanel.add(listarUsuariosButton);
        controlPanel.add(gerarRelatorioButton);
        controlPanel.add(sairButton);

        add(controlPanel);
        revalidate();
        repaint();

        // Ações dos botões
        cadastrarUsuarioButton.addActionListener(e -> showCadastroUsuario());
        registrarPontoButton.addActionListener(e -> showRegistroPonto());
        folhaPagamentoButton.addActionListener(e -> showGeracaoFolhaPagamento());
        listarUsuariosButton.addActionListener(e -> showListarUsuarios());
        gerarRelatorioButton.addActionListener(e -> showGerarRelatorios());
        sairButton.addActionListener(e -> System.exit(0)); // Sair do sistema
    }

    // Tela de cadastro de funcionários
    private void showCadastroUsuario() {
        getContentPane().removeAll();
        JPanel cadastroPanel = new JPanel();
        cadastroPanel.setLayout(new GridLayout(10, 2));

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField enderecoField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField cargoField = new JTextField();
        JTextField departamentoField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField contaBancariaField = new JTextField();
        JTextField beneficiosField = new JTextField();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        cadastroPanel.add(new JLabel("Nome:"));
        cadastroPanel.add(nomeField);
        cadastroPanel.add(new JLabel("CPF:"));
        cadastroPanel.add(cpfField);
        cadastroPanel.add(new JLabel("Endereço:"));
        cadastroPanel.add(enderecoField);
        cadastroPanel.add(new JLabel("Telefone:"));
        cadastroPanel.add(telefoneField);
        cadastroPanel.add(new JLabel("Cargo:"));
        cadastroPanel.add(cargoField);
        cadastroPanel.add(new JLabel("Departamento:"));
        cadastroPanel.add(departamentoField);
        cadastroPanel.add(new JLabel("Salário:"));
        cadastroPanel.add(salarioField);
        cadastroPanel.add(new JLabel("Conta Bancária:"));
        cadastroPanel.add(contaBancariaField);
        cadastroPanel.add(new JLabel("Benefícios:"));
        cadastroPanel.add(beneficiosField);
        cadastroPanel.add(cadastrarButton);
        cadastroPanel.add(voltarButton);

        add(cadastroPanel);
        revalidate();
        repaint();

        // Ação do botão de cadastro
        cadastrarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String endereco = enderecoField.getText();
            String telefone = telefoneField.getText();
            String cargo = cargoField.getText();
            String departamento = departamentoField.getText();
            double salario = Double.parseDouble(salarioField.getText());
            String contaBancaria = contaBancariaField.getText();
            String beneficios = beneficiosField.getText();

            usuarioController.cadastrarUsuario(
                usuarioController.listarUsuarios().size() + 1, 
                nome, cpf, endereco, telefone, cargo, departamento, 
                salario, "01/01/2023", contaBancaria, beneficios, 
                "username", "password");

            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
        });

        voltarButton.addActionListener(e -> showControlPanel());
    }

    // Listar usuários
    private void showListarUsuarios() {
        getContentPane().removeAll();

        String[] colunas = {"ID", "Nome", "CPF", "Endereço", "Telefone", "Cargo", "Departamento", "Salário", "Atualizar"};

        java.util.List<Usuario> usuarios = usuarioController.listarUsuarios();
        String[][] dados = new String[usuarios.size()][9];
        
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            dados[i][0] = String.valueOf(usuario.getId());
            dados[i][1] = usuario.getNome();
            dados[i][2] = usuario.getCpf();
            dados[i][3] = usuario.getEndereco();
            dados[i][4] = usuario.getTelefone();
            dados[i][5] = usuario.getCargo();
            dados[i][6] = usuario.getDepartamento();
            dados[i][7] = String.valueOf(usuario.getSalario());
            dados[i][8] = "Atualizar";
        }

        JTable table = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, BorderLayout.SOUTH);

        voltarButton.addActionListener(e -> showControlPanel());

        revalidate();
        repaint();
    }

    // Tela de registro de ponto
    private void showRegistroPonto() {
        getContentPane().removeAll();
        JPanel registroPontoPanel = new JPanel();
        registroPontoPanel.setLayout(new GridLayout(3, 2));

        JTextField funcionarioIdField = new JTextField();
        JButton registrarEntradaButton = new JButton("Registrar Entrada");
        JButton registrarSaidaButton = new JButton("Registrar Saída");
        JButton voltarButton = new JButton("Voltar");

        registroPontoPanel.add(new JLabel("ID do Funcionário:"));
        registroPontoPanel.add(funcionarioIdField);
        registroPontoPanel.add(registrarEntradaButton);
        registroPontoPanel.add(registrarSaidaButton);
        registroPontoPanel.add(voltarButton);

        add(registroPontoPanel);
        revalidate();
        repaint();

        // Ação de registrar entrada
        registrarEntradaButton.addActionListener(e -> {
            int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
            pontoController.registrarEntrada(funcionarioId);
            JOptionPane.showMessageDialog(null, "Entrada registrada com sucesso!");
        });

        // Ação de registrar saída
        registrarSaidaButton.addActionListener(e -> {
            int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
            pontoController.registrarSaida(funcionarioId);
            JOptionPane.showMessageDialog(null, "Saída registrada com sucesso!");
        });

        voltarButton.addActionListener(e -> showControlPanel());
    }

    // Geração de folha de pagamento
    private void showGeracaoFolhaPagamento() {
        getContentPane().removeAll();
        JPanel folhaPagamentoPanel = new JPanel();
        
        JTextField usuarioIdField = new JTextField(10); // Campo para ID do usuário
        JTextField salarioField = new JTextField(10); // Campo para salário
        JButton gerarButton = new JButton("Gerar Folha de Pagamento");
        JButton voltarButton = new JButton("Voltar");
    
        folhaPagamentoPanel.add(new JLabel("ID do Funcionário:"));
        folhaPagamentoPanel.add(usuarioIdField);
        folhaPagamentoPanel.add(new JLabel("Horas:"));
        folhaPagamentoPanel.add(salarioField);
        folhaPagamentoPanel.add(gerarButton);
        folhaPagamentoPanel.add(voltarButton);
    
        add(folhaPagamentoPanel);
        revalidate();
        repaint();
    
        gerarButton.addActionListener(e -> {
            int usuarioId = Integer.parseInt(usuarioIdField.getText());
            long salario = Long.parseLong(salarioField.getText());
            
            // Supondo que você tenha um método para buscar o usuário pelo ID
            Usuario usuario = usuarioController.buscarUsuarioPorId(usuarioId);
            if (usuario != null) {
                folhaPagamentoController.gerarFolhaPagamento(usuario, salario);
                JOptionPane.showMessageDialog(null, "Folha de pagamento gerada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            }
        });
    
        voltarButton.addActionListener(e -> showControlPanel());
    }

    // Tela de geração de relatórios
    private void showGerarRelatorios() {
        getContentPane().removeAll();
        JPanel relatorioPanel = new JPanel();
        relatorioPanel.setLayout(new GridLayout(4, 1));

        JButton relatorioFolhaButton = new JButton("Relatório de Folha de Pagamento");
        JButton relatorioPontoButton = new JButton("Relatório de Ponto");
        JButton relatorioFuncionariosAtivosButton = new JButton("Relatório de Funcionários Ativos");
        JButton voltarButton = new JButton("Voltar");

        relatorioPanel.add(relatorioFolhaButton);
        relatorioPanel.add(relatorioPontoButton);
        relatorioPanel.add(relatorioFuncionariosAtivosButton);
        relatorioPanel.add(voltarButton);

        add(relatorioPanel);
        revalidate();
        repaint();

        relatorioFolhaButton.addActionListener(e -> {
            relatorioController.gerarRelatorioFolhaPagamentoTXT(usuarioController.listarUsuarios());
            JOptionPane.showMessageDialog(null, "Relatório de folha de pagamento gerado com sucesso!");
        });

        relatorioPontoButton.addActionListener(e -> {
            relatorioController.gerarRelatorioPontoTXT(pontoController.listarPontos(), usuarioController.listarUsuarios());
            JOptionPane.showMessageDialog(null, "Relatório de ponto gerado com sucesso!");
        });

        relatorioFuncionariosAtivosButton.addActionListener(e -> {
            relatorioController.gerarRelatorioFuncionariosAtivosTXT(usuarioController.listarUsuarios());
            JOptionPane.showMessageDialog(null, "Relatório de funcionários ativos gerado com sucesso!");
        });

        voltarButton.addActionListener(e -> showControlPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RHManagementSystem system = new RHManagementSystem();
            system.setVisible(true);
        });
    }
}
