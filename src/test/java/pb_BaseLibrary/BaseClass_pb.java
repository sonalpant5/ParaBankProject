package pb_BaseLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass_pb {

	protected static WebDriver driver;
	protected Properties prop = new Properties();
	private String browser;
	
	@Parameters({"browser"})
	@BeforeClass
	public void setup(String browser) throws Exception {
		readPropertiesFile("pb_global.properties");
		// Add browser launch logic here based on prop values
		this.browser = browser.toLowerCase();

		// Launch Browser based on browser type.
		switch (this.browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;
				
		default:
			throw new IllegalArgumentException("Unsupported browser: " + prop.getProperty("browsertype").toLowerCase());
		}

		// Launch environment specific properties
		switch (prop.getProperty("environment").toLowerCase()) {
		case "qa":
			readPropertiesFile("pb_qa.properties");
			break;
		case "dev":
			readPropertiesFile("pb_dev.properties");
			break;

		default:
			throw new IllegalArgumentException("Unsupported environment: " + prop.getProperty("environment").toLowerCase());
		}

		LaunchApplication(prop.getProperty("url"));
	}

	public void readPropertiesFile(String filename) throws Exception {
		String filepath = System.getProperty("user.dir") + "/config/" + filename;
		File file = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
	}

	public void LaunchApplication(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

	}

	
	@AfterClass
	public void tearDown() throws Exception
	{
		if (driver != null)
		driver.close();
		
	}

}
