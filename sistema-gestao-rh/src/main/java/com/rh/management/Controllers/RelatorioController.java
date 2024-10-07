package com.rh.management.Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.rh.management.models.Ponto;
import com.rh.management.models.Relatorio;
import com.rh.management.models.Usuario;

public class RelatorioController {

    // Geração do relatório de Folha de Pagamento em TXT usando a superclasse Relatorio
    public void gerarRelatorioFolhaPagamentoTXT(List<Usuario> funcionarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_folha_pagamento.txt"))) {
            writer.write("Relatório de Folha de Pagamento\n");
            writer.write("-----------------------------\n");

            for (Usuario funcionario : funcionarios) {
                Relatorio relatorio = new Relatorio(1, funcionario, null); // Ponto null para folha de pagamento
                writer.write(relatorio.gerarRelatorioCompleto());
            }

            System.out.println("Relatório de Folha de Pagamento gerado com sucesso em TXT!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório de folha de pagamento em TXT.");
        }
    }

    // Geração do relatório de Ponto em TXT usando a superclasse Relatorio
    public void gerarRelatorioPontoTXT(List<Ponto> pontos, List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_ponto.txt"))) {
            writer.write("Relatório de Ponto\n");
            writer.write("-----------------------------\n");

            for (Ponto ponto : pontos) {
                // Encontra o usuário associado ao ponto pelo ID do funcionário
                Usuario usuarioRelacionado = usuarios.stream()
                        .filter(u -> u.getId() == ponto.getFuncionarioId())
                        .findFirst().orElse(null);

                if (usuarioRelacionado != null) {
                    Relatorio relatorio = new Relatorio(1, usuarioRelacionado, ponto);
                    writer.write(relatorio.gerarRelatorioCompleto());
                }
            }

            System.out.println("Relatório de Ponto gerado com sucesso em TXT!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório de ponto em TXT.");
        }
    }

    // Geração do relatório de Funcionários Ativos em TXT usando a superclasse Relatorio
    public void gerarRelatorioFuncionariosAtivosTXT(List<Usuario> funcionarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_funcionarios_ativos.txt"))) {
            writer.write("Relatório de Funcionários Ativos\n");
            writer.write("-----------------------------\n");

            for (Usuario funcionario : funcionarios) {
                if (funcionario.isAtivo()) {
                    Relatorio relatorio = new Relatorio(1, funcionario, null);  // Ponto null para relatório de funcionários ativos
                    writer.write(relatorio.gerarRelatorioCompleto());
                }
            }

            System.out.println("Relatório de Funcionários Ativos gerado com sucesso em TXT!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório de funcionários ativos em TXT.");
        }
    }
}
