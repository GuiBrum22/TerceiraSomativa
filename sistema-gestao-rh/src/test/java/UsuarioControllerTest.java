import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rh.management.models.Usuario;

import java.util.List;

import com.rh.management.Controllers.UsuarioController;

public class UsuarioControllerTest {

    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        // Limpa o estado do controlador para cada teste
        usuarioController = new UsuarioController();
    }

    @Test
    public void testCadastrarUsuario() {
        // Cadastra um novo usuário
        usuarioController.cadastrarUsuario(1, "João Silva", "123.456.789-10", "Rua A", "9999-9999", "Desenvolvedor",
                "TI", 5000.0, "10/01/2020", "Banco A", "Vale Transporte", "joaosilva", "senha123");

        // Verifica se o usuário foi adicionado corretamente
        Usuario usuario = usuarioController.buscarUsuarioPorId(1);
        assertNotNull(usuario, "O usuário deve ser encontrado");
        assertEquals("João Silva", usuario.getNome(), "O nome deve ser João Silva");
    }

    @Test
    public void testBuscarUsuarioPorId() {
        // Cadastra um novo usuário
        usuarioController.cadastrarUsuario(2, "Maria Souza", "987.654.321-00", "Rua B", "8888-8888", "Analista",
                "Financeiro", 4500.0, "15/02/2021", "Banco B", "Vale Alimentação", "mariasouza", "senha123");

        // Verifica se o usuário é encontrado corretamente
        Usuario usuario = usuarioController.buscarUsuarioPorId(2);
        assertNotNull(usuario, "O usuário deve ser encontrado");
        assertEquals("Maria Souza", usuario.getNome(), "O nome deve ser Maria Souza");
    }

    @Test
    public void testAtualizarUsuario() {
        // Cadastra um novo usuário
        usuarioController.cadastrarUsuario(3, "Carlos Pereira", "321.654.987-00", "Rua C", "7777-7777", "Gerente",
                "Vendas", 7000.0, "20/03/2019", "Banco C", "Plano de Saúde", "carlospereira", "senha123");

        // Atualiza as informações do usuário
        usuarioController.atualizarUsuario(3, "Rua D", "6666-6666", "Diretor", "Administração", 8000.0, "Banco D", "Vale Refeição");

        // Verifica se as informações foram atualizadas corretamente
        Usuario usuarioAtualizado = usuarioController.buscarUsuarioPorId(3);
        assertEquals("Rua D", usuarioAtualizado.getEndereco(), "O endereço deve ser Rua D");
        assertEquals("Diretor", usuarioAtualizado.getCargo(), "O cargo deve ser Diretor");
    }

    @Test
    public void testDesativarUsuario() {
        // Cadastra um novo usuário
        usuarioController.cadastrarUsuario(4, "Ana Lima", "654.321.987-00", "Rua E", "5555-5555", "Assistente",
                "RH", 3500.0, "05/06/2018", "Banco E", "Vale Transporte", "analima", "senha123");

        // Desativa o usuário
        usuarioController.desativarUsuario(4);

        // Verifica se o usuário foi desativado
        Usuario usuario = usuarioController.buscarUsuarioPorId(4);
        assertFalse(usuario.isAtivo(), "O usuário deve estar desativado");
    }

    @Test
    public void testRemoverUsuario() {
        // Cadastra um novo usuário
        usuarioController.cadastrarUsuario(5, "Paulo Castro", "963.852.741-00", "Rua F", "4444-4444", "Coordenador",
                "Marketing", 6000.0, "25/07/2017", "Banco F", "Plano Odontológico", "paulocastro", "senha123");

        // Remove o usuário
        usuarioController.removerUsuario(5);

        // Verifica se o usuário foi removido corretamente
        Usuario usuario = usuarioController.buscarUsuarioPorId(5);
        assertNull(usuario, "O usuário deve ser removido");
    }

    @Test
    public void testListarPorDepartamento() {
        // Cadastra vários usuários
        usuarioController.cadastrarUsuario(6, "Lucas Mendes", "852.741.963-00", "Rua G", "3333-3333", "Analista",
                "TI", 4500.0, "12/09/2022", "Banco G", "Vale Transporte", "lucasmendes", "senha123");
        usuarioController.cadastrarUsuario(7, "Fernanda Costa", "741.963.852-00", "Rua H", "2222-2222", "Desenvolvedor",
                "TI", 5000.0, "18/10/2020", "Banco H", "Vale Alimentação", "fernandacosta", "senha123");

        // Filtra os usuários do departamento de TI
        List<Usuario> usuariosTI = usuarioController.listarPorDepartamento("TI");

        // Verifica se a quantidade de usuários filtrados está correta
        assertEquals(2, usuariosTI.size(), "Devem existir 2 usuários no departamento de TI");
    }
}
