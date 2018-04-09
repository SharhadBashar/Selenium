package trainingRequest;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class trainingRequest {
	public static int course(String[] args) throws InterruptedException {
		System.setProperty("webdriver.ie.driver",args[1]);		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		driver.get(args[0] + "/TMM/Lists/Training%20Matrix/SB.aspx");
		Thread.sleep(1000);	
		String amount = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = amount.split("= ");
		int course = Integer.parseInt(data[1]);
		driver.close();
		return course;
	}
	public static double reportTime (String[] args) throws InterruptedException {
		System.setProperty("webdriver.ie.driver",args[1]);		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get(args[0] + "/TMM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		long start = 0, end = 0;
		
		String oldUrl = driver.getCurrentUrl();
		start = System.nanoTime();
		driver.findElement(By.id("Tile_WPQ2_2_2")).click();//Open Training Overview Form
		Thread.sleep(1000);
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl == oldUrl){
			driver.findElement(By.id("Tile_WPQ2_2_2")).click();
		}
		
		String button = driver.findElement(By.id("FormControl_V1_I1_B27")).getAttribute("value");
		while (button.isEmpty()) {
			try {
				button = driver.findElement(By.id("FormControl_V1_I1_B27")).getAttribute("value");
			}
			catch(Exception e) {
				button = driver.findElement(By.id("FormControl_V1_I1_B27")).getAttribute("value");
			}
		}
		if (button.equals("Discard & Close")){
			end = System.nanoTime();
		}
		double diff = ((end - start)/1000000000.0);
		Thread.sleep(1000);
		driver.close();
		return diff;
		
	}
	
	public static double[] gapsTime(String[] args, double time, int courses) throws InterruptedException {
		long start, end = 0;
		System.setProperty("webdriver.ie.driver",args[1]);		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
		LocalDate dueDate = LocalDate.now().plusDays(7);
		
		Random rn = new Random();
		int id = rn.nextInt(courses) + 2;
		
		//Get number of gaps for the course
		driver.get(args[0] + "/TMM/Lists/Training%20Gaps/SB.aspx#InplviewHashe2472e88-f15f-4431-8be8-9c463eab114b=FilterField1%3DLinkTitle-FilterValue1%3DID%252D" + id);
		Thread.sleep(1000);
		String amount = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = amount.split("= ");
		int gapAmount = Integer.parseInt(data[1]);
		
		String date = dtf.format(dueDate);
		driver.get(args[0] + "/TMM/_layouts/15/FormServer.aspx?XsnLocation=~sitecollection/FormServerTemplates/Training%20Request%20Form.xsn&SaveLocation=~sitecollection/TMM/Training%20Requests&ClientInstalled=true&DefaultItemOpen=1");
		driver.manage().window().maximize();
		Thread.sleep((long) (time * 1000));
		driver.findElement(By.id("FormControl_V1_I1_O9")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("FormControl_V1_I1_O12")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("FormControl_V1_I1_T17")).sendKeys(date);
		Thread.sleep(1000);
		
		driver.findElement(By.id("FormControl_V1_I1_S23_I8_D1")).click();
		Select course = new Select(driver.findElement(By.id("FormControl_V1_I1_S23_I8_D1")));
		course.selectByValue("ID-" + id);
		Thread.sleep(5000);
		start = System.nanoTime();
		driver.findElement(By.id("FormControl_V1_I1_S23_I8_B4")).click();
		String gaps = driver.findElement(By.id("FormControl_V1_I1_S23_I8_R7")).getText();
		
		while (gaps.isEmpty()) {
			try {
				gaps = driver.findElement(By.id("FormControl_V1_I1_S23_I8_R7")).getText();
			}
			catch(Exception e) {
				gaps = driver.findElement(By.id("FormControl_V1_I1_S23_I8_R7")).getText();
			}
		}
		end = System.nanoTime();
		double diff = ((end - start)/1000000000.0);
		Thread.sleep(5000);
		
		driver.close();
		double[] returnItems = {id, gapAmount, diff};
		return returnItems;
	}
	
	public static void writeResults(String[] args, int course, double time, double[] getGaps) throws IOException {
		FileWriter fw = null; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		fw = new FileWriter(args[2] + "\\Results\\Training Request Form.txt");

		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Test done on: " + dtf.format(now) + "\r\n");
		bw.write("\r\n");
		bw.write("Total Courses: " + course + "\r\n");
		bw.write("Training report load time " + time + "s\r\n");
		bw.write("Filter by: Course ID\r\n");
		bw.write("Course ID: ID-" + (int)getGaps[0] + "\r\n");
		bw.write("Gaps for course: " + (int)getGaps[1] + "\r\n");
		bw.write("Time to get Gaps: " + getGaps[2] + "s\r\n");

		bw.close();
		fw.close();
	}
	
	public static void main(String[] args)throws InterruptedException, IOException {
		//String[] args = {"http://sandbox2013/QMW/QM4521F", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\IEDriverServer.exe", "C:\\Users\\sbashar\\Desktop\\Massuploader"};
		int course = course(args);
		double time = reportTime(args);
		double[] getGaps = gapsTime(args, time, course);
		writeResults(args, course, time, getGaps);
		System.out.println("File Created");
	}
}
