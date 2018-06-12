package com.order;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	String[] card = {
		    "#ctl00_MainContent_fmwOrder_cardList_0",
		    "#ctl00_MainContent_fmwOrder_cardList_1",
		    "#ctl00_MainContent_fmwOrder_cardList_2"};
	public String selectCard(int cardcode ) {
		  return card[cardcode];
		}
	
	public static void main(String[] args) throws InterruptedException {
		
		

		System.setProperty("webdriver.chrome.driver", "/Users/malvo/Documents/selenium dependencies/drivers/chromedriver");
		
		WebDriver driver = new ChromeDriver(); 
	
		driver.manage().window().fullscreen();
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		driver.findElement(By.cssSelector("#ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.cssSelector("#ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.cssSelector("#ctl00_MainContent_login_button")).click();
		driver.findElement(By.cssSelector("#ctl00_menu > li:nth-child(3) > a")).click();
		
		//5. Random Quantity
		Random r = new Random();
		int random = r.nextInt(99);
		String rStr = "" + random;
		
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(rStr);
		
		//6. Name with random middle name
		String[] middleName = {"Olsen", "Kamil", "Can", "Michael", "Ali", "Mike", "Omer", "Yusuf"};
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtName")).sendKeys("John " 
		+ middleName[r.nextInt(middleName.length)]+ " Smith");
		
		//7.Street
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("123 Any st");
		
		//8.City
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Anytown");
		
		//9.State
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");
		
		//10. Zip code
		String zipCode ="";
		for (int i = 0; i<5; i++) {
			zipCode += "" + r.nextInt(9);
		}
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zipCode);
		
		//11.Card Type
		Order o = new Order();
		int cardType = r.nextInt(3);
		
		String creditCard = o.selectCard(cardType);
		
		driver.findElement(By.cssSelector(creditCard)).click();
		
		//12.Card Number
		
		String card= "";
		
		if(cardType ==0) {
			card = "4";
			for (int i = 0; i<15; i++) {
				card +=r.nextInt(9);
		}
		}else if(cardType ==1) {
			card = "5";
			for (int i = 0; i < 15; i++) {
				card +=r.nextInt(9);
			}
		}else if(cardType ==2) {
			card = "3";
			for (int i = 0; i <14; i++) {
				card +=r.nextInt(9);
			}
		}
			driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(card);
			
			
		//13.Expiration
			
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("06/19");
		
		//14.Process
	//	driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		
		//15. Verify Contains
		
//		if( driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox1")).isDisplayed()){
//			System.out.println("Account Added");
//			}else{
//			System.out.println("Not Added");
//			}
		
		 String expected = "New order has been successfully added.";
	        String text = driver.findElement(By.tagName("body")).getText();
	        if (text.contains(expected)) {
	            System.out.println("pass");
	        } else {
	            System.out.println("fail");
	            System.out.println("Expected:\t" + expected);
	        }

	
		Thread.sleep(5000);
		//driver.close();	
		
	}

}
