package com.montanha.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtilsTest {

    @Test
    public void testGerarBCrypt() {
        String senha = "senha123";
        String senhaEncoded = SenhaUtils.gerarBCrypt(senha);

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptEncoder.matches(senha, senhaEncoded);

        assertThat(matches).isTrue();
    }

    @Test
    public void testGerarBCryptComSenhaNula() {
        String senha = null;
        String senhaEncoded = SenhaUtils.gerarBCrypt(senha);

        assertThat(senhaEncoded).isNull();
    }

    @Test
    public void testSenhaValida() {
        String senha = "senha123";
        String senhaEncoded = new BCryptPasswordEncoder().encode(senha);

        boolean isValid = SenhaUtils.senhaValida(senha, senhaEncoded);

        assertThat(isValid).isTrue();
    }

    @Test
    public void testSenhaInvalida() {
        String senha = "senha123";
        String senhaErrada = "senhaErrada";
        String senhaEncoded = new BCryptPasswordEncoder().encode(senha);

        boolean isValid = SenhaUtils.senhaValida(senhaErrada, senhaEncoded);

        assertThat(isValid).isFalse();
    }
    
    @Test
    @Disabled("Este teste está sendo ignorado temporariamente")
    public void testGerarBCryptComSenhaMuitoLonga() {
        // Senha com 100 caracteres
        /*String senha = "a".repeat(100);
        String senhaEncoded = SenhaUtils.gerarBCrypt(senha);

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        assertThat(bCryptEncoder.matches(senha, senhaEncoded)).isTrue();*/
    }

    @Test
    public void testGerarBCryptComCaracteresEspeciais() {
        String senha = "senha@#$$%ˆ&123";
        String senhaEncoded = SenhaUtils.gerarBCrypt(senha);

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        assertThat(bCryptEncoder.matches(senha, senhaEncoded)).isTrue();
    }

    @Test
    public void testSenhaValidaComMesmaSenhaHashedVariasVezes() {
        String senha = "senha123";
        String senhaEncodedPrimeiraVez = SenhaUtils.gerarBCrypt(senha);
        String senhaEncodedSegundaVez = SenhaUtils.gerarBCrypt(senha);

        assertThat(senhaEncodedPrimeiraVez).isNotEqualTo(senhaEncodedSegundaVez);

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        assertThat(bCryptEncoder.matches(senha, senhaEncodedPrimeiraVez)).isTrue();
        assertThat(bCryptEncoder.matches(senha, senhaEncodedSegundaVez)).isTrue();
    }

    @Test
    @Disabled("Este teste está sendo ignorado temporariamente")
    public void testSenhaValidaComSenhaNulaESenhaEncodedNaoNula() {
        String senha = null;
        String senhaEncoded = new BCryptPasswordEncoder().encode("senha123");

        assertThat(SenhaUtils.senhaValida(senha, senhaEncoded)).isFalse();
    }

    @Test
    public void testSenhaValidaComSenhaNaoNulaESenhaEncodedNula() {
        String senha = "senha123";
        String senhaEncoded = null;

        assertThat(SenhaUtils.senhaValida(senha, senhaEncoded)).isFalse();
    }

    @Test
    public void testSenhaValidaComAmbosParametrosNulos() {
        String senha = null;
        String senhaEncoded = null;

        assertThat(SenhaUtils.senhaValida(senha, senhaEncoded)).isFalse();
    }

    @Test
    public void testSenhaInvalidaComSenhaCorretaESenhaEncodedDiferente() {
        String senha = "senha123";
        String senhaEncodedDiferente = SenhaUtils.gerarBCrypt("senhaDiferente123");

        assertThat(SenhaUtils.senhaValida(senha, senhaEncodedDiferente)).isFalse();
    }

    @Test
    public void testGerarBCryptGaranteHashesDiferentes() {
        String senha = "senha123";
        String senhaEncodedPrimeiraVez = SenhaUtils.gerarBCrypt(senha);
        String senhaEncodedSegundaVez = SenhaUtils.gerarBCrypt(senha);

        assertThat(senhaEncodedPrimeiraVez).isNotEqualTo(senhaEncodedSegundaVez);
    }
}
