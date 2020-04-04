package resources;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {

    public WebDriver driver;
    public Properties prop;
    public static Logger log = LogManager.getLogger(base.class.getName());

    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/resources/data.properties");

        prop.load(fis);
        String browserName = prop.getProperty("browser");
        log.info("browser Chosen: " + browserName);

        if (browserName.equals("chrome")) {
            // System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            driver = new ChromeDriver();
            //execute in chrome driver

        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
            driver = new FirefoxDriver();

        } else if (browserName.equals("opera")) {
            System.setProperty("webdriver.opera.driver", "/usr/bin/operadriver");
            driver = new OperaDriver();


        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;


    }

    public void getScreenshot(String result) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("src/main/java/screenshots/" + result + "screenshot.png"));

    }

}
