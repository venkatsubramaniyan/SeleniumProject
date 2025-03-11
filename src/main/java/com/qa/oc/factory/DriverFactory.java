package com.qa.oc.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import com.qa.oc.constant.AppConstant;
import com.qa.oc.exception.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();	

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("The browser name is " + browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver());
			break;
		default:
			System.out.println("Please pass the right browser" + browserName);
			throw new BrowserException("Browser not found");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	

	public Properties initProp() {

		String envName = System.getProperty("env");
		System.out.println("The env name is " + envName);
		FileInputStream fis = null;
		prop = new Properties();

		try {

			if (envName == null) {
				fis = new FileInputStream(AppConstant.QA_CONFIG_FILE_PATH);
			}

			else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					fis = new FileInputStream(AppConstant.QA_CONFIG_FILE_PATH);
					break;
				case "uat":
					fis = new FileInputStream(AppConstant.UAT_CONFIG_FILE_PATH);
					break;
				case "stg":
					fis = new FileInputStream(AppConstant.STG_CONFIG_FILE_PATH);
					break;
				case "dev":
					fis = new FileInputStream(AppConstant.DEV_CONFIG_FILE_PATH);
					break;
				default:
					System.out.println("Please pass the right environment");
					break;
				}
			}

			prop.load(fis);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {
		
		// Get the driver instance
        TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
        
        // Take the screenshot and save it to a temporary location
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
        
        // Define the path for the screenshots folder
        String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots";
        
        // Create the screenshots folder if it doesn't exist
        File screenshotsDir = new File(screenshotsDirPath);
        if (!screenshotsDir.exists()) {
            if (screenshotsDir.mkdirs()) {
                System.out.println("Folder 'screenshots' created successfully at: " + screenshotsDirPath);
            } else {
                System.out.println("Failed to create the folder 'screenshots' at: " + screenshotsDirPath);
            }
        }

        // Define the destination path for the screenshot
        String screenshotPath = screenshotsDirPath + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(screenshotPath);

        // Copy the screenshot to the destination path
        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destination.getAbsolutePath();		
	}

}
