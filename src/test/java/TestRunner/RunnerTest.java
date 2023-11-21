package TestRunner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature/tableBrowser.feature", 
glue = "Code")
	public class RunnerTest extends AbstractTestNGCucumberTests {



}

