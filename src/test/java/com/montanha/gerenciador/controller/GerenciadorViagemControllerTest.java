package com.montanha.gerenciador.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.montanha.gerenciador.dtos.ViagemDto;
import com.montanha.gerenciador.entities.Viagem;
import com.montanha.gerenciador.services.ViagemServices;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GerenciadorViagemControllerTest {

    @Mock
    private ViagemServices viagemService;

    @InjectMocks
    private GerenciadorViagemController controller;

    private ViagemDto viagemDto;
    private BindingResult bindingResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viagemDto = new ViagemDto();
        // Configuração inicial do viagemDto conforme necessário
        
        // Inicialização do BindingResult com o viagemDto
        bindingResult = new BeanPropertyBindingResult(viagemDto, "viagemDto");
        
        // Configuração do MockHttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    }

    @Test
    public void testCadastrarViagemComSucesso() {
        // Configuração do mock
        Viagem viagemSalva = new Viagem();
        // Configure sua viagemSalva conforme necessário

        when(viagemService.salvar(any(ViagemDto.class))).thenReturn(viagemSalva);

        // Execução do método
        ResponseEntity<?> responseEntity = controller.cadastrar(viagemDto, "Authorization", bindingResult);

        // Verificação
        assertEquals(201, responseEntity.getStatusCodeValue()); // Verifica se o status é CREATED
        assertNotNull(responseEntity.getBody()); // Verifica se o corpo da resposta não é nulo

        verify(viagemService, times(1)).salvar(any(ViagemDto.class));
    }
    
    @After
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }
}
