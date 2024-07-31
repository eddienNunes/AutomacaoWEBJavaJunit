package modulos.produtos;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;
import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutoTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        this.navegador = new ChromeDriver();
        this.navegador.manage().window().maximize();
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this. navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test //Caso de Teste 1: Valor do Produto Igual a Zero
    @DisplayName("Verificar se o sistema impede o cadastro de um produto com valor igual a zero")
    public void testValorProdutoIgualZero()
    {

        String msgToast = new LoginPage(navegador)
                .informarOUsuario("edmarnunes")
                .informarASenha("123456")
                .logar()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("ProdWebAutomacao")
                .informarValorDoProduto("000")
                .informarCorDoProduto("Laranja, verde")
                .submeterFormularioDeProdutoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", msgToast);

    }

    @Test //Caso de Teste 2: Valor do Produto Negativo
    @DisplayName("Verificar se o sistema impede o cadastro de um produto com valor negativo")
    public void testValorProdutoNegativo()
    {
        String msgToast =  new LoginPage(navegador)
                .informarOUsuario("edmarnunes")
                .informarASenha("123456")
                .logar()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("ProdValido")
                .informarValorDoProduto("-10.00")
                .informarCorDoProduto("Cinza")
                .submeterFormularioDeProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso",msgToast);
    }

    @Test  // Caso de Teste 3: Valor do Produto Maior que Zero e Menor que 7.000,00
    @DisplayName("Verificar se o sistema permite o cadastro de um produto com valor válido")
    public void testValorProdutoValido()
    {
        String msgToast =  new LoginPage(navegador)
                .informarOUsuario("edmarnunes")
                .informarASenha("123456")
                .logar()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("ProdValido")
                .informarValorDoProduto("1000.00")
                .informarCorDoProduto("Cinza")
                .submeterFormularioDeProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso",msgToast);
    }

    @Test  //Caso de Teste 4: Valor do Produto Igual a 7.000,00
    @DisplayName("Verificar se o sistema permite o cadastro de um produto com valor no limite superior permitido")
    public void testValorProdutoIgualSeteMil()
    {
        String msgToast =  new LoginPage(navegador)
                .informarOUsuario("edmarnunes")
                .informarASenha("123456")
                .logar()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("ProdValido")
                .informarValorDoProduto("7000.00")
                .informarCorDoProduto("Cinza")
                .submeterFormularioDeProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso",msgToast);
    }

    @Test  // Caso de Teste 5: Valor do Produto Maior que 7.000,00
    @DisplayName("Verificar se o sistema impede o cadastro de um produto com valor superior ao limite permitido")
    public void testValorProdutoMaiorQueSeteMil()
    {

        String msgToast = new LoginPage(navegador)
                .informarOUsuario("edmarnunes")
                .informarASenha("123456")
                .logar()
                .acessarFormularioDeAdicaoNovoProduto()
                .informarNomeDoProduto("ProdWebAutomacao")
                .informarValorDoProduto("7.000,01")
                .informarCorDoProduto("Laranja, verde")
                .submeterFormularioDeProdutoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", msgToast);

    }

    @AfterEach
    public void afterEach(){
        navegador.quit();
    }
}
