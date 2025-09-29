package com.testcase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Home_Base;

import com.utility.Home_Utility;

public class Home_TestCases extends Home_Base {

	public static Home_Utility homeutil;
	public static Workbook workbook;
	public static Sheet sheet;
	public static com.aventstack.extentreports.ExtentReports extent;
	public static com.aventstack.extentreports.ExtentTest test;

	public Home_TestCases() {
		super();
	}

	@BeforeMethod
	public void GetSetup() throws InterruptedException {
		setup();
		homeutil = new Home_Utility();
		extent = homeutil.getInstance();

	}
	
	@Test(priority = 1)
	public void Test_selectFromdromdown() throws InterruptedException {
		test = extent.createTest("Test_SelectFromdromdown");

		
		WebElement fromstation = driver.findElement(By.xpath("//input[@id='txtStationFrom']"));
		fromstation.clear();

		fromstation.sendKeys("del");
		Thread.sleep(3000);

		List<WebElement> option_List = driver.findElements(
				By.xpath("//div[@class='autocomplete-w1']/div[contains(@class,'autocomplete')]/div[@title]"));

		
		System.out.println(option_List.size());
		
		System.out.println("*********************************************************************\n");
			
		System.out.println("Verifying the position"+ " "+option_List.get(3).getText());
		
		System.out.println("*********************************************************************\n");
		
		
		if (option_List.size() >= 4) {
	        System.out.println("Selecting the 4th suggestion: " + option_List.get(3).getText());
	        option_List.get(3).click();
	        Thread.sleep(2000);
	    } else {
	        System.out.println("Fewer than 4 suggestions appeared.");
	        System.out.println("*********************************************************************\n");
	    }
		

	}

	@Test(priority = 2)
	public void Test_FromStationDropdwon() throws IOException {
		test = extent.createTest("Test_FromStationDropdwon");

		WebElement fromstation = driver.findElement(By.xpath("//input[@id='txtStationFrom']"));
		fromstation.clear();
		fromstation.sendKeys("del");

		List<WebElement> options = driver.findElements(By.xpath("(//div[@class='autocomplete-w1'])[1]"));

		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("DropdownValues_29Sep");
		int rowNum = 0;

		for (WebElement option : options) {

			Row row = sheet.createRow(rowNum++);
			Cell cell = row.createCell(0);
			cell.setCellValue(option.getText());

		}
		FileOutputStream Fileout = new FileOutputStream("C:\\Users\\anshu\\DropdownValues_29Sep.xlsx");
		workbook.write(Fileout);
		Fileout.close();
		System.out.println("New Excel File Created Save");
	}



	@Test(priority = 3)
	public void Test_HhandleCalendar() throws InterruptedException {
		test = extent.createTest("Test_HandleCalendar");
	
		String date = "30-Sep-25 Tue";
		String[] splitdateArr = date.split("-");
		String day = splitdateArr[0];
		String month = splitdateArr[1];
		String year = splitdateArr[2];
		
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		
		WebElement click_On_date_Calendar = driver.findElement(By.xpath("//input[contains(@title,'Select Departure date for availability')]"));
		click_On_date_Calendar.click();
		Thread.sleep(3000);
		
		//driver.findElement(By.xpath("//body[1]/div[2]/center[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[8]/td[1]"))

		String B_xpath = "//body[1]/div[2]/center[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[";
		String A_xpath ="]/td[";
		
		final int totalweekdays = 7;
		boolean flag = false;
		String dayVal = null;
		

		for (int row1 = 6; row1 <= 8; row1++) {
			for (int col = 1; col <= totalweekdays; col++) {

				try {
					dayVal = driver.findElement(By.xpath(B_xpath + row1 + A_xpath + col + "]")).getText();
				} catch (NoSuchElementException e) {
					System.out.println("please enter correct value");
					flag = false;
					break;
				}

				System.out.println(dayVal);

				if (dayVal.contains(day)) {
					driver.findElement(By.xpath(B_xpath + row1 + A_xpath + col + "]")).click();
					Thread.sleep(2000);
					flag = true;

				}

			}

			if (flag) {
				break;
			}
		}

	}

	@AfterMethod
	public void teardown() {
		extent.flush();
		driver.quit();
		
	}

}
