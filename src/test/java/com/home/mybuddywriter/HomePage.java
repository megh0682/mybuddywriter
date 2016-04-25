package com.home.mybuddywriter;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
import org.openqa.selenium.support.FindBy;
 
import org.openqa.selenium.support.PageFactory;
 
public class HomePage {

	WebDriver driver;
	 
    @FindBy(xpath="//body/div/div/div/h1")
 
    WebElement homePageTitle;
 
	  @FindBy(linkText="Log Out")
	 
	  WebElement logoutlnk;
	  
	  
 
    public HomePage(WebDriver driver){
 
        this.driver = driver;
 
        //This initElements method will create all WebElements
 
        PageFactory.initElements(driver, this);
 
    }
 
     
 
    //Get the title of Home Page
 
        public String getHomePageTitleText(){
        	String txt;
        if(isElementExisting(homePageTitle)){
        	txt =homePageTitle.getText();
         }else{
        	System.out.println("Webelement logoutlnk not found");
        	txt="Null";
         }
         
         return txt;
 
        }
        

    //Get the title of Home Page
     
        public void clicklogout(){
        
        if(isElementExisting(logoutlnk)){
        	logoutlnk.click();
        }else{
            System.out.println("Webelement logoutlnk not found");
        }
         
        }
        
    // web element existence validity
        
        public boolean isElementExisting(WebElement we) {
            try {
                we.isDisplayed();
                return true;
            } catch(NoSuchElementException e) {
            	System.out.println("Element does not exist.");
                return false;
            }
        }
	
}