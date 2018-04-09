package org.openqa.selenium;
import java.util.concurrent.ThreadLocalRandom;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;


public class sopIQ {
	public static int find(String[] list, String text) {
		for (int i = 0; i < list.length; i++) {
			if (list[i].contains(text)) {
				return i;
			}
		}
		return 0;
	}
	public static String[] prereq() throws InterruptedException {
		String[] preReq = new String[10];
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("SharePoint Application Server Name: ");
		preReq[0] = scanner.nextLine();
		
		System.out.println("IP Address: ");
		preReq[1] = scanner.nextLine();
		
		boolean x = true;
		
		while(x) {
			System.out.println("Landing Site: ");
			preReq[2] = scanner.nextLine();
			
//			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//			WebDriver driver = new ChromeDriver();
			System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);		
			@SuppressWarnings("deprecation")
			WebDriver driver = new InternetExplorerDriver(caps);
			
			driver.get(preReq[2]);
			driver.manage().window().maximize();
			String link = driver.getCurrentUrl();
			if (link.contains(preReq[0])) {
				System.out.println("Link is ok");
				Thread.sleep(2000);
				driver.close();
				x = false;
			}
			else {
				System.out.println("Wrong Address, please try again");
			}
		}
		
		System.out.println("DCW Filename: ");
		preReq[3] = scanner.nextLine();
		
		System.out.println("Reason: ");
		preReq[4] = scanner.nextLine();
		
		System.out.println("Date Format: ");
		preReq[5] = scanner.nextLine();
		
		System.out.println("Time Format: ");
		preReq[6] = scanner.nextLine();
		
		System.out.println("Entity Name: ");
		preReq[7] = scanner.nextLine();
		
		System.out.println("Entity Code: ");
		preReq[8] = scanner.nextLine();
		
