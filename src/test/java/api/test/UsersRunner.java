package api.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = "src/test/java/api/test/Users.feature",
            glue = {"api/test"},
            plugin = {"html:reports/ApiReqresUsers.html"},
            monochrome = true
    )

    public class UsersRunner extends AbstractTestNGCucumberTests {
    }

