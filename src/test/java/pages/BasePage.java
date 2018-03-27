package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver navegador;

    public BasePage(WebDriver navegador){
        this.navegador = navegador;
    }

    public String capturarTextoToast(){
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        return mensagemPop.getText();
    }
}
