package modulos.produtos;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutoTest {
    @Test
    @DisplayName("Teste 01")
    public void testNaoEpermitidoRegistraProdutoIgualAzero()
    {
        //abir o navegador
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // ir para a lojinha web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        //fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("edmarnunes");
        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("123456");
        navegador.findElement(By.id("btn-entrar")).click();

        //ir para tela de registro de produto
         navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        //vou preencher daod do produto e valor igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("ProdAutomaWeb ");
        navegador.findElement(By.id("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("Azul, Amarelo");

        // submeter o formulario
        navegador.findElement(By.id("btn-salvar")).click();
        //validar mensagem de erro
        String msgToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", msgToast);
        navegador.quit();
    }
}
