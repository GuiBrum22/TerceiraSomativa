package com.rh.management.models;

public class Relatorio {
    private int id;
    private Usuario usuario;
    private Ponto ponto;  // Ponto pode ser null dependendo do tipo de relatório

    // Construtor
    public Relatorio(int id, Usuario usuario, Ponto ponto) {
        this.id = id;
        this.usuario = usuario;
        this.ponto = ponto;
    }

    // Gera o relatório completo considerando se ponto é null ou não
    public String gerarRelatorioCompleto() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("ID Relatório: ").append(id).append("\n");
        relatorio.append("Nome: ").append(usuario.getNome()).append("\n");
        relatorio.append("Cargo: ").append(usuario.getCargo()).append("\n");
        relatorio.append("Departamento: ").append(usuario.getDepartamento()).append("\n");

        // Se o ponto não for null, adiciona as informações de ponto
        if (ponto != null) {
            relatorio.append("Entrada: ").append(ponto.formatarDataHora(ponto.getEntrada())).append("\n");
            if (ponto.getSaida() != null) {
                relatorio.append("Saída: ").append(ponto.formatarDataHora(ponto.getSaida())).append("\n");
                relatorio.append("Horas Trabalhadas: ").append(ponto.calcularHorasTrabalhadas()).append(" horas\n");
            } else {
                relatorio.append("Saída ainda não registrada.\n");
            }
        } else {
            // Relatório de folha de pagamento, adiciona detalhes de salário
            relatorio.append("Salário: R$ ").append(usuario.getSalario()).append("\n");
        }

        relatorio.append("-----------------------------\n");
        return relatorio.toString();
    }
}
