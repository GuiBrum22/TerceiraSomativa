import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.rh.management.Controllers.PontoController;
import com.rh.management.models.Ponto;

public class PontoControllerTest {

    @Test
    public void testRegistrarEntrada() {
        PontoController pontoController = new PontoController();

        // Registra entrada de um funcionário fictício
        int funcionarioId = 1;
        pontoController.registrarEntrada(funcionarioId);

        // Verifica se a lista de pontos não está vazia
        assertFalse(pontoController.listarPontosPorFuncionario(funcionarioId).isEmpty(), "A lista de pontos não deve estar vazia.");

        // Recupera o ponto registrado para o funcionário
        Ponto ponto = pontoController.listarPontosPorFuncionario(funcionarioId).get(0);
        System.out.println("Ponto registrado na entrada: " + ponto);

        // Verifica se o ponto foi registrado corretamente
        assertNotNull(ponto.getEntrada(), "A entrada deve ser registrada.");
        assertNull(ponto.getSaida(), "A saída não deve ser registrada ainda.");
        assertEquals(funcionarioId, ponto.getFuncionarioId(), "O ID do funcionário deve corresponder.");
    }

    @Test
    public void testRegistrarSaida() {
        PontoController pontoController = new PontoController();

        // Registra a entrada de um funcionário
        int funcionarioId = 2;
        pontoController.registrarEntrada(funcionarioId);

        // Em seguida, registra a saída
        pontoController.registrarSaida(funcionarioId);

        // Verifica se a lista de pontos não está vazia
        assertFalse(pontoController.listarPontosPorFuncionario(funcionarioId).isEmpty(), "A lista de pontos não deve estar vazia.");

        // Recupera o ponto para verificar a saída
        Ponto ponto = pontoController.listarPontosPorFuncionario(funcionarioId).get(0);
        System.out.println("Ponto registrado na saída: " + ponto);

        // Verifica se a saída foi registrada corretamente
        assertNotNull(ponto.getSaida(), "A saída deve ser registrada.");
        assertTrue(ponto.getSaida().isAfter(ponto.getEntrada()), "A saída deve ser após a entrada.");
    }
}
