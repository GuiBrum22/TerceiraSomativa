package com.rh.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rh.management.Controllers.*;
import com.rh.management.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RHManagementSystem extends JFrame {

    private UsuarioController usuarioController;
    private PontoController pontoController;
    private FolhaPagamentoController folhaPagamentoController;

    public RHManagementSystem() {
        usuarioController = new UsuarioController();
        pontoController = new PontoController();
        folhaPagamentoController = new FolhaPagamentoController();

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

        controlPanel.add(cadastrarUsuarioButton);
        controlPanel.add(registrarPontoButton);
        controlPanel.add(folhaPagamentoButton);

        add(controlPanel);
        revalidate();
        repaint();

        // Ações dos botões
        cadastrarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCadastroUsuario();
            }
        });

        registrarPontoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegistroPonto();
            }
        });

        folhaPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGeracaoFolhaPagamento();
            }
        });
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

        add(cadastroPanel);
        revalidate();
        repaint();

        // Ação do botão de cadastro
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String endereco = enderecoField.getText();
                String telefone = telefoneField.getText();
                String cargo = cargoField.getText();
                String departamento = departamentoField.getText();
                double salario = Double.parseDouble(salarioField.getText());
                String contaBancaria = contaBancariaField.getText();
                String beneficios = beneficiosField.getText();

                // Chamando o controlador para cadastrar o usuário
                usuarioController.cadastrarUsuario(
                    usuarioController.listarUsuarios().size() + 1, 
                    nome, cpf, endereco, telefone, cargo, departamento, 
                    salario, "01/01/2023", contaBancaria, beneficios, 
                    "username", "password");

                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
            }
        });
    }

    // Tela para registrar ponto
    private void showRegistroPonto() {
        getContentPane().removeAll();
        JPanel pontoPanel = new JPanel();
        pontoPanel.setLayout(new GridLayout(3, 2));

        JTextField funcionarioIdField = new JTextField();
        JButton registrarEntradaButton = new JButton("Registrar Entrada");
        JButton registrarSaidaButton = new JButton("Registrar Saída");

        pontoPanel.add(new JLabel("ID do Funcionário:"));
        pontoPanel.add(funcionarioIdField);
        pontoPanel.add(registrarEntradaButton);
        pontoPanel.add(registrarSaidaButton);

        add(pontoPanel);
        revalidate();
        repaint();

        // Ação para registrar entrada
        registrarEntradaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
                pontoController.registrarEntrada(funcionarioId);
                JOptionPane.showMessageDialog(null, "Entrada registrada!");
            }
        });

        // Ação para registrar saída
        registrarSaidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
                pontoController.registrarSaida(funcionarioId);
                JOptionPane.showMessageDialog(null, "Saída registrada!");
            }
        });
    }

    // Tela para geração da folha de pagamento
    private void showGeracaoFolhaPagamento() {
        getContentPane().removeAll();
        JPanel folhaPanel = new JPanel();
        folhaPanel.setLayout(new GridLayout(2, 2));

        JTextField funcionarioIdField = new JTextField();
        JButton gerarFolhaButton = new JButton("Gerar Folha de Pagamento");

        folhaPanel.add(new JLabel("ID do Funcionário:"));
        folhaPanel.add(funcionarioIdField);
        folhaPanel.add(gerarFolhaButton);

        add(folhaPanel);
        revalidate();
        repaint();

        // Ação para gerar a folha de pagamento
        gerarFolhaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
                Usuario funcionario = usuarioController.buscarUsuarioPorId(funcionarioId);
                long horasTrabalhadas = pontoController.calcularHorasTotaisTrabalhadas(funcionarioId);

                if (funcionario != null) {
                    folhaPagamentoController.gerarFolhaPagamento(funcionario, horasTrabalhadas);
                    JOptionPane.showMessageDialog(null, "Folha de pagamento gerada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RHManagementSystem frame = new RHManagementSystem();
            frame.setVisible(true);
        });
    }
}
