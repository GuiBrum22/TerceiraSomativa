package com.rh.management;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.rh.management.Controllers.FolhaPagamentoController;
import com.rh.management.Controllers.PontoController;
import com.rh.management.Controllers.UsuarioController;
import com.rh.management.models.Usuario;

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
        gerarRelatorioButton.addActionListener(e -> gerarRelatorio());
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

            // Chamando o controlador para cadastrar o usuário
            usuarioController.cadastrarUsuario(
                usuarioController.listarUsuarios().size() + 1, 
                nome, cpf, endereco, telefone, cargo, departamento, 
                salario, "01/01/2023", contaBancaria, beneficios, 
                "username", "password");

            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
        });

        // Voltar ao painel de controle
        voltarButton.addActionListener(e -> showControlPanel());
    }

   // Tela para listar usuários com JTable
   private void showListarUsuarios() {
    getContentPane().removeAll();

    // Criação da tabela
    String[] colunas = {"ID", "Nome", "CPF", "Endereço", "Telefone", "Cargo", "Departamento", "Salário", "Atualizar"};
    
    // Obtenção da lista de usuários e conversão para uma matriz de dados
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

    JTable tabelaUsuarios = new JTable(dados, colunas);
    JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
    
    JPanel listarPanel = new JPanel();
    listarPanel.setLayout(new BorderLayout());
    listarPanel.add(scrollPane, BorderLayout.CENTER);

    JButton voltarButton = new JButton("Voltar");
    listarPanel.add(voltarButton, BorderLayout.SOUTH);

    add(listarPanel);
    revalidate();
    repaint();

    voltarButton.addActionListener(e -> showControlPanel());

    // Ação ao clicar na célula de "Atualizar"
    tabelaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int row = tabelaUsuarios.rowAtPoint(evt.getPoint());
            int col = tabelaUsuarios.columnAtPoint(evt.getPoint());
            if (col == 8) { // Se a coluna "Atualizar" foi clicada
                int usuarioId = Integer.parseInt(dados[row][0]);
                Usuario usuario = usuarioController.buscarUsuarioPorId(usuarioId);
                if (usuario != null) {
                    showAtualizarUsuario(usuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
                }
            }
        }
    });
}

