package Code;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONObject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.cucumber.messages.types.TestStep;
import utils.ExtentReportUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
public class mainCode {

	private static WebDriver driver;


	 String browserDetails;
	 String url;
	 String chromeeDriverPath;
	 String msEdgeDriverPath;
	 ExtentReportUtils extentReportUtils;


	    public static WebDriver getDriver() {
	        return driver;
	    }
	    @BeforeTest
	    public void setUp() {
	        extentReportUtils = new ExtentReportUtils();
	        ExtentReportUtils.initializeExtentReports();
	        ExtentReportUtils.startTest("Your");
	    }
	    
	public void propertiesFileRead() {
		System.out.println("------------------------Given User details read from propertiesfile--------------------");
		System.out.println("entering into propertiesFileRead");

		Properties prop = new Properties();
		try {
			//load a properties file from class path, inside static method
			prop.load(new FileInputStream("src\\test\\resources\\resource\\userDetails.properties"));
			
			//get the property value and print it out
			//printing browser details
			browserDetails = prop.getProperty("browser");
			System.out.println(browserDetails);
			url = prop.getProperty("url");
			
			//printing edge driver path
			msEdgeDriverPath = prop.getProperty("msEdgeDriverPath");

			
			//printing chrome driver path
			chromeeDriverPath = prop.getProperty("chromeDriverPath");


		} 
		catch (IOException ex) {
			System.err.print("failing inside propertiesFileRead");
			ex.printStackTrace();
		}
		System.out.println("exiting into propertiesFileRead");
	}
	@Test
	public void startDriver() {
	    System.out.println("--------------Given Verify User Login to Dynamic table Page------------");
	    System.out.println("entering startDriver()..." + browserDetails);
	    try {

	        if (browserDetails.equals("chrome")) {
	            System.getProperty("webdriver.chrome.driver", chromeeDriverPath);
	            driver = new ChromeDriver();
	            ChromeOptions co = new ChromeOptions();
	            co.addArguments("disable-infobars");
	        } else if (browserDetails.equals("edge")) {
	            System.out.println(browserDetails);
	            System.getProperty("webdriver.edge.driver", msEdgeDriverPath);
	            driver = new EdgeDriver();
	            EdgeOptions edge = new EdgeOptions();
	            edge.addArguments("--disable-notifications");
	        }
	       

	        driver.manage().window().maximize();
	        driver.get(url);
	        System.out.println("driver is in " + driver.getCurrentUrl());
		//verifying the url link
		if(driver.getCurrentUrl().contains("https://testpages.herokuapp.com/styled/tag/dynamic-table.html")) {
		System.out.println("Verified user is in right url i.e. "+driver.getCurrentUrl());
		}
		else {
			System.err.println("failed on validating table url");
		}
		System.out.println("exiting startDriver()...");

		 ExtentReportUtils.logInfo("Starting the browser and navigating to the URL");
         ExtentReportUtils.attachScreenshot();
		}catch(Exception e) {
			System.err.print("failing inside startDriver()");
			e.printStackTrace();
		}
	}
	
