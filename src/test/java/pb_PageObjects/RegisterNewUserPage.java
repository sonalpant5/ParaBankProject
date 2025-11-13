package pb_PageObjects;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//Sonal Code
/*
 * This class handles the registration of a new user on the ParaBank application.
 */
public class RegisterNewUserPage {

protected WebDriver driver;

	private By firstName = By.id("customer.firstName");
	private By lastName = By.id("customer.lastName");
	private By address = By.id("customer.address.street");
	private By city = By.id("customer.address.city");
	private By state = By.id("customer.address.state");
	private By zipCode = By.id("customer.address.zipCode");
	private By phoneNumber = By.id("customer.phoneNumber");
	private By ssn = By.id("customer.ssn");
	private By username = By.id("customer.username");
	private By password = By.id("customer.password");
	private By confirmPassword = By.id("repeatedPassword");
	private By registerButton = By.xpath("//input[@value='Register']");
	private By successMsg = By.xpath("//p[contains(text(),'Your account was created successfully')]");


	public RegisterNewUserPage(WebDriver driver) {
		this.driver = driver;
	}

	public void registerNewUser(Map<String,String> userData) {
		driver.findElement(firstName).sendKeys(userData.get("firstName"));
		driver.findElement(lastName).sendKeys(userData.get("lastName"));
		driver.findElement(address).sendKeys(userData.get("address"));
		driver.findElement(city).sendKeys(userData.get("city"));
		driver.findElement(state).sendKeys(userData.get("state"));
		driver.findElement(zipCode).sendKeys(userData.get("zipCode"));
		driver.findElement(phoneNumber).sendKeys(userData.get("phoneNumber"));
		driver.findElement(ssn).sendKeys(userData.get("ssn"));
		driver.findElement(username).sendKeys(userData.get("username"));
		driver.findElement(password).sendKeys(userData.get("password"));
		driver.findElement(confirmPassword).sendKeys(userData.get("confirmPassword"));
		driver.findElement(registerButton).click();
	}

	public boolean confirmRegistrationSuccess() {
		try{
			return(new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(successMsg))
                    .isDisplayed());
		}catch(Exception e) {
            return false;
        }
}
}
