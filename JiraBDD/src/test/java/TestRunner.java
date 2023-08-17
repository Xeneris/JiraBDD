import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"StepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-reports.html"},
        monochrome = true
)
public class TestRunner {
}