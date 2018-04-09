package tmm;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class tmm {
	public static int[] items(String filterBy, String filterName) throws InterruptedException{
		int[] numOfItems = new int[5];
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		
		driver.get("http://dev2013b/QMW/BBS/TMM/_layouts/15/viewlsts.aspx");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		//Training Activities
		driver.get("http://dev2013b/QMW/BBS/TMM/Lists/Training%20Activities/SB.aspx");
		Thread.sleep(1000);
		String countTrainingActivities = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = countTrainingActivities.split("= ");
		numOfItems[0] = Integer.parseInt(data[1]);
		
		String link = "http://dev2013b/QMW/BBS/TMM/Lists/Training%20Gaps/SB.aspx";
		if (filterBy.equals("Course")) {
			String[] course = filterName.split("-");
			
			link = "http://dev2013b/QMW/BBS/TMM/Lists/Training%20Gaps/SB.aspx#InplviewHash5bb061a1-7163-4641-93b6-12c18feab6ac=FilterField1%3DLinkTitle-FilterValue1%3D" + course[0] + "%252D" + course[1];
		}
		else if (filterBy.equals("Employee")) {
			String[] name = filterName.split(" ");
			link = "http://dev2013b/QMW/BBS/TMM/Lists/Training%20Gaps/SB.aspx#InplviewHash5bb061a1-7163-4641-93b6-12c18feab6ac=FilterField1%3DEmployee%255Fx0020%255FName-FilterValue1%3D" + name[0] +"%2520" + name[1];
		}
		
		//Training GAPS
		driver.get(link);
		Thread.sleep(1000);
		String countTrainingGaps = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingGaps.split("= ");
		numOfItems[1] = Integer.parseInt(data[1]);
		
		//Training Matrix
		driver.get("http://dev2013b/QMW/BBS/TMM/Lists/Training%20Matrix/SB.aspx");
		Thread.sleep(1000);
		String countTrainingMatrix = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingMatrix.split("= ");
		numOfItems[2] = Integer.parseInt(data[1]);
		
		//Employees
		driver.get("http://dev2013b/QMW/BBS/Lists/Employee%20List/SB.aspx");
		Thread.sleep(1000);
		String countEmployees = driver.findElement(By.id("aggWPQ2")).getText();
		data = countEmployees.split("= ");
		numOfItems[3] = Integer.parseInt(data[1]);
		
		//Training Forms
		driver.get("http://dev2013b/QMW/BBS/TMM/Training%20Requests/Forms/SB.aspx");
		Thread.sleep(1000);
		String countTrainingForms = driver.findElement(By.id("aggWPQ2")).getText();
		data = countTrainingForms.split("= ");
		numOfItems[4] = Integer.parseInt(data[1]);
		
		Thread.sleep(2000);
		driver.close();
		
		return numOfItems;		
	}
	
	public static double loadTime(int form) throws InterruptedException {
		long start, end = 0;
		
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		

		driver.get("http://dev2013b/QMW/BBS/TMM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		String oldUrl = driver.getCurrentUrl();
		
		if (form == 1) {
			driver.findElement(By.id("Tile_WPQ2_7_5")).click();//Open Request Training form
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl == oldUrl){
				driver.findElement(By.id("Tile_WPQ2_7_5")).click();
			}

			start = System.nanoTime();//Start clock
			String button = driver.findElement(By.id("FormControl_V1_I1_B27")).getAttribute("value");

			if (button.contains("Discard & Close")) {
				end = System.nanoTime();
				Thread.sleep(1000);
				driver.close();
				return ((end - start)/1000000000.0);
			}
		}
		
		else if (form == 2) {
			driver.findElement(By.id("")).click();//Open Training Overview form
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl == oldUrl){
				driver.findElement(By.id("")).click();
			}

			start = System.nanoTime();//Start clock
			String button = driver.findElement(By.id("")).getAttribute("value");

			if (button.equals("Discard & Close")) {
				end = System.nanoTime();
				Thread.sleep(1000);
				driver.close();
				return ((end - start)/1000000000.0);
			}
		}
		driver.close();
		return (-1);
	}
	
	public static double gapsTime(int form, double time, String filterBy, String filterName) throws InterruptedException {
		
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		long start, end = 0;
		driver.get("http://dev2013b/QMW/BBS/TMM/SitePages/Home.aspx");
		driver.manage().window().maximize();
		
		LocalDate localDate = LocalDate.now();
		LocalDate futureDate = localDate.plusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date = futureDate.format(formatter);
		
		if (form == 1) {
			driver.get("http://dev2013b/QMW/BBS/TMM/Training%20Requests/Forms/Summary.aspx"); //Open Request Training form
			
			//String countFiles = driver.findElement(By.id("aggWPQ2")).getText();
			//String[] data = countFiles.split("= ");
			//int count = Integer.parseInt(data[1]);
			int idNum = 8010;
			
			//
			//
			//
//////////////ENTER COUNT HERE//////////////////////////////////////////////////////////////////////
/*********/	String id = "FormControl_V1_I1_S23_I8_R7_I"+ (idNum) +"_E1" ;
/*********/	String id1 = "FormControl_V1_I1_S23_I8_R7_I"+ (idNum + 1) +"_E1" ;
//////////////ENTER COUNT HERE//////////////////////////////////////////////////////////////////////
			//
			//
			//
			
			driver.findElement(By.id("idHomePageNewDocument-WPQ2")).click(); //Open new document
			long timeToLoad = (long) (time*1000);
			
			Thread.sleep(timeToLoad);
			driver.findElement(By.id("FormControl_V1_I1_O9")).click(); //Click Fill Training Gaps
			
			Thread.sleep(7000);
			driver.findElement(By.id("FormControl_V1_I1_O12")).click(); //Click Manager
			
			Thread.sleep(3000);
			driver.findElement(By.id("FormControl_V1_I1_T17")).sendKeys(date); //Enter the date(required field)
			Thread.sleep(1000);
						
			//Selecting from Drop down
			//Course
			if (filterBy.equals("Course")) {
				driver.findElement(By.id("FormControl_V1_I1_S23_I8_D1")).click();
				Select course = new Select(driver.findElement(By.id("FormControl_V1_I1_S23_I8_D1")));
				course.selectByValue(filterName);
				Thread.sleep(6000);
			}

			//BU
			else if (filterBy.equals("Business Unit")) {
			//	driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D1")).click();
			//	Select bu = new Select(driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D1")));
			//	bu.selectByValue("filterName");
			//	Thread.sleep(2000);
			//	FormControl_V1_I1_S23_I8_R7_I4291_E1
			}
			
			//Dept
			else if (filterBy.equals("Employee")) {
			//	driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D2")).click();
			//	Select dept = new Select(driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D2")));
			//	dept.selectByValue("filterName");
			//	Thread.sleep(2000);
			}
			
			//Sub-dept
			else if (filterBy.equals("Sub-Department")) {
			//	driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D3")).click();
			//	Select subDept = new Select(driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D3")));
			//	subDept.selectByValue("filterName");
			//	Thread.sleep(2000);
			}
			
			//Location 
			else if (filterBy.equals("Location")) {
			//	driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D4")).click();
			//	Select location = new Select(driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D4")));
			//	location.selectByValue("Bangalore");
			//	Thread.sleep(2000);
			}
				
			//Trainee
			else if (filterBy.equals("Employee")) {
				driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D5")).click();
				Select trainee = new Select(driver.findElement(By.id("FormControl_V1_I1_S23_I8_S3_I8_D5")));
				trainee.selectByValue(filterName);
				Thread.sleep(2000);
			}
			
			//Make sure Get training gaps are empty
			String traineeName = driver.findElement(By.id(id)).getText();
			
			if (traineeName.equals(" ")) {
				driver.findElement(By.id("FormControl_V1_I1_S23_I8_B4")).click();
				start = System.nanoTime();
				while(true) {
					try {
						traineeName = driver.findElement(By.id(id1)).getText();				
						
						if (traineeName.length() > 0) {
							end = System.nanoTime();
							double diff = ((end - start)/1000000000.0);
							driver.findElement(By.id("FormControl_V1_I1_B30")).click();
							Thread.sleep(1000);
							driver.close();
							System.out.println(diff);
							return diff;
						}
						
					}
					catch(Throwable e) {		
					}
				}				
			}			
		}
		return (-1);		
	}
	
	private static double TRworkflow() throws InterruptedException, ParseException {
		
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		//Timeouts
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		
		driver.get("http://dev2013b/QMW/BBS/TMM/Training%20Requests/Forms/Summary.aspx");
		driver.manage().window().maximize();
		
		String countFiles = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = countFiles.split("= ");
		int count = Integer.parseInt(data[1]);
		
		System.out.println(count);  
		driver.get("http://dev2013b/QMW/BBS/TMM/_layouts/15/NintexWorkflow/ItemWorkflows.aspx?ListId=%7B1547B1FA%2D6560%2D41E0%2DBC04%2D0AD1542FF517%7D&ItemId=" + (count + 1));
		
		Thread.sleep(5000);
		driver.navigate().refresh();
		
		driver.findElement(By.id("ctl00_PlaceHolderMain_rptWorkflows_ctl01_HyperLink1")).click();
		Thread.sleep(5000);
		
		String wfInfo = driver.findElement(By.id("ctl00_PlaceHolderMain_dvDetailsForRunningWorkflow")).getText();
		Thread.sleep(2000);
				
		String[] info = wfInfo.split("\\r?\\n"); 
		String[] start = info[4].split(" ");
		String[] end = info[5].split(" ");
		String startTime = start[2];
		String status = end[1];

		while (!status.equals("Completed")) {
			Thread.sleep(60000);
			driver.navigate().refresh();
			//driver.get(link);
			wfInfo = driver.findElement(By.id("ctl00_PlaceHolderMain_dvDetailsForRunningWorkflow")).getText();
			info = wfInfo.split("\\r?\\n");
			end = info[5].split(" ");
			status = end[1];	
		}
		
		String endTime = end[3];	
		if (status.equals("Completed")){
			DateFormat sdf = new SimpleDateFormat("hh:mm");
			Date started = sdf.parse(startTime);
			endTime = end[3];
			Date completed = sdf.parse(endTime);
			long timeTaken = completed.getTime() - started.getTime();
			Thread.sleep(2000);
			driver.close();
			return (timeTaken/(60000));
		}
		return (-1);
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, ParseException, FileNotFoundException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter: "); 
		System.out.println("Filter By Course (C), Employee(E), Business Unit(B), Department(D), Sub-Department(S), Location(L)"); 
		String filterBy = scanner.nextLine();
		String filterName = "";
		
		if (filterBy.equals("C") || filterBy.equals("c")){
			filterBy = "Course";
			System.out.println("Please enter Course Name: "); 
			filterName = scanner.nextLine();
		}
		else if (filterBy.equals("E") || filterBy.equals("e")){
			filterBy = "Employee";
			System.out.println("Please enter Employee Name: "); 
			filterName = scanner.nextLine();
		}
		else if (filterBy.equals("B") || filterBy.equals("b")){
			filterBy = "Business Unit";
			System.out.println("Please enter Business Unit: "); 
			filterName = scanner.nextLine();
		}
		else if (filterBy.equals("D") || filterBy.equals("d")){
			filterBy = "Department";
			System.out.println("Please enter Department: "); 
			filterName = scanner.nextLine();
		}
		else if (filterBy.equals("S") || filterBy.equals("s")){
			filterBy = "Sub-Department";
			System.out.println("Please enter Sub-Department: "); 
			filterName = scanner.nextLine();
		}
		else if (filterBy.equals("L") || filterBy.equals("l")){
			filterBy = "Location";
			System.out.println("Please enter Location: "); 
			filterName = scanner.nextLine();
		}
		
		int[] itemCount = items(filterBy, filterName);
		System.out.println("Item count: " + itemCount[0] + " " + itemCount[1] + " " + itemCount[2] + " " + itemCount[3] + " " + itemCount[4]);
		System.out.println("Please enter: "); 
		System.out.println("  1 for Training Request Form"); 
		System.out.println("  2 for Training Overview Form");

//		int form = scanner.nextInt();
		int form = 1;
		
		double[] time = new double[3];
		
		time[0] = loadTime(form);
		Thread.sleep(1000);
		filterBy = "Course";
		filterName = "Course-001";
		time[1] = gapsTime(form, time[0], filterBy, filterName);
		Thread.sleep(5000);
		
		time[2] = TRworkflow();
		Thread.sleep(1000);
		
		System.out.println("Training Request Form TR-" + (itemCount[4] + 1) + ":");
		System.out.println("Training Activities: " + itemCount[0]);
		System.out.println("Training Gaps: " + itemCount[1]);
		System.out.println("Training Matrix: " + itemCount[2]);
		System.out.println("Employees: " + itemCount[3]);
		System.out.println("Number of forms (before this one): " + itemCount[4]);
		System.out.println("Form took: " + time[0] + " s to load");
		System.out.println("GAPs took: " + time[1] + " s to load");
		System.out.println("Training Workflow took " + time[2] + " min to complete");
		
		PrintWriter out = new PrintWriter ("C:\\Users\\sbashar\\Desktop\\StrainTesting\\Training Request form TR-" + (itemCount[4] + 1) + ".txt");
		out.println("Training Request Form TR-" + (itemCount[4] + 1) +":");
		out.println("Training Activities: " + itemCount[0]);
		out.println("Training Gaps: " + itemCount[1]);
		out.println("Training Matrix (courses): " + itemCount[2]);
		out.println("Employees: " + itemCount[3]);
		out.println("Number of forms (before this one): " + itemCount[4]);
		out.println("Form took: " + time[0] + " s to load");
		out.println("GAPs took: " + time[1] + " s to load");
		out.println("Training Workflow took " + time[2] + " min to complete");
		out.close();		
	}
}
