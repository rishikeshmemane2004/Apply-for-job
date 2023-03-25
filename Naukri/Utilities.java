package Naukri;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Utilities extends Applied_for_job{

	static WebElement loc;
	static FileWriter write;
	
	Utilities() throws IOException 
	{
		super();
		write = new FileWriter("D:/ECLIPSE/My_Project/src/test/java/Naukri/Applied_Jobs"+ date +".txt");
	}

	public static String getParentWindows() {
		Set<String> windHandles = driver.getWindowHandles();
		Iterator<String> windIds = windHandles.iterator();
		String pWindId = windIds.next();

		return pWindId;
	}

	public static String getChildWindows() {
		Set<String> windHandles = driver.getWindowHandles();
		Iterator<String> windIds = windHandles.iterator();
		String pWindId = windIds.next();
		String cWindId = windIds.next();

		return cWindId;
	}

	public static void getLogin(String email, String password) throws InterruptedException {
		// click on login button
		driver.findElement(By.linkText("Login")).click();
		no = random.nextInt(1000, getTime());
		Thread.sleep(no);

		// entering an email
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(email);
		no = random.nextInt(1000, getTime());
		Thread.sleep(no);

		// entering password
		driver.findElement(By.cssSelector("input[placeholder='Enter your password']")).sendKeys(password);
		no = random.nextInt(1000, getTime());
		Thread.sleep(no);

		// click on login button
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		no = random.nextInt(1000, getTime());
		Thread.sleep(no);
	}

	public static int getTime() {
		 no = random.nextInt(8900, 9800);
		return no;
	}

	public static void sleep(int no) throws InterruptedException
	{
		Thread.sleep(no);
	}
	
	public static void clickOnSearchJob() throws InterruptedException
	{
		driver.findElement(By.xpath("(//div[@class='nI-gNb-sb__main'])[1]")).click();
		no = random.nextInt(1000, Utilities.getTime());
		Utilities.sleep(no);
	}
	
	public static void enterJobName(String jobName) throws InterruptedException
	{
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(jobName);
		no = random.nextInt(1000, Utilities.getTime());
		Utilities.sleep(no);
	}
	
	public static void selectExperience() throws InterruptedException
	{
		driver.findElement(By.id("experienceDD")).click();
		no = random.nextInt(1000, Utilities.getTime());
		Utilities.sleep(no);
	}
	
	public static void clickOnFresher() throws InterruptedException
	{
		driver.findElement(By.cssSelector("li[title='Fresher']")).click();
		no = random.nextInt(1000, Utilities.getTime());
		Utilities.sleep(no);
	}
	
	public static void enterLocation(String location) throws InterruptedException
	{
		WebElement loc = driver.findElement(By.cssSelector("input[placeholder='Enter location']"));
		loc.sendKeys(location);
		no = random.nextInt(1000, Utilities.getTime());
		Utilities.sleep(no);
	}
	
	public static void clickOnSearchButton() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[normalize-space()='Search']")).click();
		no = random.nextInt(1000, Utilities.getTime());
		Utilities.sleep(no);
	}
	
	public static void Applying() throws InterruptedException, IOException
	{
		List<WebElement> low = driver.findElements(By.cssSelector("article[class='jobTuple']"));

		for (int i = 0; i < low.size(); i++) {
			low = driver.findElements(By.cssSelector("article[class='jobTuple']"));

			low.get(i).click();
			no = random.nextInt(1000, Utilities.getTime());
			Utilities.sleep(no);

			driver.switchTo().window(Utilities.getChildWindows());

			try {
				if (driver.findElement(By.cssSelector(
						"div[class='apply-button-container'] button[class='waves-effect waves-ripple btn apply-button']"))
						.getText().contains("APPLY"))
				{
					
					driver.findElement(By.xpath("(//button[@class='waves-effect waves-ripple btn apply-button'])[1]")).click();
					no = random.nextInt(1000, Utilities.getTime());
					Utilities.sleep(no);

					String appliedJob = driver.findElement(By.xpath("//span[@class='apply-message']//strong")).getText();
					System.out.println(appliedJob);
					
					
					write.write(appliedJob + Keys.ENTER);
					
					driver.switchTo().window(Utilities.getChildWindows()).close();
					no = random.nextInt(1000, Utilities.getTime());
					Utilities.sleep(no);
					driver.switchTo().window(Utilities.getParentWindows());
				}
			} catch (Exception e) 
			{
				System.out.println("Should done manualy only...!!");
				driver.switchTo().window(Utilities.getChildWindows()).close();
				no = random.nextInt(1000, Utilities.getTime());
				Utilities.sleep(no);
				driver.switchTo().window(Utilities.getParentWindows());
			}
		}
		write.close();
	}
	
	
	
	
	
	

}
