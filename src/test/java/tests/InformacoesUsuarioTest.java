package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuarioTest {
    // Declaro a variavel navegador como private expondo a propriedade da para toda classe
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup(){
        navegador = Web.createChrome();

        // Clicar no link que possue o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o Formulário Login
        WebElement formularioSingnInbox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com o name "login" que esta dentro do formulário de id "signinbox" o texto "julio0001"
        formularioSingnInbox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar no campo com o name "password" que esta dentro do formulário de id "signinbox" o texto "123456"
        formularioSingnInbox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar no texto "Hi, Julio" com a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar no link com o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionaDoUsuario(@Param(name="tipo")String tipo,@Param(name="contato")String contato,@Param(name="mensagem")String mensagemEsperada){

        // Clicar no Botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a pop-up onde esta o formulario de id="addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name="type" escolher a opção Phone -- Utilizando o contexto WebElement e New Select
        WebElement comboType = popupAddMoreData.findElement(By.name("type"));
        new Select(comboType).selectByVisibleText(tipo);

        // No campo de name="contact" digitar "+55419999-1111"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que esta na pop-up
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de ID "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada,mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario(){
        // Clicar no elemento pelo xpath //span[text()="+551133334444"]/following-sibling:a
        navegador.findElement(By.xpath("//span[text()=\"+554199994358\"]/following-sibling::a")).click();

        // Confirmar a janela javascript - Acessar a janela javascript clicando em OK = accept
        navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!",mensagem);

        String screenshotArquivo = "E:\\ProjetosQA\\webdriverJava\\src\\test\\test-report\\taskit\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça - stalenessOf (manipula o webElement e aguardar o elemento sumir do DOM)
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com o texto "Lgout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
        // Fechar o navegador
        navegador.quit();
    }
}
