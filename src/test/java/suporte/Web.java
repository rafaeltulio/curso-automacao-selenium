package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String USERNAME = "rafaeltulio1";
    public static final String AUTOMATE_KEY = "EY22vFYyazM2HSDH6fMo";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        // PROPRIEDADE PARA DIRECIONAR O DRIVER
        System.setProperty("webdriver.chrome.driver","E:\\ProjetosQA\\webdriverJava\\src\\test\\drivers\\chromedriver.exe");
        // variavel navegador ira ter uma instância do Chrome driver e maximizando a janela
        WebDriver navegador = new ChromeDriver();

        // Esperar por 5 segundos que os elementos possam aparecer.
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();

        // Navegando para a página do Task
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
    // Executar Testes Remotamente
    public static WebDriver createBrowserStack()  {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "62.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x800");
                caps.setCapability("browserstack.debug","true");

        WebDriver navegador = null;
        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Navegando para a página do Taskit
            navegador.get("http://www.juliodelima.com.br/taskit");
        } catch (MalformedURLException e) {
            System.out.println("Houveram problemas com a URL: " +e.getMessage());
        }
        return navegador;
    }

}
