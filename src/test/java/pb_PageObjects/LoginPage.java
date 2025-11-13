package pb_PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	protected WebDriver driver;
	private By username = By.name("username");
	private By password = By.name("password");
	private By loginButton = By.xpath("//input[@value='Log In']");
	private By accountOverview = By.xpath("//h1[normalize-space(text())='Accounts Overview']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;

	}

	public void enterLoginDetails(String username, String password) {
		driver.findElement(this.username).sendKeys(username);
		driver.findElement(this.password).sendKeys(password);
		driver.findElement(loginButton).click();
	}
	
	
	public boolean isLoginSuccesful() {
		
		try{
			return(new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(accountOverview))
                    .isDisplayed());
		}catch(Exception e) {
            return false;
        }
        
	}
}
