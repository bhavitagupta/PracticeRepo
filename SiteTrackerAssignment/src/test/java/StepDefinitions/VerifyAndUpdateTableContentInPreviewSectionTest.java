package StepDefinitions;

import io.cucumber.java.en.*;
import Enum.ObjectRepository.Identfier;
import Selenium.SeleniumHelper;
import io.cucumber.datatable.DataTable;

public class VerifyAndUpdateTableContentInPreviewSectionTest extends SeleniumHelper {

	@Given("the browser and application URL")
	public void the_browser_and_application_url() {
		goToGivenUrl("https://developer.salesforce.com/docs/component-library/documentation/en/48.0/lwc");
	}

	@And("click on Tab {string}")
	public void click_on_tab(String string) throws InterruptedException {
	    clickOnGivenTab(Identfier.COMPONENT_REFERENCE.getLabel());
	}

	@When("in quickfind user search {string}")
	public void in_quickfind_user_search(String string) {
		enterAndSelectTheDataTable(Identfier.QUICK_FIND.getLabel(), Identfier.DATATABLE.getLabel(), string);
	}

	@And("select {string} from the dropdown")
	public void select_from_the_dropdown(String string) {
		selectFromExampleDropdown(Identfier.EXAMPLE_DROP_TAB.getLabel(), Identfier.DROPDOWNVALUE.getLabel(), string);
	}

	@And("click on the run button")
	public void click_on_the_run_button() throws InterruptedException {
		clickOnGivenTab(Identfier.RUNBUTTON.getLabel());
	}

	@And("under the section {string} update the table values for all colums in row {int}")
	public void under_the_section_update_the_table_values_for_all_colums_in_row(String string, Integer int1, DataTable dataTable) {
	    updateTableValuesOfPreview(Identfier.DATA_TABLE.getLabel(), int1, dataTable);
	}

	@Then("validate the table is updated for given row {int}")
	public void validate_the_table_is_updated_for_given_row(Integer int1, DataTable dataTable) {
		validateDataOfthePreviewTable(Identfier.DATA_TABLE.getLabel(), dataTable, int1);
	}
}