		System.out.println("Application Code: ");
		preReq[9] = scanner.nextLine();
		return preReq;
	}
	public static boolean installVer(String[] preReq) throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get(preReq[2]);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		driver.get(preReq[2]+"/_layouts/15/start.aspx#/Lists/Workspace%20Creation/AllItems.aspx");
		String hasDCW = driver.findElement(By.id("MSOZoneCell_WebPartWPQ2")).getText();
		Thread.sleep(2000);
		driver.close();
		if (hasDCW.contains(preReq[3])){
			return true;
		}
		return false;
	}
	
	public static boolean deploymentLog(String[] preReq) throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get(preReq[2]);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		driver.get(preReq[2]+"/_layouts/15/start.aspx#/WorkspaceCreationLogs/Forms/AllItems.aspx");
		String[] log;
		String logs = driver.findElement(By.id("MSOZoneCell_WebPartWPQ2")).getText();
		

		if (logs.contains(preReq[7] + "_" + preReq[9] + " Records Center Deployment Log") && logs.contains(preReq[7] + "_" + preReq[9] + " Work Area Deployment Log")) {
			String wa = preReq[7] + "_" + preReq[9] + " Work Area Deployment Log";
			String rc = preReq[7] + "_" + preReq[9] + " Records Center Deployment Log";
			log = logs.split("\\n");
			int wai = find(log,wa);
			int rci = find(log,rc);
			driver.get("http://" + preReq[0] + "/WorkspaceCreationLogs/" + log[wai] + ".txt");
			Thread.sleep(2000);
			String errorWA = driver.getPageSource();
			if (!errorWA.contains("Error")) {
				driver.get("http://" + preReq[0] + "/WorkspaceCreationLogs/" + log[rci] + ".txt");
				Thread.sleep(2000);
				String errorRC = driver.getPageSource();
				if (!errorRC.contains("Error")) {
					driver.close();
					return true;
				}
			}	
		}
		driver.close();
		return false;
		
	}
	public static boolean spFeatures(String[] preReq) throws InterruptedException {
		int featuresPresent = 0;
		boolean siteCollectionFeaturesPresent = false, siteFeaturesPresent = false;
		String[] siteCollectionFeaturesList = {"ArtfulBits Cascaded Lookup Column",
		 		   "CoSign Connector for SharePoint",
		 		   "CoSign Connector for Workflows",
		 		   "Nintex Workflow 2013",
		 		   "Nintex Workflow 2013 Web Parts",
		 		   "SharePoint Server Publishing Infrastructure",
		 		   "SharePoint Server Enterprise Site Collection features",
		 		   "Montrium Core Components",
		 		   "Montrium Library Templates",
		 		   "Montrium UDI Generator",
		 		   "Montrium Classification",
		 		   "Montrium Task Access Enabler",
		 		   "Montrium Nintex Task Forms",
		 		   "Montrium Item Contextual Upload",
		 		   "Montrium Conditional UI Elements"};
		String[] siteFeaturesList = {"Montrium CDM Components","Nintex Workflow 2013","SharePoint Server Publishing"};		
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/_layouts/15/settings.aspx?Source=http%3A%2F%2F" + preReq[0] + "%2F" + preReq[9] + "%2F" + preReq[8] + "%2FSitePages%2FHome%2Easpx");
		driver.manage().window().maximize();
		Thread.sleep(2000);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/_layouts/15/ManageFeatures.aspx?Scope=Site");
		Thread.sleep(2000);		
		String siteCollectionFeature = driver.findElement(By.id("DeltaPlaceHolderMain")).getText();		
		String[] scf = siteCollectionFeature.split("\\n");
		int[] scfPlace = new int[siteCollectionFeaturesList.length];
		for (int i = 0; i < siteCollectionFeaturesList.length; i++) {
			scfPlace[i] = find(scf,siteCollectionFeaturesList[i]);
		}		
		for (int i = 0; i < scfPlace.length; i++) {
			if (scf[scfPlace[i] + 2].contains("Active")){
				featuresPresent = i;
			}
		}		
		if (featuresPresent + 1 == siteCollectionFeaturesList.length) {
			siteCollectionFeaturesPresent = true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		featuresPresent = 0;
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/_layouts/15/ManageFeatures.aspx");
		Thread.sleep(2000);		
		String siteFeature = driver.findElement(By.id("DeltaPlaceHolderMain")).getText();		
		String[] sf = siteFeature.split("\\n");
		int[] sfPlace = new int[siteFeaturesList.length];		
		for (int i = 0; i < siteFeaturesList.length; i++) {
			sfPlace[i] = find(sf,siteFeaturesList[i]);
		}		
		for (int i = 0; i < sfPlace.length; i++) {
			if (sf[sfPlace[i] + 2].contains("Active")){
				featuresPresent = i;
			}
		}
		driver.close();
		if (featuresPresent + 1 == siteFeaturesList.length) {
			siteFeaturesPresent = true;
		}		
		if(siteCollectionFeaturesPresent && siteFeaturesPresent) {
			return true;
		}		
		return false;
	}
	public static boolean helpCenter(String[] preReq) throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		String link = "http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/CDMHelpCenter/SitePages/Home.aspx";
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/CDMHelpCenter/SitePages/Home.aspx");
		Thread.sleep(2000);
		String helpCenter = driver.getPageSource();
		
		if (driver.getCurrentUrl().equals(link) && helpCenter.contains("HelpCenter") && helpCenter.contains("Montrium's SOP Management Module")) {
			driver.close();
			return true;
		}
		driver.close();
		return false;		
	}
	
	public static boolean dataConnection(String[] preReq) throws InterruptedException {
		int count = 0;
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/Data%20Connection%20Library/Forms/AllItems.aspx");
		driver.manage().window().maximize();
		
		Thread.sleep(1000);	
		
		String dataConnection = driver.findElement(By.id("aggWPQ2")).getText();
		String[] data = dataConnection.split("= ");
		int numDataConnection = Integer.parseInt(data[1]);
		
		String dataConnectionFiles = driver.findElement(By.id("DeltaPlaceHolderMain")).getText();
		String[] seperated = dataConnectionFiles.split("\\s");
		for (int i = 0; i < seperated.length; i++) {
			if (seperated[i].equals("Approved")){
				count++;
			}
		}
		Thread.sleep(2000);
		if (count == numDataConnection) {
			driver.close();
			return true;
		}
		driver.close();
		return false;	
	}
	public static boolean infoPathForms(String[] preReq) throws InterruptedException {
		int formPresent = 0;
		String[] forms = {"CAPA Form",
						  "Change Control Form",
						  "Document Activity Log",
						  "Incident Form",
						  "Training Quiz Form",
						  "Training Request Form",
						  "Training Session Form"};
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/FormServerTemplates/Forms/AllItems.aspx");
		driver.manage().window().maximize();
		Thread.sleep(2000);		
		String formList = driver.findElement(By.id("DeltaPlaceHolderMain")).getText();		
		String[] fl = formList.split("\\n");
		
		int[] flPlace = new int[forms.length];		
		for (int i = 0; i < forms.length; i++) {
			flPlace[i] = find(fl,forms[i]);
		}	
		for (int i = 0; i < flPlace.length; i++) {
			if (fl[flPlace[i]].contains(forms[i])){
				formPresent = i;
			}
		}
		driver.close();
		if (formPresent + 1 == forms.length) {
			driver.close();
			return true;
		}		
		driver.close();
		return false;
	}
	public static boolean wfConstants(String[] preReq) throws InterruptedException {
		int wfcPresent = 0;
		String[] constants = {"CCM Version", "CMM Version", "TMM Enable Certificate Signature", "TMM Version", "QMW Support", "PDF by Adlib Express", "CDM Version"};
		
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/_layouts/15/NintexWorkflow/ManageCredentials.aspx?Scope=Web");
		driver.manage().window().maximize();
		Thread.sleep(2000);		
		String wfConstantList = driver.findElement(By.id("DeltaPlaceHolderMain")).getText();		
		String[] wfcl = wfConstantList.split("\\n");
		
		int[] wfclPlace = new int[constants.length];		
		for (int i = 0; i < constants.length; i++) {
			wfclPlace[i] = find(wfcl,constants[i]);
		}
		for (int i = 0; i < wfclPlace.length; i++) {
			if (wfcl[wfclPlace[i]].contains("String")){
				wfcPresent = i;
			}
		}
		if (wfcPresent + 1 == constants.length) {
			driver.close();
			return true;
		}		
		driver.close();
		return false;
		
	}
	public static boolean wf(String[] preReq) throws InterruptedException {
		int wfandl = 0;
		String[][] wf = {{"Draft Controlled Documents","Controlled Document Review"},
						 {"Draft Controlled Documents","Controlled Document Approval"},
						 {"Draft Controlled Documents","PDF Conversion"},
						 {"Draft Controlled Documents","Register Controlled Document"},
						 {"Effective Controlled Documents","Periodic Review Initiation"},
						 {"Document Activity Logs","DCC Review and Approval"},
						 {"Document Activity Logs","Periodic Review"},
						 {"Document Activity Logs","DCC Training and Closeout"},
						 {"Document Activity Logs","DCC Promotion"},
						 {"Document Activity Logs","DCC Obsolescence"},
						 {"Document Activity Logs","DCC Cancellation"},
						 {"Controlled Copy Log","Controlled Copy Manager"},
						 {" ","Periodic Review Date Monitor"},
						 {" ","Search for Controlled Copies"}};

//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/_layouts/15/NintexWorkflow/workflowgallery.aspx?&scope=site");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		String wfList = driver.findElement(By.id("DeltaPlaceHolderMain")).getText();		
		String[] wfl = wfList.split("\\n");
		for (int i = 0; i < wfl.length; i++) {
			for (int j = 0; j < wf.length; j++) {
				if (wfl[i].contains(wf[j][1]) && wfl[i].contains(wf[j][0])){ 
					wfandl++;
					break;
				}
			}
		}
		if (wfandl == wf.length) {
			driver.close();
			return true;
		}
		driver.close();
		return false;
	}
	public static boolean scheduledWF(String[] preReq) throws InterruptedException {
		int swfPresent = 0;
		String[] schedluedwf = {"Periodic Review Date Monitor"};
		
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Java\\IEDriverServer_Win32_3.6.0\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);		
		@SuppressWarnings("deprecation")
		WebDriver driver = new InternetExplorerDriver(caps);
		
		
		driver.get("http://" + preReq[0] + "/" + preReq[9] + "/" + preReq[8] + "/CDM/_layouts/15/NintexWorkflow/WorkflowSchedules.aspx");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		String swfList = driver.findElement(By.id("DeltaPlaceHolderMain")).getText();
		String[] swfl = swfList.split("\\n");
		
		int[] swflPlace = new int[schedluedwf.length];		
		for (int i = 0; i < schedluedwf.length; i++) {
			swflPlace[i] = find(swfl,schedluedwf[i]);
		}
		
		for (int i = 0; i < swflPlace.length; i++) {
			if(swfl[swflPlace[i] + 1].contains("Every day forever")){
				swfPresent = i;
			}
		}
		if (swfPresent + 1 == schedluedwf.length) {
			driver.close();
			return true;
		}
		driver.close();
		return false;
	}

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		String[] preReq = {"dev2013b", 
						   "1.1.1.1", 
						   "http://dev2013b", 
						   "MTM QBW 4.5.2.1 DCW (Quality Management Workspace)",
						   "reason",
						   "date",
						   "time",
						   "BitCoin",
						   "BTC",
						   "QMW"};//prereq();
		ArrayList<String> result = new ArrayList<String>();
		boolean installVer = installVer(preReq);
		boolean deployLog = deploymentLog(preReq);
		boolean spFeature = spFeatures(preReq);
		boolean helpCenter = helpCenter(preReq);
		boolean dataConnection = dataConnection(preReq);
		boolean infoPathForms = infoPathForms(preReq);
		boolean wfConstants = wfConstants(preReq);
		boolean wf = wf(preReq);
		boolean scheduledWF = scheduledWF(preReq);
		
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		
		writer.println("PREREQUISITE");
		writer.println("SharePoint Application Server Name: " + preReq[0]);
		writer.println("IP Address: " + preReq[1]);
		writer.println("Landing Site URL: " + preReq[2] + "/");
		writer.println("DCW fileName: " + preReq[3]);
		writer.println("Reasons: " + preReq[4]);
		writer.println("Date Format: " + preReq[5]);
		writer.println("Time Format: " + preReq[6]);
		writer.println("Entity Name: " + preReq[7]);
		writer.println("Entity Code: " + preReq[8]);
		writer.println("Application Name: " + preReq[9]);
		writer.println(" ");
		
		writer.println("INSTALLATION VERIFICATION");
		if (installVer) {
			writer.println("Solution installed as specified");
			writer.println(preReq[2] + "/" + preReq[9] + "/" + preReq[8] + "/");
			writer.println(preReq[2] + "/" + preReq[9] + "/" + preReq[8] + "-RC/");
		}
		else if (!installVer) {
			writer.println("Solution did not install as specified");
		}
		writer.println(" ");
		
		writer.println("INSTALLATION VERIFICATION: DEPLOYMENT LOGS");
		if (deployLog) {
			writer.println("Deployment Logs for both Work Area and Record Center were created");
			writer.println("No errors in Work Area log");
			writer.println("No errors in Record Center log");
		}
		else if (!deployLog) {
			writer.println("Logs not properly created, and/or error in logs");
		}
		writer.println(" ");
		
		writer.println("INSTALLATION VERIFICATION: SHAREPOINT FEATURES AND SOLUTIONS");
		if(spFeature) {
			writer.println("SharePoint site collection features were activated");
			writer.println("Montrium SharePoint Add-Ons required for SOP Connect were activated");
			writer.println("SharePoint site features were activated");
		}
		else if (!spFeature) {
			writer.println("Not all features were activated");
		}
		writer.println(" ");
		
		writer.println("INSTALLATION VERIFICATION: HELPCENTER");
		if (helpCenter) {
			writer.println("HelpCenter was deployed properly with content");
		}
		else if (!helpCenter) {
			writer.println("HelpCenter was not deployed properly");
		}
		writer.println(" ");
		
		writer.println("CONFIGURATION VERIFICATION: DATA CONNECTION FILES");
		if (dataConnection) {
			writer.println("All data connection files are approved");
		}
		else if (!dataConnection) {
			writer.println("Not all data connection files were approved");
		}
		writer.println(" ");
		
		writer.println("CONFIGURATION VERIFICATION: INFO PATH FORMS");
		if (infoPathForms) {
			writer.println("CAPA Form was successfully published");
			writer.println("Change Control Form was successfully published");
			writer.println("Document Activity Log was successfully published");
			writer.println("Incident Form was successfully published");
			writer.println("Training Quiz Form was successfully published");
			writer.println("Training Request Form was successfully published");
			writer.println("Training Session Form was successfully published");
			writer.println("ALL FORMS WERE SUCCESSFULLY PUBLISHED");
		}
		else if (!infoPathForms) {
			writer.println("NOT ALL FORMS WERE SUCCESSFULLY PUBLISHED");
		}
		writer.println(" ");
		
		writer.println("CONFIGURATION VERIFICATION: WORKFLOW CONSTANTS");
		if (wfConstants) {
			writer.println("CCM Version has type String");
			writer.println("CMM Version has type String");
			writer.println("TMM Enable Certificate Signature Version has type String");
			writer.println("TMM Version has type String");
			writer.println("QMW Support has type String");
			writer.println("PDF by Adlib Express has type String");
			writer.println("CDM Version has type String");
		}
		else if (!wfConstants) {
			writer.println("Not all Workflow Constants are configured as specified");
		}
		writer.println(" ");
		
		writer.println("CONFIGURATION VERIFICATION: NINTEX WORKFLOWS");
		if(wf) {
			writer.println("Controlled Document Reviewwas published in Draft Controlled Documents");
			writer.println("Controlled Document Approvalwas published in Draft Controlled Documents");
			writer.println("PDF Conversionwas published in Draft Controlled Documents");
			writer.println("Register Controlled Documentwas published in Draft Controlled Documents");
			writer.println("Periodic Review Initiationwas published in Effective Controlled Documents");
			writer.println("DCC Review and Approvalwas published in Document Activity Logs");
			writer.println("Periodic Reviewwas published in Document Activity Logs");
			writer.println("DCC Training and Closeoutwas published in Document Activity Logs");
			writer.println("DCC Promotionwas published in Document Activity Logs");
			writer.println("DCC Obsolescencewas published in Document Activity Logs");
			writer.println("DCC Cancellationwas published in Document Activity Logs");
			writer.println("Controlled Copy Managerwas published in Controlled Copy Log");
			writer.println("Periodic Review Date Monitorwas published in Site");
			writer.println("Search for Controlled Copieswas published in Site");
		}
		else if (!wf) {
			System.out.println("Not all WorkFlwos were properly published");
		}
		writer.println(" ");
		
		writer.println("CONFIGURATION VERIFICATION: SCHEDULED WORKFLOWS");
		if (scheduledWF) {
			writer.println("Periodic Review Date Monitor workflow is scheduled to run Everyday for an indefinite time");
		}
		else if (!scheduledWF) {
			writer.println("Periodic Review Date Monitor workflow not scheduled as specified");
		}
		
		writer.close();
		
	}

}
