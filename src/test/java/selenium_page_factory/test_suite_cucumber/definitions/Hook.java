package selenium_page_factory.test_suite_cucumber.definitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    public static WebDriver webDriver;
    public static Wait<WebDriver> wait;

    @Before
    public void beforeScenario() throws IOException {
        System.out.println("beforeScenario");

        String env = System.getProperty("env") == null ? "staging" : System.getProperty("env");
        System.out.println("env: " + env);
        if (!env.equals("staging") && !env.equals("production")) {
            env = "staging";
        }

        String currentWorkingDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory = " + currentWorkingDirectory);
        FileInputStream fileInputStream = new FileInputStream(
                currentWorkingDirectory + "/src/test/resources/selenium_page_factory/" + env + ".properties");
        System.getProperties().load(fileInputStream);
        System.out.println(System.getProperty("browser"));
        System.out.println(System.getProperty("env"));
        System.out.println(System.getProperty("suiteXml"));

        if (System.getProperty("browser").equals("chrome")) {
            WebDriverManager.chromedriver().setup();
        } else {
            throw new IllegalArgumentException("Browser not supported: ");
        }

        Hook.webDriver = new ChromeDriver();
        Hook.wait = new WebDriverWait(Hook.webDriver, Duration.ofSeconds(5));

    }

    @After
    public void afterScenario() {
        System.out.println("afterScenario");
        if (Hook.webDriver != null) {
            Hook.webDriver.close();
        }
    }
}
