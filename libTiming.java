package libTiming;

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

public class libTiming {
	public static int numItems(String[] args) throws InterruptedException {
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/TMM/Lists/Training%20Activities/SB.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);	
		
		String countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = countTrainingActivities.split("= ");
		int numOfItems = Integer.parseInt(data[1]);
		
		Thread.sleep(1000);
		driver.close();
		return numOfItems;
	}
	
	public static double libLoad(String[] args) throws InterruptedException {
		long start, end = 0;
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/TMM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		String oldUrl = driver.getCurrentUrl();
		start = System.nanoTime();
		driver.findElement(By.id("Tile_WPQ3_5_2")).click();//Open Training Activities
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl == oldUrl){
			driver.findElement(By.id("Tile_WPQ3_5_2")).click();
		}
		//driver.get(args[0] + "/TMM/Lists/Training%20Activities/AllItems.aspx");

		while(true) {
			try {
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();			
				if (view.equals("1 - 30")|| view.equals("1 - 100")) {
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
	
	public static double changeView(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			
		driver.get(args[0] + "/TMM/Lists/Training%20Activities/GroupedbyTrainee.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		String oldUrl = driver.getCurrentUrl();
		Thread.sleep(1000);
		start = System.nanoTime();
		driver.findElement(By.id("WPQ2_ListTitleViewSelectorMenu_Container_surfaceopt2")).click();

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
	public static double filterTime(String[] args) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get(args[0] + "/TMM/Lists/Training%20Activities/Allitems.aspx");	
		Thread.sleep(2500);
		start = System.nanoTime();
		driver.get(args[0] + "/TMM/Lists/Training%20Activities/Allitems.aspx?Filter=1&View={42EDC2ED-FBB6-485B-90A9-B2894A32C804}");

		while(true) {
			try {
				String view = driver.findElement(By.id("bottomPagingCellWPQ2")).getText();
				if (view.equals("1 - 30") || view.equals("1 - 100")) {
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
	public static void writeResults(String[] args, int items, double[] time) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		FileWriter fw = new FileWriter(args[2] + "\\Results\\TMM Library load times.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("Test done on: " + dtf.format(now) + "\r\n");
		bw.write("\r\n");
		bw.write("Training Activities\r\n");
		bw.write("Number of activities: " + items + "\r\n");
		bw.write("Library load time: " + time[0] + "s\r\n");
		bw.write("Library view change time: " + time[1] + "s\r\n");
		bw.write("Library filter load time: " + time[2] + "s\r\n");
		bw.close();
		fw.close();
	}
	public static void main(String[] args)throws InterruptedException, IOException {
		//String[] args = {"http://sandbox2013/QMW/QM4521F", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\IEDriverServer.exe", "C:\\Users\\sbashar\\Desktop\\Massuploader"};
		double[] time = new double[3];
		int items = numItems(args);
		time[0] = libLoad(args);
		time[1] = changeView(args);
		time[2] = filterTime(args);
		writeResults(args, items, time);
		System.out.println("File created");
	}

}
