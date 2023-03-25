package Naukri;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Applied_for_job {
	
	Applied_for_job() throws IOException
	{
		write = new FileWriter("D:/ECLIPSE/My_Project/src/test/java/Naukri/Applied_Jobs"+ date +".txt");
	}
	
	static WebDriver driver;
	static int no;
	FileWriter write;
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
		driver.findElement(By.xpath("//div[@class='nI-gNb-sb__main']")).click();
		no = random.nextInt(1000, Utilities.getTime());
		Thread.sleep(no);

		// entering job name
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(jobName);
		no = random.nextInt(1000, Utilities.getTime());
		Thread.sleep(no);

		// selecting experience
		driver.findElement(By.id("experienceDD")).click();
		no = random.nextInt(1000, Utilities.getTime());
		Thread.sleep(no);

		driver.findElement(By.cssSelector("li[title='Fresher']")).click();
		no = random.nextInt(1000, Utilities.getTime());
		Thread.sleep(no);

		// entering location
		WebElement loc = driver.findElement(By.cssSelector("input[placeholder='Enter location']"));
		loc.sendKeys("Pune");
		no = random.nextInt(1000, Utilities.getTime());
		Thread.sleep(no);

		// click on search button
		driver.findElement(with(By.tagName("span")).toRightOf(loc)).click();
		no = random.nextInt(1000, Utilities.getTime());
		Thread.sleep(no);

		// Applying for jobs
		List<WebElement> low = driver.findElements(By.cssSelector("article[class='jobTuple']"));

		for (int i = 0; i < low.size(); i++) {
			low = driver.findElements(By.cssSelector("article[class='jobTuple']"));

			low.get(i).click();
			no = random.nextInt(1000, Utilities.getTime());
			Thread.sleep(no);

			driver.switchTo().window(Utilities.getChildWindows());

			try {
				if (driver.findElement(By.cssSelector(
						"div[class='apply-button-container'] button[class='waves-effect waves-ripple btn apply-button']"))
						.getText().contains("APPLY"))
				{
					
					driver.findElement(By.xpath("(//button[@class='waves-effect waves-ripple btn apply-button'])[1]")).click();
					no = random.nextInt(1000, Utilities.getTime());
					Thread.sleep(no);

					String appliedJob = driver.findElement(By.xpath("//span[@class='apply-message']//strong")).getText();
					System.out.println(appliedJob);
					
					
					write.write(appliedJob + Keys.ENTER);
					
					driver.switchTo().window(Utilities.getChildWindows()).close();
					no = random.nextInt(1000, Utilities.getTime());
					Thread.sleep(no);
					driver.switchTo().window(Utilities.getParentWindows());
				}
			} catch (Exception e) 
			{
				System.out.println("Should done manualy only...!!");
				driver.switchTo().window(Utilities.getChildWindows()).close();
				no = random.nextInt(1000, Utilities.getTime());
				Thread.sleep(no);
				driver.switchTo().window(Utilities.getParentWindows());
			}
		}
		write.close();
	}
}















