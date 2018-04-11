package deptAndSubDept;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class deptAndSubDept {
	public static void dept (String bu, int dept) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		for (int i = 0; i < dept; i++) {
			driver.get("http://sandbox2013/QMW/QM4541G/Lists/Departments/NewForm.aspx?Source=http%3A%2F%2Fsandbox2013%2FQMW%2FQM4541G%2FLists%2FDepartments%2FAllItems%2Easpx&RootFolder=");
			if (bu.equals("AMEA")) {	
			}
			else if (bu.equals("CHINA")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_33064ce4-53d7-48b5-9ccb-c7bee51eb364_$LookupField")));
				buType.selectByValue("3");
			}
			else if (bu.equals("DP")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_33064ce4-53d7-48b5-9ccb-c7bee51eb364_$LookupField")));
				buType.selectByValue("7");
			}
			else if (bu.equals("EA")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_33064ce4-53d7-48b5-9ccb-c7bee51eb364_$LookupField")));
				buType.selectByValue("4");
			}
			else if (bu.equals("MLA")) {				
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_33064ce4-53d7-48b5-9ccb-c7bee51eb364_$LookupField")));
				buType.selectByValue("5");
			}
			else if (bu.equals("Corporate")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_33064ce4-53d7-48b5-9ccb-c7bee51eb364_$LookupField")));
				buType.selectByValue("6");
			}
			else if (bu.equals("API")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_33064ce4-53d7-48b5-9ccb-c7bee51eb364_$LookupField")));
				buType.selectByValue("8");				
			}
			driver.findElement(By.id("Department_x0020_Name_ea4b8701-21ea-4dd3-848e-b3ce04615a8f_$TextField")).sendKeys("Dept " + bu + " " + (i + 1));
			driver.findElement(By.id("Department_x0020_Code_c4742ee3-b3d3-4b6a-8008-2aec973fd1e2_$TextField")).sendKeys(bu + (i + 1));
			driver.findElement(By.id("ctl00_ctl40_g_322ae353_930d_4142_ba78_b409332cf707_ctl00_toolBarTbl_RightRptControls_ctl00_ctl00_diidIOSaveItem")).click();
		}
		driver.close();
	}
	public static void location (String bu, int location) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		for (int i = 0; i < location; i++){
			driver.get("http://sandbox2013/QMW/QM4541G/Lists/Locations/NewForm.aspx?Source=http%3A%2F%2Fsandbox2013%2FQMW%2FQM4541G%2FLists%2FLocations%2FAllItems%2Easpx&RootFolder=");
			if (bu.equals("AMEA")) {				
			}			
			else if (bu.equals("CHINA")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_cfb09fb4-8622-49ce-bd44-29ba85f117ed_$LookupField")));
				buType.selectByValue("3");				
			}			
			else if (bu.equals("DP")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_cfb09fb4-8622-49ce-bd44-29ba85f117ed_$LookupField")));
				buType.selectByValue("7");				
			}			
			else if (bu.equals("EA")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_cfb09fb4-8622-49ce-bd44-29ba85f117ed_$LookupField")));
				buType.selectByValue("4");	
			}			
			else if (bu.equals("MLA")) {				
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_cfb09fb4-8622-49ce-bd44-29ba85f117ed_$LookupField")));
				buType.selectByValue("5");	
			}
			else if (bu.equals("Corporate")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_cfb09fb4-8622-49ce-bd44-29ba85f117ed_$LookupField")));
				buType.selectByValue("6");				
			}			
			else if (bu.equals("API")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_cfb09fb4-8622-49ce-bd44-29ba85f117ed_$LookupField")));
				buType.selectByValue("8");	
			}			
			driver.findElement(By.id("Title_fa564e0f-0c70-4ab9-b863-0177e6ddd247_$TextField")).sendKeys("Location " + bu + " " + (i + 1));
			driver.findElement(By.id("Location_x0020_Code_1d4da0a3-8d8f-4ae3-9482-2f3e288073e4_$TextField")).sendKeys(bu + (i + 1));
			driver.findElement(By.id("ctl00_ctl40_g_ed68698a_3c18_4c72_9668_f620011a863a_ctl00_toolBarTbl_RightRptControls_ctl00_ctl00_diidIOSaveItem")).click();
		}
		driver.close();
	}
	public static void subDept (String bu, int sub) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sbashar\\Desktop\\Massuploader\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		for (int i = 0; i < sub; i++) {
			driver.get("http://sandbox2013/QMW/QM4541G/Lists/Sub-Departments/NewForm.aspx?Source=http%3A%2F%2Fsandbox2013%2FQMW%2FQM4541G%2FLists%2FSub-Departments%2FAllItems%2Easpx&RootFolder=");
			if (bu.equals("AMEA")) {				
			}
			else if  (bu.equals("CHINA")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_45ca8dd5-d395-4535-8704-4d405ffa0eba_$LookupField")));
				buType.selectByValue("3");
			}
			else if  (bu.equals("DP")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_45ca8dd5-d395-4535-8704-4d405ffa0eba_$LookupField")));
				buType.selectByValue("7");
			}
			else if  (bu.equals("EA")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_45ca8dd5-d395-4535-8704-4d405ffa0eba_$LookupField")));
				buType.selectByValue("4");
			}
			else if  (bu.equals("MLA")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_45ca8dd5-d395-4535-8704-4d405ffa0eba_$LookupField")));
				buType.selectByValue("5");
			}
			else if  (bu.equals("Corporate")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_45ca8dd5-d395-4535-8704-4d405ffa0eba_$LookupField")));
				buType.selectByValue("6");
			}
			else if  (bu.equals("API")) {
				Select buType = new Select(driver.findElement(By.id("Business_x0020_Unit_45ca8dd5-d395-4535-8704-4d405ffa0eba_$LookupField")));
				buType.selectByValue("8");
			}
			driver.findElement(By.id("Sub_x002d_Department_84d8c35f-bf91-40fb-a7b7-bb16ce175c9e_$TextField")).sendKeys(bu + " Sub-Dept " + (i + 1));
			driver.findElement(By.id("Sub_x002d_Department_x0020_Code_46390984-0364-4068-9362-d1c218140a8b_$TextField")).sendKeys(bu + (i + 1));
			driver.findElement(By.id("ctl00_ctl40_g_43662973_fa5b_4de9_a134_b8fcdfc2c876_ctl00_toolBarTbl_RightRptControls_ctl00_ctl00_diidIOSaveItem")).click();
		}
		driver.close();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Map<String, Integer> buDept = new HashMap<String, Integer>();
			buDept.put("AMEA", 17);
			buDept.put("CHINA", 29);
			buDept.put("DP", 14);
			buDept.put("EA", 24);
			buDept.put("MLA", 11);
			buDept.put("Corporate", 15);
			buDept.put("API", 1);
		
		Map<String, Integer> buLocation = new HashMap<String, Integer>();
			buLocation.put("AMEA", 7);
			buLocation.put("CHINA", 3);
			buLocation.put("DP", 4);
			buLocation.put("EA", 5);
			buLocation.put("MLA", 2);
			buLocation.put("Corporate", 5);
			buLocation.put("API", 1);
			
		Map<String,Integer> buSubDept = new HashMap<String, Integer>();
			buSubDept.put("AMEA", 93);
			buSubDept.put("CHINA", 69);
			buSubDept.put("DP", 12);
			buSubDept.put("EA", 55);
			buSubDept.put("MLA", 26);
			buSubDept.put("Corporate", 18);
			
//			
//		for (Map.Entry<String, Integer> entry : buDept.entrySet()) {
//			String bu = entry.getKey();
//			int dept = entry.getValue();
//			dept(bu, dept);
//		}
//			
//		for (Map.Entry<String, Integer> entry : buLocation.entrySet()) {
//			String bu = entry.getKey();
//			int location = entry.getValue();
//			location(bu, location);
//		}
		for (Map.Entry<String, Integer> entry : buSubDept.entrySet()) {
			String bu = entry.getKey();
			int subDept = entry.getValue();
			subDept(bu, subDept);
		}
	}

}
