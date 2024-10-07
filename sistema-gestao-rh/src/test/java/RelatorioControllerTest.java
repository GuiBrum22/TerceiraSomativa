import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.rh.management.Controllers.RelatorioController;
import com.rh.management.models.Usuario;

public class RelatorioControllerTest {

    @Test
    public void testGerarRelatorioFolhaPagamentoTXT() {
        RelatorioController relatorioController = new RelatorioController();
        List<Usuario> funcionarios = new ArrayList<>();

        // Criar exemplo de funcionário
        Usuario funcionario = new Usuario(0, null, null, null, null, null, null, 0, null, null, null, false, null, null);
        funcionario.setNome("João Silva");
        funcionario.setSalario(5000.00);
        funcionario.setCargo("Desenvolvedor");
        funcionario.setDepartamento("TI");
        funcionarios.add(funcionario);

        relatorioController.gerarRelatorioFolhaPagamentoTXT(funcionarios);

        // Verificar se o arquivo foi criado
        File arquivo = new File("relatorio_folha_pagamento.txt");
        assertTrue(arquivo.exists());
    }
}

