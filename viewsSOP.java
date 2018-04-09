package libTiming;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class viewsSOP {
	
	public static double changeViewDCD(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/Draft%20Controlled%20Documents/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		Thread.sleep(1000);
		driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt1")).click();
		
		start = System.nanoTime();
		Thread.sleep(500);
		while(true) {
			try {
				String url = driver.getCurrentUrl();
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();
				if ((view.equals("1 - 30") || view.equals("1 - 100")) && (!(oldUrl.equals(url)))) {
					end = System.nanoTime();
					double diff = ((end - start)/1000000000.0);
					Thread.sleep(1000);
					driver.close();
					return diff;
				}	
			}
			catch(Throwable e) {		
			}
		}		
	}
	
	public static double changeViewECD(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/Effective%20Controlled%20Documents/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		Thread.sleep(1000);
		driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt1")).click();
		
		start = System.nanoTime();
		Thread.sleep(500);
		while(true) {
			try {
				String url = driver.getCurrentUrl();
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();
				if ((view.equals("1 - 30") || view.equals("1 - 100")) && (!(oldUrl.equals(url)))) {
					end = System.nanoTime();
					double diff = ((end - start)/1000000000.0);
					Thread.sleep(1000);
					driver.close();
					return diff;
				}	
			}
			catch(Throwable e) {		
			}
		}		
	}
	
	public static double changeViewDAL(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/Document%20Activity%20Logs/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		Thread.sleep(1000);
		driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt0")).click();
		
		start = System.nanoTime();
		Thread.sleep(500);
		while(true) {
			try {
				String url = driver.getCurrentUrl();
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();
				if ((view.equals("1 - 30") || view.equals("1 - 100")) && (!(oldUrl.equals(url)))) {
					end = System.nanoTime();
					double diff = ((end - start)/1000000000.0);
					Thread.sleep(1000);
					driver.close();
					return diff;
				}	
			}
			catch(Throwable e) {		
			}
		}		
	}
	
	public static double filterTimeDCD(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get(args[0] + "/CDM/Draft%20Controlled%20Documents/Forms/GroupedbyDepartment.aspx");	
		Thread.sleep(2500);
		start = System.nanoTime();
		driver.get(args[0] + "/CDM/Draft%20Controlled%20Documents/Forms/SB.aspx?Filter=1&View={64EF939D-F6A6-432E-9741-BB6167FF8646}");
		

		while(true) {
			try {
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();
				if ((view.equals("1 - 30") || view.equals("1 - 100"))) {
					end = System.nanoTime();
					double diff = ((end - start)/1000000000.0);
					Thread.sleep(1000);
					driver.close();
					return diff;
				}	
			}
			catch(Throwable e) {		
			}
		}	
		
	}
	
	public static double filterTimeECD(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get(args[0] + "/CDM/Effective%20Controlled%20Documents/Forms/GroupedbyDepartment.aspx");	
		Thread.sleep(2500);
		start = System.nanoTime();
		driver.get(args[0] + "/CDM/Effective%20Controlled%20Documents/Forms/SB.aspx?Filter=1&View={878C4626-AC20-4BC9-9B1D-C847163A887A}");
		

		while(true) {
			try {
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();
				if ((view.equals("1 - 30") || view.equals("1 - 100"))) {
					end = System.nanoTime();
					double diff = ((end - start)/1000000000.0);
					Thread.sleep(1000);
					driver.close();
					return diff;
				}	
			}
			catch(Throwable e) {		
			}
		}	
		
	}
	
	public static double filterTimeDAL(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get(args[0] + "/CDM/Document%20Activity%20Logs/Forms/GroupedByDocumentNumber.aspx");	
		Thread.sleep(2500);
		start = System.nanoTime();
		driver.get("");//upload documents and put link
		

		while(true) {
			try {
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();
				if ((view.equals("1 - 30") || view.equals("1 - 100"))) {
					end = System.nanoTime();
					double diff = ((end - start)/1000000000.0);
					Thread.sleep(1000);
					driver.close();
					return diff;
				}	
			}
			catch(Throwable e) {		
			}
		}	
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		double[] time = new double[3];
		double[] filterTime = new double[3];
		time[0] = changeViewDCD(args);
		time[1] = changeViewECD(args);
		time[2] = changeViewDAL(args);
		
		filterTime[0] = filterTimeDCD(args);
		filterTime[1] = filterTimeECD(args);
		//filterTime[2] = filterTimeDAL();
		
		System.out.println("Draft Controlled Document view change time: " + time[0] + "s");
		System.out.println("Effective Controlled Document view change time: " + time[1] + "s");
		System.out.println("Document Activity Log view change time: " + time[2] + "s");

		System.out.println("Draft Controlled Document filter change time: " + filterTime[0] + "s");
		System.out.println("Effective Controlled Document filter change time: " + filterTime[1] + "s");
		//System.out.println("Document Activity Log filter change time: " + filterTime[2] + "s");
	}

}
