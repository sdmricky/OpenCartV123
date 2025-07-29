package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

public static WebDriver driver;
public Logger logger;
public Properties p;

	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			switch(br.toLowerCase())
			{
				case "chrome" : cap.setBrowserName("chrome"); break;
				case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
				default : System.out.println("No matching browser"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		}
			
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome" : driver = new ChromeDriver(); break;
				case "edge" : driver = new EdgeDriver(); break;
				case "firefox" : driver = new FirefoxDriver(); break;
				default : System.out.println("Invalid browser"); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get(p.getProperty("appURL"));      //reading URL from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric()
	{
		String randomStr = RandomStringUtils.randomAlphabetic(5);
		String randomNbr = RandomStringUtils.randomNumeric(5);
		return (randomStr + randomNbr);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilepath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilepath;
	}
}
