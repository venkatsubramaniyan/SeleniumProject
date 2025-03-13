package com.qa.oc.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.oc.constant.AppConstant;
import com.qa.oc.error.AppError;
import com.qa.oc.exception.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();	

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("The browser name is " + browserName);
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run tcs on remote/container:
				init_remoteDriver("chrome");
			} else {
				// run tcs in local:
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run tcs on remote/container:
				init_remoteDriver("firefox");
			} else {
				// run tcs in local:
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run tcs on remote/container:
				init_remoteDriver("edge");
			} else {
				// run tcs in local:
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;

		
		default:
			// System.out.println(AppError.INVALID_BROWSER_MESG + browserName + " is
			// invalid");
			
			throw new BrowserException(AppError.INVALID_BROWSER_MESG);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
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

	
	private void init_remoteDriver(String browserName) {
		// System.out.println("running tests on grid with browser : " + browserName);
		System.out.println("running tests on grid with browser: " + browserName);

		try {

			switch (browserName.toLowerCase().trim()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;

			default:
				System.out.println("please pass the right remote browser name....");
				throw new BrowserException(AppError.INVALID_BROWSER_MESG);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

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
