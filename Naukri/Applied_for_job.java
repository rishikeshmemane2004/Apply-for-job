package Naukri;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Applied_for_job {
	
	
	static WebDriver driver;
	static int no;
	
	// creating random time for heating user action
	static Random random = new Random();
	static LocalDate date = LocalDate.now();
	
	
	@BeforeMethod
	public void setUp() 
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://www.naukri.com/");
	}

	@AfterMethod
	public void tearDown() 
	{
		driver.quit();
	}

	
	@Test(dataProvider = "getData", dataProviderClass = DataSet.class)
	public void applyForJob(String jobName) throws InterruptedException, IOException 
	{

		// login to naukari
		Utilities.getLogin("bobades340@gmail.com", "Swapnil");

		// For Which job
		System.out.println(jobName);

		// click on search jobs
		Utilities.clickOnSearchJob();

		// entering job name
		Utilities.enterJobName(jobName);

		// selecting experience
		Utilities.selectExperience();

		//click on Fresher
		Utilities.clickOnFresher();

		// entering location
		Utilities.enterLocation("Mumbai");

		// click on search button
		Utilities.clickOnSearchButton();

		// Applying for jobs
		Utilities.Applying();
		
	}
}















