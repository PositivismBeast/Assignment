package com.stepDefinitions;

import java.io.IOException;

import assignPages.Assign1Page;
import io.cucumber.java.en.*;

public class AssignStep {
	Assign1Page assign1page;
	
	@Given("User navigate to React JS Site")
	public void userNavigateToReactJSSite() {
		Assign1Page.launch();
		assign1page = new Assign1Page();
	}
	
	@When("Move to headings and  get text dynamically and save it in a file")
	public void moveToHeadingsAndGetTextDynamicallyAndSaveItInAFile() throws InterruptedException, IOException {
		assign1page.getTextDynamically();
		
		   
	}
	
	@Then("Verify scroll functionality in tutorial tab")
	public void verifyScrollFunctionalityInTutorialTab() throws InterruptedException  {
		assign1page.VerifyScroll();
	   
	}
	@Then("Verify the respected contentis Bolded on Right Navigation and Blue color is seen")
	public void verifyTheRespectedContentisBoldedOnRightNavigationAndBlueColorIsSeen() throws InterruptedException {
		assign1page.VerifyScrollHighlight();
	  
	}
	



}
