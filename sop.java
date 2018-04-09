package sop;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class sop {
	public static double loadForm() throws InterruptedException {
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("http://dev2013b/QMW/BBS/CDM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		long start = 0, end = 0;
		double diff = 0; 
		
		String oldUrl = driver.getCurrentUrl();
		driver.findElement(By.id("Tile_WPQ2_14_5")).click();//Open DAL Form
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.equals(oldUrl)){
			driver.findElement(By.id("Tile_WPQ2_14_5")).click();
		}
		start = System.nanoTime();
		
		String button = driver.findElement(By.id("FormControl_V1_I1_B35")).getAttribute("value");

		if (button.equals("Submit Request")) {
			end = System.nanoTime();
			Thread.sleep(1000);
			driver.close();
			diff = (end - start)/1000000000.0;
		}

		return diff;
	}
	
	public static double draftConDoc() throws InterruptedException {
		long start = 0, end = 0;
		
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get("http://dev2013b/QMW/BBS/CDM/Draft%20Controlled%20Documents/Forms/NoFolders.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		String numDoc = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = numDoc.split("= ");
		int numOfDocs = Integer.parseInt(data[1]);
		System.out.println("There are:" + numOfDocs + " Documents");
		
		start = System.nanoTime();//Start clock
		for (int i = 7; i < 21; i++) {
			if (i == 11) {
				continue;
			}
			driver.get("http://dev2013b/QMW/BBS/CDM/_layouts/15/NintexWorkflow/StartWorkflow.aspx?List={9ada4fec-68a9-4c12-80c4-d12a17337f5d}&ID=" + i + "&ItemGuid={C51A23EE-2EEF-420A-97B5-E8A98F7AD9C7}&TemplateID={55e54dee-c0f9-4355-bf78-30a9e7ea0ce2}&Source=http%3A%2F%2Fdev2013b%2FQMW%2FBBS%2FCDM%2FDraft%2520Controlled%2520Documents%2FForms%2FNoFolders%2Easpx");
//			String button = driver.findElement(By.id("ctl00_PlaceHolderMain_btnOk")).getAttribute("value");
//			System.out.println(i);
//			System.out.println(button);
			driver.findElement(By.id("ctl00_PlaceHolderMain_btnOk")).click();
			System.out.println(i);
		}
		end = System.nanoTime();
		return ((end - start)/1000000000.0);
		
	}
	public static void main(String[] args) throws InterruptedException {
		//double time = loadForm();
		//System.out.println("Time to load DAL form: " + time + "s");
		Thread.sleep(1500);
		double pdfTime = draftConDoc();
		System.out.println(pdfTime + "s");
	}

}
