package Naukri;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;

public class Utilities extends Applied_for_job{


	Utilities() throws IOException {
		super();
		// TODO Auto-generated constructor stub
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


}
