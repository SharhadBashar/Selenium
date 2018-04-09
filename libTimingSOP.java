package libTiming;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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

public class libTimingSOP {
	
	public static int[] numItems(String[] args) throws InterruptedException {
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		
		int[] numOfItems = new int[3];
		 
		driver.get(args[0] + "/CDM/Draft%20Controlled%20Documents/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);	
		
		String count = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = count.split("= ");
		numOfItems[0] = Integer.parseInt(data[1]);
		Thread.sleep(1000);
		
		
		driver.get(args[0] + "/CDM/Effective%20Controlled%20Documents/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);	
		
		count = driver.findElement(By.id("aggWPQ2")).getText();
		data = count.split("= ");
		numOfItems[1] = Integer.parseInt(data[1]);
		Thread.sleep(1000);
		
		
		driver.get(args[0] + "/CDM/Document%20Activity%20Logs/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);	
		
		count = driver.findElement(By.id("aggWPQ2")).getText();
		data = count.split("= ");
		numOfItems[2] = Integer.parseInt(data[1]);
		Thread.sleep(1000);

		driver.close();
		
		return numOfItems;
	}
	
	public static double libLoadDCD(String[] args) throws InterruptedException {
		long start, end = 0;
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		start = System.nanoTime();
		driver.findElement(By.xpath("//*[@id=\"Tile_WPQ2_4_5\"]")).click();//Open DCD Tile_WPQ2_4_5
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl == oldUrl){
			driver.findElement(By.id("Tile_WPQ2_4_5")).click();
		}

		while(true) {
			try {
				String view = driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt0")).getText();			
				if (view.length() > 0) {
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
	
	public static double libLoadECD(String[] args) throws InterruptedException {
		long start, end = 0;
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		String oldUrl = driver.getCurrentUrl();
		start = System.nanoTime();
		driver.findElement(By.id("Tile_WPQ2_1_5")).click();//Open ECD
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl == oldUrl){
			driver.findElement(By.id("Tile_WPQ2_1_5")).click();
		}
	
		while(true) {
			try {
				String view = driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt0")).getText();			
				if (view.length() > 0) {
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
	
	public static double libLoadDAL(String[] args) throws InterruptedException {
		long start, end = 0;
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		String oldUrl = driver.getCurrentUrl();
		start = System.nanoTime();
		driver.findElement(By.id("Tile_WPQ2_5_5")).click();//Open DAL
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl == oldUrl){
			driver.findElement(By.id("Tile_WPQ2_5_5")).click();
		}

		while(true) {
			try {
				String view = driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt0")).getText();			
				if (view.length() > 0) {
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
	
	public static double changeViewDCD(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/Draft%20Controlled%20Documents/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		Thread.sleep(1000);
		start = System.nanoTime();
		driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt1")).click();

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
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/Effective%20Controlled%20Documents/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		Thread.sleep(1000);
		start = System.nanoTime();
		driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt1")).click();

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
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/CDM/Document%20Activity%20Logs/Forms/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		Thread.sleep(1000);
		start = System.nanoTime();
		driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt0")).click();

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
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
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
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
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
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
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
	public static void writeResults(String[] args, int[] items, double[] loadTime, double[] viewTime, double[] filterTime) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		FileWriter fw = new FileWriter(args[2] + "\\Results\\SOP Library load times.txt");
		BufferedWriter bw = new BufferedWriter(fw);		
		bw.write("Test done on: " + dtf.format(now) + "\r\n");
		bw.write("\r\n");
		bw.write("Number of items: \r\n");
		bw.write("Draft Controlled Documents: " + items[0] + "\r\n");
		bw.write("Effective Controlled Documents: " + items[1] + "\r\n");
		bw.write("Document Activity Logs: " + items[2] + "\r\n");
		bw.write(" \r\n");
		bw.write("Time to load: " + "\r\n");
		bw.write("Draft Controlled Documents: " + loadTime[0] + "s\r\n");
		bw.write("Effective Controlled Documents: " + loadTime[1] + "s\r\n");
		bw.write("Document Activity Logs: " + loadTime[2] + "s\r\n");
		bw.write(" \r\n");
		bw.write("Time to Change Views: \r\n");
		bw.write("Draft Controlled Document view change time: " + viewTime[0] + "s\r\n");
		bw.write("Effective Controlled Document view change time: " + viewTime[1] + "s\r\n");
		bw.write("Document Activity Log view change time: " + viewTime[2] + "s\r\n");
		bw.write(" \r\n");
		bw.write("Time to Filter on view: \r\n");
		bw.write("Draft Controlled Document filter change time: " + filterTime[0] + "s\r\n");
		bw.write("Effective Controlled Document filter change time: " + filterTime[1] + "s\r\n");
		//bw.write("Document Activity Log filter change time: " + filterTime[2] + "s\r\n");
		bw.close();
		fw.close();
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		//String[] args = {"http://sandbox2013/QMW/QM4521E", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\IEDriverServer.exe", "C:\\Users\\sbashar\\Desktop\\Massuploader"};
		int[] items = numItems(args);
		System.out.println("1");
	
		double[] loadTime = new double[3];
		loadTime[0] = libLoadDCD(args);
		loadTime[1] = libLoadECD(args);
		loadTime[2] = libLoadDAL(args);
		System.out.println("2");
		
		double[] viewTime = new double[3];		
		viewTime[0] = changeViewDCD(args);
		viewTime[1] = changeViewECD(args);
		viewTime[2] = changeViewDAL(args);
		System.out.println("3");
		
		double[] filterTime = new double[3];
		filterTime[0] = filterTimeDCD(args);
		filterTime[1] = filterTimeECD(args);
		//filterTime[2] = filterTimeDAL();

		writeResults(args, items, loadTime, viewTime, filterTime);
		System.out.println("File created");
		
		
	}

	

}
