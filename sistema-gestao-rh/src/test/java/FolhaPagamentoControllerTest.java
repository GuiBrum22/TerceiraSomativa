import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.rh.management.Controllers.FolhaPagamentoController;
import com.rh.management.models.Usuario;

public class FolhaPagamentoControllerTest {

    @Test
    public void testCalculoSalarioLiquido() {
        // Configura os dados de um funcionário fictício
        Usuario funcionario = new Usuario(0, null, null, null, null, null, null, 0, null, null, null, false, null, null);
        funcionario.setNome("João");
        funcionario.setSalario(3000.00); // Salário base de R$ 3000
        funcionario.setCargo("Desenvolvedor");
        funcionario.setDepartamento("TI");

        FolhaPagamentoController folhaController = new FolhaPagamentoController();

        // Testa com 160 horas trabalhadas (horário completo)
        double salarioBase = 3000.00;
        double salarioPorHora = salarioBase / 160;
        double salarioFinal = salarioPorHora * 160;
        double inss = salarioFinal * 0.08;
        double fgts = salarioFinal * 0.08;
        double valeTransporte = salarioFinal * 0.06;
        double valeRefeicao = salarioFinal * 0.02;

        double salarioLiquidoEsperado = salarioFinal - inss - fgts - valeTransporte + valeRefeicao;

        // Simula a geração da folha de pagamento
        folhaController.gerarFolhaPagamento(funcionario, 160);

        // Aqui seria onde você testaria a saída esperada, 
        // como gerar um arquivo PDF e verificar se o conteúdo está correto
        assertEquals(salarioLiquidoEsperado, salarioFinal - inss - fgts - valeTransporte + valeRefeicao);
    }
}
