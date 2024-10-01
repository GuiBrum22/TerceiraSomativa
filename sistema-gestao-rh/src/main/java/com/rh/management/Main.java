package com.rh.management;

import com.rh.management.models.Funcionario;
import com.rh.management.models.Departamento;
import com.rh.management.services.DepartamentoService;
import com.rh.management.services.FuncionarioService;
import com.rh.management.services.PontoService;
import com.rh.management.services.RelatorioService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();
        PontoService pontoService = new PontoService();
        DepartamentoService departamentoService = new DepartamentoService();
        RelatorioService relatorioService = new RelatorioService();

        // Cadastro de departamento
        departamentoService.cadastrarDepartamento(1, "TI");
        departamentoService.cadastrarDepartamento(2, "RH");

        // Cadastro de funcionário
        funcionarioService.cadastrarFuncionario(1, "João", "Desenvolvedor", 5000);
        funcionarioService.cadastrarFuncionario(2, "Maria", "Analista de RH", 4000);

        // Registro de ponto
        Funcionario joao = funcionarioService.buscarFuncionarioPorId(1);
        pontoService.registrarPonto(joao, LocalDateTime.now().minusHours(8), LocalDateTime.now());

        // Gerar relatórios
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        try {
            relatorioService.gerarRelatorioCSV(funcionarios, "relatorio_funcionarios.csv");
            relatorioService.gerarRelatorioTXT(funcionarios, "relatorio_funcionarios.txt");
            relatorioService.gerarRelatorioPDF(funcionarios, "relatorio_funcionarios.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
