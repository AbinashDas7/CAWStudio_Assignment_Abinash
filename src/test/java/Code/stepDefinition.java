package Code;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class stepDefinition {

	 private mainCode mainInstance = new mainCode();
	@Given("Data retreived from jsonFile")
	public void Data_retreived_from_jsonFile() {
		System.out.println("json file reading");
		mainInstance.setUp();
		mainInstance.propertiesFileRead();
	}
	@When("User Login to Dynamic table Page")
	public void User_Login_to_Dynamic_table_Page() {
		mainInstance.startDriver();
	}
	
	@And("User able to click on Table Data button")
	public void User_ableto_click_on_Table_Data_button() {
		mainInstance.clickOnTableData();
	}
	
	@Then("Add details from json file")
	public void Then_Add_details_json_file() {
		mainInstance.addKeysFromJson();
	}
	
	@And("Click on Refresh Table button")
	public void Click_on_Refresh_Table_button() {
		mainInstance.clickOnRefreshButton();
	}
	
	@Then("Validate the Table for copied user")
	public void Validate_the_Table_for_copied_user() {
		mainInstance.validateResults();
	}
}
