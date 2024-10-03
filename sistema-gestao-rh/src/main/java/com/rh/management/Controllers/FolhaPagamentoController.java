package com.rh.management.Controllers;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.rh.management.models.Usuario;

public class FolhaPagamentoController {

    private static final double INSS_PERCENTUAL = 0.08;
    private static final double FGTS_PERCENTUAL = 0.08;
    private static final double VALE_TRANSPORTE_PERCENTUAL = 0.06;
    private static final double VALE_REFEICAO = 300.00; // valor fixo de benefício de refeição

    // Método para gerar a folha de pagamento
    public void gerarFolhaPagamento(Usuario funcionario, long horasTrabalhadas) {
        double salarioBase = funcionario.getSalario();
        double salarioPorHora = salarioBase / 160; // Considerando 160 horas mensais
        double salarioFinal = salarioPorHora * horasTrabalhadas;

        // Cálculo dos descontos
        double descontoINSS = salarioFinal * INSS_PERCENTUAL;
        double descontoFGTS = salarioFinal * FGTS_PERCENTUAL;
        double descontoValeTransporte = salarioFinal * VALE_TRANSPORTE_PERCENTUAL;

        // Cálculo do salário líquido
        double salarioLiquido = salarioFinal - descontoINSS - descontoFGTS - descontoValeTransporte + VALE_REFEICAO;

        // Gerar o PDF da folha de pagamento
        try {
            // Define o caminho do arquivo PDF
            String nomeArquivo = funcionario.getNome() + "_folha_pagamento.pdf";
            
            // Criar o PdfWriter para escrever o conteúdo no arquivo
            PdfWriter writer = new PdfWriter(nomeArquivo);
            
            // Criar o documento PDF
            com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document documento = new Document(pdf);
            
            // Adicionar conteúdo ao PDF
            documento.add(new Paragraph("Folha de Pagamento"));
            documento.add(new Paragraph("-----------------------------"));
            documento.add(new Paragraph("Nome: " + funcionario.getNome()));
            documento.add(new Paragraph("Cargo: " + funcionario.getCargo()));
            documento.add(new Paragraph("Departamento: " + funcionario.getDepartamento()));
            documento.add(new Paragraph("Horas trabalhadas: " + horasTrabalhadas));
            documento.add(new Paragraph("Salário Base: R$ " + String.format("%.2f", salarioBase)));
            documento.add(new Paragraph("Salário Final (Horas trabalhadas): R$ " + String.format("%.2f", salarioFinal)));
            documento.add(new Paragraph("-----------------------------"));
            documento.add(new Paragraph("Descontos:"));
            documento.add(new Paragraph("INSS (8%): R$ " + String.format("%.2f", descontoINSS)));
            documento.add(new Paragraph("FGTS (8%): R$ " + String.format("%.2f", descontoFGTS)));
            documento.add(new Paragraph("Vale Transporte (6%): R$ " + String.format("%.2f", descontoValeTransporte)));
            documento.add(new Paragraph("Vale Refeição: R$ " + String.format("%.2f", VALE_REFEICAO)));
            documento.add(new Paragraph("-----------------------------"));
            documento.add(new Paragraph("Salário Líquido: R$ " + String.format("%.2f", salarioLiquido)));

            // Fechar o documento
            documento.close();
            System.out.println("Folha de pagamento gerada com sucesso! Arquivo: " + nomeArquivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar a folha de pagamento.");
        }
    }
}
    