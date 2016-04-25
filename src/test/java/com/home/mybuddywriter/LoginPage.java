package com.home.mybuddywriter;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	/**
	 
     * All WebElements are identified by @FindBy annotation
 
     */
		
    WebDriver driver;
  
    @FindBy(name="username")
    WebElement uname;
    
    @FindBy(name="userpwd")
    WebElement pwd;
  
    @FindBy(id="savename")
    WebElement loginbtn;
  
    @FindBy(id="gotoregister")
    WebElement registerbtn;
    
   // @FindBy(id="pagetitle")
  //WebElement logintitle;
    
    //constructor for LoginPage class
    
   public LoginPage(WebDriver driver){
    	 
        this.driver = driver;
 
        //This initElements method will create all WebElements
 
        PageFactory.initElements(driver, this);
 
    }
    
  //Set user name in textbox
    
   public void setUserName(String strusername){
	   if(isElementExisting(uname)){
        uname.sendKeys(strusername);
	   }else{
		System.out.println("Webelement uname not found");
	   }
    }
    
  //Set password in textbox
    
    public void setPassword(String strpassword){
    	if(isElementExisting(pwd)){
    		pwd.sendKeys(strpassword);
    	}else{
    		System.out.println("Webelement pwd not found");
 	   }
        
    }
    
  //click on login button
    
    public void clickLogin(){
    	if(isElementExisting(loginbtn)){
    		loginbtn.click();
    	}else{
    		System.out.println("Webelement loginbtn not found");
 	   }
    	
    }
    
  //click on register button
    
    public void clickRegister(){
    	if(isElementExisting(registerbtn)){
    		registerbtn.click();
    	}else{
    		System.out.println("Webelement registerbtn not found");
 	   }
    	
    }
    
  //Get the title of Login Page
    
    public String getLoginTitle(){
    	String txt;
    	txt= driver.getTitle();
        return   txt;
 
    }
    
    /*
     * Validating the existence of the web elements on the page.
     */
    
    public boolean isElementExisting(WebElement we) {
        try {
            we.isDisplayed();
            return true;
        } catch(NoSuchElementException e) {
        	System.out.println("Element does not exist.");
            return false;
        }
    }
    
    /**
    
     * This POM methods will be exposed in test case to login in the application
 
     * @param strusername
 
     * @param strpassword
 
     * @return
 
     */
 
    public void loginTomybuddywriter(String strusername,String strpassword){
 
        //Fill user name
 
        this.setUserName(strusername);
 
        //Fill password
 
        this.setPassword(strpassword);
 
        //Click Login button
 
        this.clickLogin();
 
                 
 
    }
    
    public void gotoregisterpage(){
    	 
       //Click Login button
 
        this.clickRegister();
 
    }
    
   

}