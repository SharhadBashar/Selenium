package tmmOverviewReport;

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

public class tmm {
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
	private static String[] businessUnitList(String[] args) {
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get(args[0] + "/Lists/Business%20Units/AllItems.aspx");
		String[] data = (driver.findElement(By.id("DeltaPlaceHolderMain")).getText()).split("Business Unit Code\n");
		String[] bu = data[1].split("\n");
		for (int i = 0; i < bu.length; i++) {
			if (bu[i].equals("")){bu[i] = "Empty";} 
			bu[i] = bu[i].substring(0, bu[i].lastIndexOf(" ")<0?0:bu[i].lastIndexOf(" "));
		}
		driver.close();
		System.out.println("Here are the Business Units: ");
		return bu;
	}
	
	private static List<String> deptBUList(String[] args, String[] bu, int listNum) {
		System.setProperty("webdriver.ie.driver",args[1]);
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.get(args[0] + "/Lists/Departments/AllItems.aspx");
		String[] data = (driver.findElement(By.id("DeltaPlaceHolderMain")).getText()).split("Department Code\n");
		String[] allDept = data[1].split("\n");
		//for(int i = 0; i < allDept.length; i++) {System.out.println(allDept[i]);}	
		List<String> dept = new ArrayList<String>();
		Arrays.sort(allDept);
		String BU = bu[listNum - 1];
		for(int i = 0; i < allDept.length; i++) {
			if (allDept[i].contains(BU)) {
				dept.add(allDept[i].substring(0, allDept[i].lastIndexOf(" ")<0?0:allDept[i].lastIndexOf(" ")));
			}
		}
		driver.close();
		
		System.out.println("Here are the Departments for the BU picked: ");
		return dept;
	}
	
	
	public static String[] genReportBU(String[] args, int listNum, int listNumDept, String dept, int error) throws InterruptedException {
		double diff = 0;
		double diffGaps = 0;
		String firstName, lastName = null;
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
		//try {
			driver.get(args[0] + "/TMM/SitePages/Home.aspx");
			driver.manage().window().maximize();
			Thread.sleep(1000);
			
			long start = 0, end = 0;
			long startGaps = 0, endGaps = 0;
			String oldUrl = driver.getCurrentUrl();
			driver.findElement(By.id("Tile_WPQ3_6_5")).click();//Open Training Overview Form
			Thread.sleep(1000);
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl == oldUrl){
				driver.findElement(By.id("Tile_WPQ3_6_5")).click();
			}
			Thread.sleep(5000);
			
			WebElement elementBU = driver.findElement(By.xpath("//*[@aria-labelledby='select2-ddlBusinessUnit-container']"));
			driver.findElement(By.xpath("//*[@aria-labelledby='select2-ddlBusinessUnit-container']")).click();
			Thread.sleep(1000);
			
			for(int i = 0; i < listNum - 1; i++){
				elementBU.sendKeys(Keys.DOWN);
			}
			elementBU.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			
			if (dept.equals("Y")||dept.equals("y")) {
				WebElement elementDept = driver.findElement(By.xpath("//*[@aria-labelledby='select2-ddlDepartment-container']"));
				driver.findElement(By.xpath("//*[@aria-labelledby='select2-ddlDepartment-container']")).click();
				Thread.sleep(1000);
				
				for(int i = 0; i < listNumDept - 1; i++){
					elementDept.sendKeys(Keys.DOWN);
				}
				elementDept.sendKeys(Keys.ENTER);
				Thread.sleep(1000);
			}
			
			driver.findElement(By.id("btnGenerateReport")).click();
			start = System.nanoTime();
			
