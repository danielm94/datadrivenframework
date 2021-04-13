package app.netlify.qaautomationpractice.shared_utilities.report_utility;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.PropertyFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    private static final String DEFAULT_IMAGE_FORMAT = getDefaultImageFormat();

    private static String getDefaultImageFormat() {
        return PropertyReader.getProperty(FrameworkPropertyFile.GLOBAL_PROPERTIES,"screenshotFormat");
    }

    /**
     * Takes a screenshot of the browser window and returns its file path as a string.
     *
     * @param driver    The WebDriver instance.
     * @param directory The directory the screenshot will be located in.
     * @param fileName  The file name the screenshot will be saved as.
     * @return The file path of the screenshot that will be taken.
     */
    public static String captureBrowser(WebDriver driver, String directory, String fileName) throws IOException {
        fileName = fileName + DEFAULT_IMAGE_FORMAT;
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + fileName));
        return directory + fileName;
    }

    /**
     * Takes a screenshot of a WebElement and returns its file path as a string.
     *
     * @param element   The WebDriver instance.
     * @param directory The directory the screenshot will be located in.
     * @param fileName  The file name the screenshot will be saved as.
     * @return The file path of the screenshot that will be taken.
     */
    public static String captureElement(WebElement element, String directory, String fileName) throws IOException {
        fileName = fileName + DEFAULT_IMAGE_FORMAT;
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + fileName));
        return directory + fileName;
    }


    /**
     * Takes a screenshot of the browser window and returns its file path as a string.
     *
     * @param driver    The WebDriver instance.
     * @param directory The directory the screenshot will be located in.
     * @param fileName  The file name the screenshot will be saved as.
     * @return The file path of the screenshot that will be taken.
     */
    public static String captureBrowser(WebDriver driver, String directory, String fileName, ImageFormats imageFormat) throws IOException {
        fileName = fileName + imageFormat.toString();
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + fileName));
        return directory + fileName;
    }

    /**
     * Takes a screenshot of a WebElement and returns its file path as a string.
     *
     * @param element   The WebDriver instance.
     * @param directory The directory the screenshot will be located in.
     * @param fileName  The file name the screenshot will be saved as.
     * @return The file path of the screenshot that will be taken.
     */
    public static String captureElement(WebElement element, String directory, String fileName, ImageFormats imageFormat) throws IOException {
        fileName = fileName + imageFormat.toString();
        File sourceFile = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + fileName));
        return directory + fileName;
    }
}
