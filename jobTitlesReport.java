package jobTitlesReport;
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


public class jobTitlesReport {
	public static int[] getItems(String[] args) throws InterruptedException{
		int[] list = new int[6];
		String countTrainingActivities = "";
		String[] data;		
		System.setProperty("webdriver.ie.driver",args[1]);		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		
		driver.get(args[0] + "/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		//Training Gaps///////////////////////////////////////////////////////////////////
		driver.get(args[0] + "/TMM/Lists/Training%20Gaps/SB.aspx");
		Thread.sleep(1000);	
		countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingActivities.split("= ");
		list[3] = Integer.parseInt(data[1]);
		//////////////////////////////////////////////////////////////////////////////////
		
		//Courses/////////////////////////////////////////////////////////////////////////
		driver.get(args[0] + "/TMM/Lists/Training%20Matrix/SB.aspx");
		Thread.sleep(1000);	
		countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingActivities.split("= ");
		list[2] = Integer.parseInt(data[1]);
		//////////////////////////////////////////////////////////////////////////////////
		
		//Training Activities////////////////////////////////////////////////////////////
		driver.get(args[0] + "/TMM/Lists/Training%20Activities/SB.aspx");
		Thread.sleep(1000);	
		countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingActivities.split("= ");
		list[0] = Integer.parseInt(data[1]);
		//////////////////////////////////////////////////////////////////////////////////
		
		//Employees///////////////////////////////////////////////////////////////////////
		driver.get(args[0] + "/Lists/Employee%20List/SB.aspx");
		Thread.sleep(1000);	
		countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingActivities.split("= ");
		list[1] = Integer.parseInt(data[1]);
		//////////////////////////////////////////////////////////////////////////////////
		
		//Job Roles///////////////////////////////////////////////////////////////////////
		driver.get(args[0] + "/Lists/Job%20Roles/SB.aspx");
		Thread.sleep(1000);	
		countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingActivities.split("= ");
		list[4] = Integer.parseInt(data[1]);
		//////////////////////////////////////////////////////////////////////////////////
		
		//Job Titles//////////////////////////////////////////////////////////////////////
		driver.get(args[0] + "/Lists/Job%20Titles/SB.aspx");
		Thread.sleep(1000);	
		countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingActivities.split("= ");
		list[5] = Integer.parseInt(data[1]);
		//////////////////////////////////////////////////////////////////////////////////
		Thread.sleep(1000);
		driver.close();		
		return list;
	}
	public static String[] genReport(String[] args) throws InterruptedException {
		System.setProperty("webdriver.ie.driver",args[1]);		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);		
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);		
		driver.get(args[0] + "/TMM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);		
		long start = 0, end = 0;
		long startCourse = 0, endCourse = 0;
		String oldUrl = driver.getCurrentUrl();
		driver.findElement(By.id("Tile_WPQ3_7_2")).click();//Open Training Overview Form
		Thread.sleep(1000);
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl == oldUrl){
			driver.findElement(By.id("Tile_WPQ3_7_2")).click();
		}
		start = System.nanoTime();
		String load = driver.findElement(By.id("tblData")).getText();
		while (load.isEmpty()) {
			load = driver.findElement(By.id("tblData")).getText();
		}
		end = System.nanoTime();
		double diff = ((end - start)/1000000000.0);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"tblData\"]/tr[1]/td/span[2]")).click();
		Thread.sleep(5000);		
		String role = driver.findElement(By.xpath("//*[@id=\"tblData\"]/tr[2]/td/span[2]/b")).getText(); 
		driver.findElement(By.xpath("//*[@id=\"tblData\"]/tr[2]/td/span[2]")).click();
		startCourse = System.nanoTime();
		String header = driver.findElement(By.xpath("//*[@id=\"tblData\"]/tr[3]/td[1]")).getText(); 
		while (header.isEmpty()) {
			try {
				header = driver.findElement(By.xpath("//*[@id=\"tblData\"]/tr[3]/td[1]")).getText();
			}
			catch(Exception e) {
				header = driver.findElement(By.xpath("//*[@id=\"tblData\"]/tr[3]/td[1]")).getText();
			}
		}
		if (header.equals("Course ID")){
			endCourse = System.nanoTime();
		}
		double diffCourse = ((endCourse - startCourse)/1000000000.0);
		Thread.sleep(1000);
		driver.get(args[0] + "/TMM/Lists/Training%20Matrix/SB.aspx#InplviewHash0e14f11b-ea89-4ec8-9ce0-7961fd50c1c6=FilterField1%3DRequired%255Fx0020%255FJob%255Fx0020%255FRoles-FilterValue1%3D" + role);
		Thread.sleep(5000);
		String course = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = course.split("= ");
		course = data[1];
		driver.close();
		Thread.sleep(1000);		
		String[] returnItems = {String.valueOf(diff), String.valueOf(diffCourse), course};
		return returnItems;	
	}
	
	public static void writeResults(String[] args, int[] list, String[] genReport) throws IOException {
		FileWriter fw = null; 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		fw = new FileWriter(args[2] + "\\Results\\Job Titles Report.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Test done on: " + dtf.format(now) + "\r\n");
		bw.write("\r\n");
		bw.write("Training Activities: " + list[0] + "\r\n");
		bw.write("Employees: " + list[1] + "\r\n");
		bw.write("Courses: " + list[2] + "\r\n");
		bw.write("GAPs: " + list[3] + "\r\n");
		bw.write("Job Roles: " + list[4] + "\r\n");
		bw.write("Job Titles: " + list[5] + "\r\n");
		bw.write("Time to generate report " + genReport [0]+ "s\r\n");
		bw.write("Time to get " + genReport[2] + " courses: " + genReport[1] + "s\r\n");
		
		bw.close();
		fw.close();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		//args[0] = link
		//args[1] = driver
		//args[2] = location of result file
		//String[] args = {"http://sandbox2013/QMW/QM4521F", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\IEDriverServer.exe", "C:\\Users\\sbashar\\Desktop\\Massuploader"};
		int[] list = new int[6];
		list = getItems(args);
		String[] genReport = genReport(args);
		writeResults(args, list, genReport);
		System.out.println("File created");
	}
}
