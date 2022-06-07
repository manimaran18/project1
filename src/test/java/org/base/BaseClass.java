package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Table.Cell;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriver chromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver BrowserLaunch(String bname) {
	    if(bname.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	    }
	    else if(bname.equalsIgnoreCase("firefox")){
	    	WebDriverManager.firefoxdriver().setup();
	    	driver= new FirefoxDriver();
	    }
	    else if (bname.equalsIgnoreCase("edge")) {
	    	WebDriverManager.edgedriver().setup();
	    	driver=new EdgeDriver();
	    }
		return driver;
	}
	public static WebDriver BrowserLaunch2(String bname) {
		switch(bname){
		case"chrome":
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		case"firefox":
			WebDriverManager.firefoxdriver().setup();
    	driver= new FirefoxDriver();
		case"edge":
			WebDriverManager.edgedriver().setup();
    	driver=new EdgeDriver();
		default:
			System.out.println("invalid Browser name");
		}
		return driver;
	}
	
	public static void launchUrl(String e) {
		driver.get(e);
		driver.manage().window().maximize();
	}
	public static void launchUrl2(String e) {
		driver.navigate().to(e);
		driver.manage().window().maximize();
	}
	public static void implicitlyWait(int a) {
		driver.manage().timeouts().implicitlyWait(a, TimeUnit.SECONDS);
    }
	public static void quit() {
		driver.quit();
	}
	public static void sendKeys (WebElement e,String value) {
		e.sendKeys(value); 
	}
	public static void click(WebElement e) {
		e.click();
	}
	public static String currentUrl() {
	    String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	public static String currentTitle() {
		String title = driver.getTitle();
		return title;
	}
	public static void moveToElement(WebElement tar) {
		Actions a = new Actions(driver);
		a.moveToElement(tar).perform();
	}
	public static void dragAndDrop(WebElement src, WebElement tar) {
		Actions a = new Actions(driver);
        a.dragAndDrop(src, tar).perform();
	}
	public static void Click (WebElement tar) {
		Actions a = new Actions(driver);
		a.click(tar).perform();
	}
	public static void doubleClick(WebElement tar) {
		Actions a = new Actions(driver);
		a.doubleClick(tar).perform();
	}
	public static void contextClick(WebElement tar) {
		Actions a = new Actions(driver);
		a.contextClick(tar).perform();
	}
	public static void selectByIndex(WebElement e, int a) {
		Select s = new Select(e);
        s.selectByIndex(a);
	}
	public static void selectByValue(WebElement e, String a) {
		Select s = new Select(e);
        s.selectByValue(a);
	}
	public static void selectByVisibleText(WebElement e, String a) {
		Select s = new Select(e);
        s.selectByVisibleText(a);
	}
	public static void deselectAll(WebElement e) {
		Select s = new Select(e);
        s.deselectAll();
	}
	public static void deselectByVisibleText(WebElement e, String a) {
		Select s = new Select(e);
        s.deselectByVisibleText(a);
	}
	public static void deselectByValue(WebElement e, String a) {
		Select s = new Select(e);
        s.deselectByValue(a);
	}
	public static void deselectByIndex(WebElement e, int a) {
		Select s = new Select(e);
        s.deselectByIndex(a);
	}
	public static void refresh() {
		driver.navigate().refresh();
        
	}
	public static String getAttributeValue(WebElement e) {
		String attribute = e.getAttribute("value");
		return attribute;
	}
	public static String getAttributeInnerText(WebElement e) {
		String attribute = e.getAttribute("innerText");
		return attribute;
	}
	public static String getText(WebElement e) {
	          String text = e.getText();
			return text;
	}
	public static void robotSelectAll() throws AWTException {
		Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_A);
   }
	public static void robotCopy() throws AWTException {
		Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_C);
   }
	public static void robotPaste() throws AWTException {
		Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
   }
	public static void robotCut() throws AWTException {
		Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_X);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_X);
   }
	public static void robotEnter() throws AWTException {
		Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);	
		r.keyRelease(KeyEvent.VK_ENTER);	
   }
	public static void windowHandles(int a) {
		Set<String> e = driver.getWindowHandles();
		List<String> li= new LinkedList<>();
		li.addAll(e);
		driver.switchTo().window(li.get(a));
	}
//	public static String readExcel(String filename, String sheet, int row, int cell) throws IOException {
//		File f = new File("C:\\Users\\Maran\\eclipse-workspace\\Maven_may23\\src\\test\\resources\\Excel\\"+filename+".xlsx");
//		 FileInputStream st = new FileInputStream(f);
//		 Workbook w = new XSSFWorkbook(st);
//		 Sheet s = w.getSheet(sheet);
//		 Row r = s.getRow(row);
//		 Cell c = r.getCell(cell);
//		 int type = c.getCellType();
//		 String value = null;
//		 
//		 if (type==1) {
//		      value = c.getStringCellValue(); 
//		 }
//		 else {
//			 if(DateUtil.isCellDateFormatted(c)) {
//				Date datecellvalue = c.getDateCellValue();
//				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd");
//			   value = sd.format(datecellvalue);
//			 }
//			 else {
//				 double numericCellValue = c.getNumericCellValue();
//				 long num = (long)numericCellValue;
//				  value = String.valueOf(num);
//			    }
//		 } 	
//		 return value;
//	} 
	public static String readExcel(String filename, String sheet, int row, int cel ) throws IOException {
		File f = new File("C:\\Users\\Maran\\eclipse-workspace\\Maven_may23\\src\\test\\resources\\Excel\\"+filename+".xlsx");
	    FileInputStream st = new FileInputStream(f);
	    Workbook w = new XSSFWorkbook(st);
	    Sheet s= w.getSheet(sheet);
	    Row r = s.getRow(row);
	    org.apache.poi.ss.usermodel.Cell cell = r.getCell(cel); //doubt
	    int type = cell.getCellType();
        String value = null;
if (type==1) {
	value = cell.getStringCellValue();
}

else {
	if(DateUtil.isCellDateFormatted(cell)) {
		Date dateCellValue = cell.getDateCellValue();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd");
		value = sd.format(dateCellValue);
	}
	else {
		double numericCellValue = cell.getNumericCellValue();
		long num = (long)numericCellValue;
		value = String.valueOf(num);
	}
}
return value;
	}
	public static void jsscrollDown(WebElement a) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", a);
	}
	public static void jsscrollup(WebElement a) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)", a);
	}
	public static void jsclick(WebElement a) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click", a);
	}
	public static void jssetAttribute(int a, String b, WebElement c) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[a].setAttribute('value','b')", c);
	}
	public static String jsgetAttribute(String a, WebElement c) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		Object executeScript= js.executeScript("return arguments[0].getAttribute('value')", c);
	     a = executeScript.toString();
		return a;
	}
	
}
	
	
