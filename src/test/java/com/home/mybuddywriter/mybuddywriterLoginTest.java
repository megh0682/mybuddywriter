package com.home.mybuddywriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.After;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
 
public class mybuddywriterLoginTest {
	
	
	String fn;
	String browser;
	String username;
	String password;
	
	WebDriver driver;
	LoginPage objLogin;
	HomePage objHomePage;
		
	
    public mybuddywriterLoginTest(String fn,String browser, String username,String password){
	    	this.fn = fn;
	    	this.browser = browser;
	    	this.username = username;
	    	this.password = password;
	   }
	
	 @Rule 
	    public TestRule watcher = new TestWatcher() {
	    	   protected void starting(Description description) {
	    		  System.out.println(description.getMethodName()+"being run...");
	    	   }
	    	   @Override
	    	      protected void succeeded(Description description) {
	    		   System.out.println(description + " " + "success!\n");
	    	         }
	    	   @Override
	    	      protected void failed(Throwable e, Description description) {
	    		   System.out.println(description + "failed!\n");
	    	      }
	    	};
	 @Rule
	    public ErrorCollector collector = new ErrorCollector();
	
	
//Parameterization 	    
	
	@Parameterized.Parameters(name = "{index}: browser-{1} username-{2} password-{3} firstname-{0}")
	public static Collection logintestParameters() throws IOException{
		//Readtestdata datatable = new Readtestdata("/testdata/testdata.xlsx");
		Object[][] data = mybuddywriterLoginTest.getData("logintest", "/testdata/testdata.xlsx");
		System.out.println( Arrays.asList(data));
        return Arrays.asList(data);
			
/*		return Arrays.asList(new Object[][]{
				{"Ved","ff","ved","123456"},
				{"Megha","chrome","megha","123456"},
				{"Sahil","ie","sahil","password"},
			    			
		} );*/
	}
	
/*Before every test method setup function will be called
 * @Before annotation
 */
	
	
	@Before
	public void setup(){
		
		//check the browser
		
		switch (browser) {
        case "chrome" : 
       	 System.setProperty("webdriver.chrome.driver", "/Drivers/chromedriver.exe");
		     driver = new ChromeDriver();
		     break;
        case "ie" : 
       	 System.setProperty("webdriver.ie.driver", "/Drivers/IEDriverServer.exe");
  		     driver = new InternetExplorerDriver();
            break;
        case "ff" :  
       	 driver = new FirefoxDriver();
            break;
        default:
       	 driver = new FirefoxDriver();
            break;
        }
			 
		
	}
		
	/**
	 
	     * This test will go to localhost:8080/mybuddywriter
	 
	     * Verify login page title as "Login Page"
	 
	     * Login to application with valid username and password
	 
	     * Verify if the user lands on the home page by verifying the homepage title text as "Hello {firstname of the user}"
	     
	    
	     */
	
	@Test	 
	 public void verify_valid_login_logout() throws InterruptedException{
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 driver.get("http://localhost:8080/mybuddywriter/");
		 	 
	    //Create Login Page object
	 
	    objLogin = new LoginPage(driver);
	 
	    //Verify login page title
	 
	    String loginPageTitle = objLogin.getLoginTitle();
	    
	    //System.out.println(loginPageTitle);
	    
	    //homepae text
	    
	    String exptext = "hello" + " " + fn.toLowerCase();
	    
	    try{
	    	Assert.assertTrue(loginPageTitle.toLowerCase().contains("login page"));
	    } catch (Throwable t){
	    	collector.addError(t);
	    }   
	    
	 
	    //login to application
	 
	    objLogin.loginTomybuddywriter(username, password);
	    
	    //wait for homepage to load
	    
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 
	    // go the next page
	 
	    objHomePage = new HomePage(driver);
	 
	    //Verify home page
	    String actText =  objHomePage.getHomePageTitleText();
	    
	    System.out.println(actText);
	    System.out.println(exptext);
	    
	    try{
	    	Assert.assertTrue(actText.toLowerCase().contains(exptext));
	    } catch (Throwable t){
	    	collector.addError(t);
	    }   
	    	    
	    //logout
	    objHomePage.clicklogout();
	    
	   //wait for login page to load
	    
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    //Verify login page title after logging out successfully
		 
	    loginPageTitle = objLogin.getLoginTitle();
	    
	    //System.out.println(loginPageTitle);
	 
	    try{
	    	Assert.assertTrue(loginPageTitle.toLowerCase().contains("login page"));
	    } catch (Throwable t){
	    	collector.addError(t);
	    }   
	    
	    
	    
	 
	    }
	
	
	//Function to fetch excel test data in junit parameterization annotation
    private static Object[][] getData(String sheetname, String path)throws IOException {
	     
    	Readtestdata datatable = new Readtestdata(path);
    	Integer rowCount = datatable.getRowCount(sheetname);
    	Integer colCount = datatable.getColumnCount(sheetname);
    	Object[][] data = new Object[rowCount-1][colCount];
    	
    	if(datatable.isSheetExist(sheetname)){
    		
    		 for(int row=0; row<rowCount; row++){
  				for(int col=0 ;col< colCount; col++){
  					if(row!=0){
  					data[row-1][col] = datatable.getCellData(sheetname,col, (row+1));
  					System.out.println(datatable.getCellData(sheetname,col,(row+1)));
  					}
  					
  				}
  		   }
    		 
    	}else{
    		System.out.println(sheetname + " does not exist.");
    	}
    	
        
        return data;
    }

    @After
	public void teardown(){
     driver.close();    	    	
    }

    
    
	
	

}


