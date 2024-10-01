package com.rh.management.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rh.management.models.Funcionario;
import com.rh.management.models.Ponto;

public class PontoService {
    private List<Ponto> registrosDePonto = new ArrayList<>();

    // Registrar o ponto de um funcionário
    public void registrarPonto(Funcionario funcionario, LocalDateTime entrada, LocalDateTime saida) {
        Ponto ponto = new Ponto(funcionario, entrada, saida);
        registrosDePonto.add(ponto);
    }

    // Listar registros de ponto por funcionário
    public List<Ponto> listarPontosPorFuncionario(Funcionario funcionario) {
        List<Ponto> pontosDoFuncionario = new ArrayList<>();
        for (Ponto ponto : registrosDePonto) {
            if (ponto.getFuncionario().equals(funcionario)) {
                pontosDoFuncionario.add(ponto);
            }
        }
        return pontosDoFuncionario;
    }

    public List<Ponto> getRegistrosDePonto() {
        return registrosDePonto;
    }

    public void setRegistrosDePonto(List<Ponto> registrosDePonto) {
        this.registrosDePonto = registrosDePonto;
    }
}

