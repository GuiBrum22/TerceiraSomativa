package com.rh.management.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ponto {
    private int id;
    private int funcionarioId;  // ID do funcionário que registrou o ponto
    private LocalDateTime entrada;
    private LocalDateTime saida;

    // Construtor
    public Ponto(int id, int funcionarioId) {
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.entrada = LocalDateTime.now();  // Registra a entrada automaticamente
        this.saida = null;  // Saída ainda não registrada
    }

    // Getter e Setter para ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter para FuncionarioId
    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    // Getter para Entrada
    public LocalDateTime getEntrada() {
        return entrada;
    }

    // Getter para Saída
    public LocalDateTime getSaida() {
        return saida;
    }

    // Método para registrar a saída
    public void registrarSaida() {
        if (this.saida == null) {
            this.saida = LocalDateTime.now();  // Registra a saída no momento atual
        } else {
            System.out.println("Saída já registrada.");
        }
    }

    // Calcular horas trabalhadas
    public long calcularHorasTrabalhadas() {
        if (saida != null) {
            Duration duracao = Duration.between(entrada, saida);
            return duracao.toHours();  // Retorna as horas trabalhadas
        } else {
            System.out.println("Saída ainda não registrada.");
            return 0;
        }
    }

    // Método para formatar a data/hora
    public String formatarDataHora(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    // Exibir detalhes do ponto
    public void exibirDetalhesPonto() {
        System.out.println("ID do Ponto: " + id);
        System.out.println("ID do Funcionário: " + funcionarioId);
        System.out.println("Entrada: " + formatarDataHora(entrada));
        if (saida != null) {
            System.out.println("Saída: " + formatarDataHora(saida));
            System.out.println("Horas Trabalhadas: " + calcularHorasTrabalhadas() + " horas");
        } else {
            System.out.println("Saída ainda não registrada.");
        }
    }

    // Método para definir a entrada
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    // Método para definir a saída
    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }
}
