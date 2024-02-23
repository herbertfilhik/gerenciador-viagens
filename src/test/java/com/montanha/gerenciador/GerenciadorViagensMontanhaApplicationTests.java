package com.montanha.gerenciador;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.montanha.gerenciador.entities.Usuario;
import com.montanha.gerenciador.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"previsaoDoTempoUri=http://demo5966266.mockable.io/",
							  "jwt.secret=testando",
							  "jwt.expiration=123"})
public class GerenciadorViagensMontanhaApplicationTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private GerenciadorViagensMontanhaApplication application;

    @Test
    public void testCommandLineRunner() throws Exception {
        // Executa o método commandLineRunner para simular a inicialização da aplicação
        application.commandLineRunner().run("");

        // Verifica se o método save foi chamado duas vezes
        verify(usuarioRepository, times(2)).save(any(Usuario.class));
    }
}
