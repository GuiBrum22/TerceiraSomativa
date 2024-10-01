package com.rh.management.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rh.management.models.Departamento;
import com.rh.management.models.Funcionario;

public class FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();

    // Método para cadastrar um funcionário
    public void cadastrarFuncionario(int id, String nome, String cpf, String endereco, String telefone, 
                                     String cargo, Departamento departamento, double salario, String contaBancaria) {
        Funcionario funcionario = new Funcionario(id, nome, cpf, endereco, telefone, cargo, departamento, salario, contaBancaria);
        funcionarios.add(funcionario);
    }

    // Atualizar informações do funcionário (promoção, alteração salarial)
    public void atualizarFuncionario(int id, String novoCargo, double novoSalario) {
        Funcionario funcionario = buscarFuncionarioPorId(id);
        if (funcionario != null) {
            funcionario.setCargo(novoCargo);
            funcionario.setSalario(novoSalario);
        }
    }

    // Buscar funcionário pelo ID
    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // Listar funcionários por departamento
    public List<Funcionario> listarPorDepartamento(Departamento departamento) {
        return funcionarios.stream()
                .filter(f -> f.getDepartamento().equals(departamento) && f.isAtivo())
                .collect(Collectors.toList());
    }

    // Desativar funcionário
    public void desativarFuncionario(int id) {
        Funcionario funcionario = buscarFuncionarioPorId(id);
        if (funcionario != null) {
            funcionario.setAtivo(false);
        }
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}