	public void clickOnTableData() {
		System.out.println("entering into clickOnTableData()..");
		try {
			driver.findElement(By.xpath("//*[text()='Table Data']")).click();
			 ExtentReportUtils.logInfo("Click on the Table Data button");
	         ExtentReportUtils.attachScreenshot();
		}catch(Exception e) {
			System.err.print(e.getMessage()+" failing inside clickOnTableData()");
			e.printStackTrace();
		}
	}
	String jsonContent;
	public void retreiveFromJson() throws IOException {
		System.out.println("entering into retreiveFromJson()..");
        String jsonFileName = "./src/test/resources/resource/datafile.json";
        // load the JSON file from the in class loader
         jsonContent = readJsonFile(jsonFileName);



	}
	private static String readJsonFile(String filePath) {
        try {
            // read file content as a string
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	public void addKeysFromJson() {
		System.out.println("entering into addKeysFromJson()..");
		try {
			retreiveFromJson();
	        System.out.println(jsonContent);
	        
			driver.findElement(By.xpath("//textarea[@id='jsondata']")).clear();

			driver.findElement(By.xpath("//textarea[@id='jsondata']")).sendKeys("["+jsonContent);
			 ExtentReportUtils.logInfo("Send the json details");
	         ExtentReportUtils.attachScreenshot();
			
		}catch(Exception e) {
			System.err.print(e.getMessage()+" failing inside addKeysFromJson()");
			e.printStackTrace();
		}
	}
	public void clickOnRefreshButton() {
		try {
			System.out.println("entering into clickOnRefreshButton()..");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@id='refreshtable']")).click();
			 ExtentReportUtils.logInfo("Click on Refresh button");
	         ExtentReportUtils.attachScreenshot();
		}catch(Exception e) {
			System.err.print(e.getMessage()+" failing inside clickOnRefreshButton()");
			e.printStackTrace();
		}
	}
	public void validateResults() {
		try {
			System.out.println("entering into validateResults()..");
			//getting all value and matching it with the data from json
	        String valueFromUI = "";
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='dynamictable']/child::tr/td[1]"));
			if(rows.size()>0) {
				for(WebElement allRow : rows) {
					System.out.println("having name "+allRow.getText());
					valueFromUI += allRow.getText()+" ";
				}
			}
			System.out.println("final names from ui are "+valueFromUI);

			String[] namesFromUI = valueFromUI.split("\\s+");

		    // using a Set to check for duplicates
            Set<String> uniqueNames = new HashSet<String>();
            boolean hasDuplicates = false;

            for (String name : namesFromUI) {
                if (!uniqueNames.add(name)) {
                    // Duplicate found
                    hasDuplicates = true;
                    break;
                }
            }

            if (hasDuplicates) {
                // split the JSON into individual JSON objects
                String[] jsonObjects = jsonContent.split("\\},\\s*");
                // create a new JSONArray to store the updated objects
                JSONArray jsonArray = new JSONArray();
                // split the valueFromUI into individual names like bob george
                String[] namesToCompare = valueFromUI.split("\\s+");

                Set<String> uniqueNames1 = new HashSet<String>();

                // iterate over each object and remove duplicate names from ValueFromUI
                for (String jsonObject : jsonObjects) {
                    if (!jsonObject.endsWith("}")) {
                        jsonObject += "}";
                    }
                    JSONObject obj = new JSONObject(jsonObject);

                    // check the name and add to the array if it's not a duplicate
                    String name = obj.getString("name");
                    if (uniqueNames1.add(name)) {
                        jsonArray.put(obj);
                    }
                }

                
                String finalValue = jsonArray.toString();
                System.out.println(finalValue);

                
                driver.findElement(By.xpath("//textarea[@id='jsondata']")).clear();
                driver.findElement(By.xpath("//textarea[@id='jsondata']")).sendKeys(finalValue);
   			 ExtentReportUtils.logInfo("Duplicate value exist so modifying");
	         ExtentReportUtils.attachScreenshot();
                clickOnRefreshButton();
            } else {
                System.out.println("Right - No duplicate names");
            }
			
			
			
		}catch(Exception e) {
			System.err.print(e.getMessage()+" failing inside validateResults()");
			e.printStackTrace();
		}
	}
    
    @AfterTest
    public void tearDown() {
        ExtentReportUtils.logInfo("Finishing yourTest...");
        ExtentReportUtils.attachScreenshot(); // Attach a final screenshot

        ExtentReportUtils.logInfo("Closing Extent Report...");
        ExtentReportUtils.flushReport(); // Flush and close the report

        // Close the browser
        closeBrowser();
    }


	
	
	public void closeBrowser() {
		System.out.println("closing browser");
		driver.quit();
	}
	


}
