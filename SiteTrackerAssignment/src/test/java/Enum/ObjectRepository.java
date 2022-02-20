package Enum;

public class ObjectRepository {
	
	public enum Identfier {

		COMPONENT_REFERENCE("xpath#//span[contains(text(), 'Component Reference')]"),
		QUICK_FIND("xpath#//input[@name='Quick Find']"),
		DATATABLE("xpath#//h3[text() = 'Lightning Web Components']/..//span[text() = 'datatable']"),
		EXAMPLE_DROP_TAB("xpath#//li[@title='Example']//a"),
		DROPDOWNVALUE("xpath#//div[@role='listbox']/lightning-base-combobox-item/span[2]"),
		RUNBUTTON("xpath#//button[contains(text(),'Run')]"),
		DATA_TABLE("xpath#//div[@class='slds-scrollable_y']/div/table/tbody");
		

		private final String label;

		Identfier(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

	}


}