// Tela de atualização de funcionário
private void showAtualizarUsuario(Usuario usuario) {
    getContentPane().removeAll();
    JPanel atualizarPanel = new JPanel();
    atualizarPanel.setLayout(new GridLayout(8, 2));

    JTextField enderecoField = new JTextField(usuario.getEndereco());
    JTextField telefoneField = new JTextField(usuario.getTelefone());
    JTextField cargoField = new JTextField(usuario.getCargo());
    JTextField departamentoField = new JTextField(usuario.getDepartamento());
    JTextField salarioField = new JTextField(String.valueOf(usuario.getSalario()));
    JTextField contaBancariaField = new JTextField(usuario.getContaBancaria());
    JTextField beneficiosField = new JTextField(usuario.getBeneficios());
    JButton atualizarButton = new JButton("Atualizar");
    JButton voltarButton = new JButton("Voltar");

    atualizarPanel.add(new JLabel("Endereço:"));
    atualizarPanel.add(enderecoField);
    atualizarPanel.add(new JLabel("Telefone:"));
    atualizarPanel.add(telefoneField);
    atualizarPanel.add(new JLabel("Cargo:"));
    atualizarPanel.add(cargoField);
    atualizarPanel.add(new JLabel("Departamento:"));
    atualizarPanel.add(departamentoField);
    atualizarPanel.add(new JLabel("Salário:"));
    atualizarPanel.add(salarioField);
    atualizarPanel.add(new JLabel("Conta Bancária:"));
    atualizarPanel.add(contaBancariaField);
    atualizarPanel.add(new JLabel("Benefícios:"));
    atualizarPanel.add(beneficiosField);
    atualizarPanel.add(atualizarButton);
    atualizarPanel.add(voltarButton);

    add(atualizarPanel);
    revalidate();
    repaint();

    // Ação do botão de atualização
    atualizarButton.addActionListener(e -> {
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        String cargo = cargoField.getText();
        String departamento = departamentoField.getText();
        double salario = Double.parseDouble(salarioField.getText());
        String contaBancaria = contaBancariaField.getText();
        String beneficios = beneficiosField.getText();

        // Atualizar as informações do usuário
        usuario.atualizarInformacoes(endereco, telefone, cargo, departamento, salario, contaBancaria, beneficios);
        JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");

        showListarUsuarios();
    });

    // Voltar ao painel de controle
    voltarButton.addActionListener(e -> showListarUsuarios());
}


    // Tela para registrar ponto
    private void showRegistroPonto() {
        getContentPane().removeAll();
        JPanel pontoPanel = new JPanel();
        pontoPanel.setLayout(new GridLayout(3, 2));

        JTextField funcionarioIdField = new JTextField();
        JButton registrarEntradaButton = new JButton("Registrar Entrada");
        JButton registrarSaidaButton = new JButton("Registrar Saída");
        JButton voltarButton = new JButton("Voltar");

        pontoPanel.add(new JLabel("ID do Funcionário:"));
        pontoPanel.add(funcionarioIdField);
        pontoPanel.add(registrarEntradaButton);
        pontoPanel.add(registrarSaidaButton);
        pontoPanel.add(voltarButton);

        add(pontoPanel);
        revalidate();
        repaint();

        registrarEntradaButton.addActionListener(e -> {
            int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
            pontoController.registrarEntrada(funcionarioId);
            JOptionPane.showMessageDialog(null, "Entrada registrada!");
        });

        registrarSaidaButton.addActionListener(e -> {
            int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
            pontoController.registrarSaida(funcionarioId);
            JOptionPane.showMessageDialog(null, "Saída registrada!");
        });

        voltarButton.addActionListener(e -> showControlPanel());
    }

    // Tela para geração da folha de pagamento
    private void showGeracaoFolhaPagamento() {
        getContentPane().removeAll();
        JPanel folhaPanel = new JPanel();
        folhaPanel.setLayout(new GridLayout(2, 2));

        JTextField funcionarioIdField = new JTextField();
        JButton gerarFolhaButton = new JButton("Gerar Folha de Pagamento");
        JButton voltarButton = new JButton("Voltar");

        folhaPanel.add(new JLabel("ID do Funcionário:"));
        folhaPanel.add(funcionarioIdField);
        folhaPanel.add(gerarFolhaButton);
        folhaPanel.add(voltarButton);

        add(folhaPanel);
        revalidate();
        repaint();

        gerarFolhaButton.addActionListener(e -> {
            int funcionarioId = Integer.parseInt(funcionarioIdField.getText());
            Usuario funcionario = usuarioController.buscarUsuarioPorId(funcionarioId);
            long horasTrabalhadas = pontoController.calcularHorasTotaisTrabalhadas(funcionarioId);

            if (funcionario != null) {
                folhaPagamentoController.gerarFolhaPagamento(funcionario, horasTrabalhadas);
                JOptionPane.showMessageDialog(null, "Folha de pagamento gerada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
            }
        });

        voltarButton.addActionListener(e -> showControlPanel());
    }

    // Método para gerar o relatório de usuários
    private void gerarRelatorio() {
    String filePath = "relatorio_funcionarios.pdf";

    try {
        // Criação do escritor de PDF
        PdfWriter writer = new PdfWriter(filePath);

        // Criar o documento PDF
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Adicionando título ao relatório
        document.add(new Paragraph("Relatório de Funcionários").setBold().setFontSize(18));

        // Criar tabela com colunas
        float[] pointColumnWidths = {100F, 100F, 150F, 100F, 100F, 100F};
        Table table = new Table(pointColumnWidths);

        // Cabeçalho da tabela
        table.addCell("ID");
        table.addCell("Nome");
        table.addCell("CPF");
        table.addCell("Endereço");
        table.addCell("Telefone");
        table.addCell("Salário");

        // Adicionar dados dos usuários na tabela
        for (Usuario usuario : usuarioController.listarUsuarios()) {
            table.addCell(String.valueOf(usuario.getId()));
            table.addCell(usuario.getNome());
            table.addCell(usuario.getCpf());
            table.addCell(usuario.getEndereco());
            table.addCell(usuario.getTelefone());
            table.addCell(String.valueOf(usuario.getSalario()));
        }

        // Adicionando tabela ao documento
        document.add(table);

        // Fechar o documento PDF
        document.close();

        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso! Salvo em: " + filePath);

    } catch (FileNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RHManagementSystem frame = new RHManagementSystem(); 
            frame.setVisible(true);
        });
    }
}
