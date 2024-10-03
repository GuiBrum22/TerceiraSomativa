package com.rh.management.Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.rh.management.models.Ponto;
import com.rh.management.models.Usuario;

public class RelatorioController {

    // Geração do relatório de Folha de Pagamento em TXT
    public void gerarRelatorioFolhaPagamentoTXT(List<Usuario> funcionarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_folha_pagamento.txt"))) {
            writer.write("Relatório de Folha de Pagamento\n");
            writer.write("-----------------------------\n");

            for (Usuario funcionario : funcionarios) {
                writer.write("Nome: " + funcionario.getNome() + "\n");
                writer.write("Salário: R$ " + funcionario.getSalario() + "\n");
                writer.write("Cargo: " + funcionario.getCargo() + "\n");
                writer.write("Departamento: " + funcionario.getDepartamento() + "\n");
                writer.write("-----------------------------\n");
            }

            System.out.println("Relatório de Folha de Pagamento gerado com sucesso em TXT!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório de folha de pagamento em TXT.");
        }
    }

    // Geração do relatório de Ponto em TXT
    public void gerarRelatorioPontoTXT(List<Ponto> pontos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_ponto.txt"))) {
            writer.write("Relatório de Ponto\n");
            writer.write("-----------------------------\n");

            for (Ponto ponto : pontos) {
                writer.write("ID do Funcionário: " + ponto.getFuncionarioId() + "\n");
                writer.write("Entrada: " + ponto.formatarDataHora(ponto.getEntrada()) + "\n");

                if (ponto.getSaida() != null) {
                    writer.write("Saída: " + ponto.formatarDataHora(ponto.getSaida()) + "\n");
                    writer.write("Horas Trabalhadas: " + ponto.calcularHorasTrabalhadas() + " horas\n");
                } else {
                    writer.write("Saída ainda não registrada.\n");
                }

                writer.write("-----------------------------\n");
            }

            System.out.println("Relatório de Ponto gerado com sucesso em TXT!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório de ponto em TXT.");
        }
    }

    // Geração do relatório de Funcionários Ativos em TXT
    public void gerarRelatorioFuncionariosAtivosTXT(List<Usuario> funcionarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_funcionarios_ativos.txt"))) {
            writer.write("Relatório de Funcionários Ativos\n");
            writer.write("-----------------------------\n");

            for (Usuario funcionario : funcionarios) {
                if (funcionario.isAtivo()) {
                    writer.write("Nome: " + funcionario.getNome() + "\n");
                    writer.write("Cargo: " + funcionario.getCargo() + "\n");
                    writer.write("Departamento: " + funcionario.getDepartamento() + "\n");
                    writer.write("-----------------------------\n");
                }
            }

            System.out.println("Relatório de Funcionários Ativos gerado com sucesso em TXT!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório de funcionários ativos em TXT.");
        }
    }
}
