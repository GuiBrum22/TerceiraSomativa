    package com.rh.management.models;

    public class Usuario {
        private int id;
        private String nome;
        private String cpf;
        private String endereco;
        private String telefone;
        private String cargo;
        private String departamento;
        private double salario;
        private String dataAdmissao;
        private String contaBancaria;
        private String beneficios;
        private boolean ativo;
        private String username;  // Adicionado o campo username
        private String password;  // Adicionado o campo password

        // Construtor
        public Usuario(int id, String nome, String cpf, String endereco, String telefone, String cargo, 
                    String departamento, double salario, String dataAdmissao, String contaBancaria, 
                    String beneficios, boolean ativo, String username, String password) {
            this.id = id;
            this.nome = nome;
            this.cpf = cpf;
            this.endereco = endereco;
            this.telefone = telefone;
            this.cargo = cargo;
            this.departamento = departamento;
            this.salario = salario;
            this.dataAdmissao = dataAdmissao;
            this.contaBancaria = contaBancaria;
            this.beneficios = beneficios;
            this.ativo = ativo;
            this.username = username;  // Atribui o valor do username
            this.password = password;  // Atribui o valor do password
        }

        // Getters e Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getCargo() {
            return cargo;
        }

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        public String getDepartamento() {
            return departamento;
        }

        public void setDepartamento(String departamento) {
            this.departamento = departamento;
        }

        public double getSalario() {
            return salario;
        }

        public void setSalario(double salario) {
            this.salario = salario;
        }

        public String getDataAdmissao() {
            return dataAdmissao;
        }

        public void setDataAdmissao(String dataAdmissao) {
            this.dataAdmissao = dataAdmissao;
        }

        public String getContaBancaria() {
            return contaBancaria;
        }

        public void setContaBancaria(String contaBancaria) {
            this.contaBancaria = contaBancaria;
        }

        public String getBeneficios() {
            return beneficios;
        }

        public void setBeneficios(String beneficios) {
            this.beneficios = beneficios;
        }

        public boolean isAtivo() {
            return ativo;
        }

        public void setAtivo(boolean ativo) {
            this.ativo = ativo;
        }

        // Atualização de informações
        public void atualizarInformacoes(String endereco, String telefone, String cargo, 
                                        String departamento, double salario, 
                                        String contaBancaria, String beneficios) {
            this.endereco = endereco;
            this.telefone = telefone;
            this.cargo = cargo;
            this.departamento = departamento;
            this.salario = salario;
            this.contaBancaria = contaBancaria;
            this.beneficios = beneficios;
        }

        // Desativar funcionário
        public void desativarFuncionario() {
            this.ativo = false;
        }

        // Ativar funcionário
        public void ativarFuncionario() {
            this.ativo = true;
        }

        // Implementação dos métodos getUsername() e getPassword()
        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
