package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Login {
	
	private static final String URL_APP = "http://localhost:8080/AppWebEgunonEuskadi/";

	public Login() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void loginOK() {
		
		WebDriver driver = new HtmlUnitDriver();
	    	    
	    driver.get(URL_APP+ "/login");
	    assertEquals("No coincide Title" ,driver.getTitle(), "Logeo");
	    
	    // Find the text input element by its name
        WebElement elementName = driver.findElement(By.name("user_login"));
        elementName.sendKeys("Ander");

        WebElement elementPass = driver.findElement(By.name("pass_login"));
        elementPass.sendKeys("22741215B");
        
        // Now submit the form. WebDriver will find the form for us from the element
        elementPass.submit();
        
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        //buscar Div donde se muestra los datos del usuario Loegado
        driver.findElement(By.id("user_login"));
        
        
	    driver.quit();		
	}

	
	@Test
	public void loginError() {
		WebDriver driver = new HtmlUnitDriver();
	    
	    driver.get(URL_APP+ "/login");
	    assertEquals("No coincide Title" ,driver.getTitle(), "Logeo");
	    
	    // Find the text input element by its name
        WebElement elementName = driver.findElement(By.name("user_login"));
        elementName.sendKeys("xxxxx");

        WebElement elementPass = driver.findElement(By.name("pass_login"));
        elementPass.sendKeys("11741215B");
        
        // Now submit the form. WebDriver will find the form for us from the element
        elementPass.submit();       
        
        
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        //buscar Div donde se muestra los mensajes
        WebElement elementMsgs = driver.findElement(By.id("msg"));
        
        assertEquals("No el mensaje de error " , elementMsgs.getText(), "Por favor comprueba tu usuario y pass, no los has escrito bien.");
       
        
	    driver.quit();		
	}
	
}
