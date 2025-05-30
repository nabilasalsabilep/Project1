package cucumber.runners;

import org.testng.annotations.AfterSuite;

import cucumber.helper.GenerateReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "cucumber.definitions",
    plugin = {"pretty",                                   // Pretty console output
                "html:target/cucumber-report.html",          // HTML report
                "json:target/cucumber-report.json",          // JSON report
                "junit:target/cucumber-report.xml"           // JUnit XML report
            },
    monochrome = true 
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
        System.out.println("generate report");
    }
}