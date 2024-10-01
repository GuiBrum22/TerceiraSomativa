package com.rh.management.models;

import java.time.LocalDateTime;

public class Ponto {
    private Funcionario funcionario;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public Ponto(Funcionario funcionario, LocalDateTime entrada, LocalDateTime saida) {
        this.funcionario = funcionario;
        this.entrada = entrada;
        this.saida = saida;
    }

    // Método para calcular as horas trabalhadas
    public long calcularHorasTrabalhadas() {
        return java.time.Duration.between(entrada, saida).toHours();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    // Getters e Setters
    
}