			String load = driver.findElement(By.id("divBusinessUnit")).getText();
			while (load.isEmpty()) {
				try {
					load = driver.findElement(By.id("divBusinessUnit")).getText();
				}
				catch(Exception e) {
					load = driver.findElement(By.id("divBusinessUnit")).getText();
				}
			}
			end = System.nanoTime();
			diff = ((end - start)/1000000000.0);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"tblData\"]/tbody/tr[3]/td")).click();
			
			startGaps = System.nanoTime();
			
			String header = driver.findElement(By.xpath("//*[@id=\"tblData\"]/tbody/tr[4]/td[1]")).getText();
			while (header.isEmpty()) {
				try {
					header = driver.findElement(By.xpath("//*[@id=\"tblData\"]/tbody/tr[4]/td[1]")).getText();
				}
				catch(Exception e) {
					header = driver.findElement(By.xpath("//*[@id=\"tblData\"]/tbody/tr[4]/td[1]")).getText();
				}
			}
			if (header.equals("Course Name")){
				endGaps = System.nanoTime();
			}
			diffGaps = ((endGaps - startGaps)/1000000000.0);
			Thread.sleep(5000);
			String employee = ((driver.findElement(By.xpath("//*[@id=\"tblData\"]/tbody/tr[3]/td/b[1]")).getText()));
			Thread.sleep(1000);
			driver.get(args[0] + "/TMM/Lists/Training%20Gaps/SB.aspx#InplviewHashe2472e88-f15f-4431-8be8-9c463eab114b=FilterField1%3DEmployee%255Fx0020%255FName-FilterValue1%3D" + employee);
			Thread.sleep(5000);
			String gaps = driver.findElement(By.id("aggWPQ2")).getText();
			String[] data = gaps.split("= ");
			gaps = data[1];
			driver.close();
		if (gaps.equals(null)) {gaps = "Unable to get GAPS";}
		String[] returnItems = {String.valueOf(diff), String.valueOf(diffGaps), gaps};
		return returnItems;
		
	}
	
	public static double genReportCourse(String[] args, int listNum) throws InterruptedException {
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
		
		String oldUrl = driver.getCurrentUrl();
		driver.findElement(By.id("Tile_WPQ3_6_5")).click();//Open Training Overview Form
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl == oldUrl){
			driver.findElement(By.id("Tile_WPQ3_6_5")).click();
		}
		Thread.sleep(5000);
		driver.findElement(By.id("rdReportType2")).click();
		
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//*[@aria-labelledby='select2-ddlCourse-container']"));
		driver.findElement(By.xpath("//*[@aria-labelledby='select2-ddlCourse-container']")).click();
		Thread.sleep(5000);
		
		for(int i = 0; i < listNum - 1; i++){
			element.sendKeys(Keys.DOWN);
		}
		element.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		driver.findElement(By.id("btnGenerateReport")).click();
		start = System.nanoTime();
		String load = driver.findElement(By.id("divCourse")).getText();
		while (load.isEmpty()) {
			load = driver.findElement(By.id("divCourse")).getText();
		}
		end = System.nanoTime();
		double diff = ((end - start)/1000000000.0);
		driver.close();
		return diff;
	}
	
	public static void writeResults(String[] args, String type, int[] list, String[] genReport) throws IOException {
		FileWriter fw = null; 
		String reportType = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		if (type.equals("B")) {fw = new FileWriter(args[2] + "\\Results\\Training Overview Report BU only.txt");}
		else if (type.equals("D")) {fw = new FileWriter(args[2] + "\\Results\\Training Overview Report BU and Dept.txt");}
		else if (type.equals("C")) {fw = new FileWriter(args[2] + "\\Results\\Training Overview Report Course.txt");}
		
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Test done on: " + dtf.format(now) + "\r\n");
		bw.write("\r\n");
		bw.write("Training Activities: " + list[0] + "\r\n");
		bw.write("Employees: " + list[1] + "\r\n");
		bw.write("Courses: " + list[2] + "\r\n");
		bw.write("GAPs: " + list[3] + "\r\n");
		bw.write("Job Roles: " + list[4] + "\r\n");
		bw.write("Job Titles: " + list[5] + "\r\n");
		
		if (type.equals("B")) {reportType = "with Business Unit only: ";}
		else if (type.equals("D")) {reportType = "with BU and Department: ";}
		else if (type.equals("C")) {reportType = "with Course only: ";}
		
		
		bw.write("Time to generate report " + reportType + genReport[0] + "s\r\n");
		if (type.equals("B")|| type.equals("D")) {bw.write("Time to get " + genReport[2] + " GAPS: " + genReport[1] + "s\r\n");}
		bw.close();
		fw.close();
	}
	

	public static void main(String[] args) throws InterruptedException, IOException {	
		//String[] args = {"http://sandbox2013/QMW/QM4521F", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\IEDriverServer.exe", "C:\\Users\\sbashar\\Desktop\\Massuploader"};
//		if (args[0].substring(args[0].length() - 1).equals("/")){
//			args[0] = args[0] + "/";
//		}
		String[] genReport = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int error = 0;
		double time = 0;
		int listNumDept = 0;
		String type = null;
		int[] list = new int[6];
		list = getItems(args);
		
		System.out.println("Filter by BU or Course? Enter 'B' for Business Unit and 'C' for Courses: ");
		String filterBy = scanner.nextLine();

		String[] bu = businessUnitList(args);
		for (int i = 0; i < bu.length; i++) {
			System.out.println((i + 1) + ". " + bu[i]);
		}
		System.out.println("Enter a BU List Number: ");
		int listNum = scanner.nextInt();
		scanner.nextLine();
		
		
		if (filterBy.equals("b") || filterBy.equals("B")){
			type = "B";
			System.out.println("Department? (Y/N)");
			String dept = scanner.nextLine();
			if (dept.equals("Y") || dept.equals("y")) {
				
				List<String> deptBU = deptBUList(args,bu,listNum );
				for (int i = 0; i < deptBU.size(); i++) {
					System.out.println((i + 1) + ". " + deptBU.get(i));
				}
				System.out.println("Enter Department List Number: ");
				listNumDept = scanner.nextInt();
				type = "D";
			}
			genReport = genReportBU(args, listNum, listNumDept, dept, error);	
		}
			
		else if (filterBy.equals("c") || filterBy.equals("C")){	
			type = "C";
			genReport[0] = String.valueOf(genReportCourse(args, listNum));
		}

		writeResults(args, type, list, genReport);
		
		System.out.println("File created");
		
	}
}
