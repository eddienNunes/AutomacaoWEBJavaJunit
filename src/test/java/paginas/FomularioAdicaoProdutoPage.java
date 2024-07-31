package paginas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FomularioAdicaoProdutoPage {
    private WebDriver navegador;

    public FomularioAdicaoProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }
    public FomularioAdicaoProdutoPage informarNomeDoProduto(String nomeProduto){
        navegador.findElement(By.id("produtonome")).sendKeys(nomeProduto);
        return this;
    }

    public FomularioAdicaoProdutoPage informarValorDoProduto(String valorProduto){
        navegador.findElement(By.id("produtovalor")).sendKeys(valorProduto);
        return this;
    }

    public FomularioAdicaoProdutoPage informarCorDoProduto(String coresProduto){
        navegador.findElement(By.id("produtocores")).sendKeys(coresProduto);
        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeProdutoComErro(){
        navegador.findElement(By.id("btn-salvar")).click();
        return new ListaDeProdutosPage(navegador);
    }
    public FomulariodeEdicaoProdutoPage submeterFormularioDeProdutoComSucesso(){
        navegador.findElement(By.id("btn-salvar")).click();
        return new FomulariodeEdicaoProdutoPage(navegador);
    }
}
