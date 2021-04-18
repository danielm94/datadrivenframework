package app.netlify.qaautomationpractice.ui.utility.driver;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.ui.utility.driver.driver_builder.RemoteDriverBuilder;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
/*
Author: Daniel Martins
Email: daniel.d.martins@outlook.com
*/

/**
 * Singleton class used to create and handle WebDriver instances.
 * <p>
 * Use this in your tests by:
 * <ul>
 *     <li>Invoking the {@link Driver#setDriver(String browser)} method at the beginning of your test.</li>
 *     <li>Invoking the {@link Driver#getDriver()} method when you need to access the WebDriver instance to perform an action.</li>
 *     <li>Invoking the {@link Driver#quit()} method when you finish your test.</li>
 * </ul>
 * </p>
 * <p>
 * This class supports running tests in parallel.
 * </p>
 */
public final class Driver {
    private static final ThreadLocal<URL> GRID_URL = new ThreadLocal<>();
    private static Driver instance;
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<DriverType> DRIVER_TYPE = new ThreadLocal<>();

    private Driver() {
    }

    /**
     * @return An instance of the {@link Driver} class.
     */
    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    /**
     * Fetches the Grid URL for the project from the resources/framework_properties/application properties file.
     * <p>If the value in the property file is null or empty, null will be returned.</p>
     *
     * @return The Grid URL, or null if the value is empty/null.
     */
    private static URL getGridURL() {
        try {
            //Get grid URL from properties file.
            String url = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "grid.url");
            if (url.equals("")) {
                //If URL is empty return null.
                return null;
            }
            return new URL(url);
        } catch (IOException e) {
            //If the key in the property file doesn't even exist, return null.
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a WebDriver instance for this thread.
     * <p>
     * The subclass of WebDriver created will depend on the browser listed in the resources/framework_properties/application properties file.
     * </p>
     */
    public void setDriver() {
        //Get browser name from application properties file.
        String browser = PropertyReader
                .getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "browser");

        //Set the DriverType for this thread to the browser we just got from the properties file.
        DRIVER_TYPE.set(DriverType.parseString(browser));

        //Set the Grid URL for this thread.
        GRID_URL.set(getGridURL());

        //If the Grid URL is not empty or null, we are testing with Selenium Grid, so create a RemoteWebDriver instance for this thread.
        if (Objects.nonNull(GRID_URL.get()) && !GRID_URL.get()
                                                        .toString()
                                                        .equals("")) {
            DRIVER.set(RemoteDriverBuilder.createDriverInstance(GRID_URL.get(), DRIVER_TYPE.get()));
        } else {
            //Otherwise, create a WebDriver instance for this thread.
            DRIVER.set(DRIVER_TYPE.get()
                                  .getDriverBuilder()
                                  .createDriverInstance());
        }
    }

    /**
     * Creates a WebDriver instance for a thread.
     * <p>Use this method in instances where:
     * <ul>
     *     <li>You don't want to use Maven profiles to manage what type of browser you're using to test.</li>
     *     <li>You want to set the browser type as a parameter in your TestNG test XML file.</li>
     * </ul>
     * </p>
     *
     * @param browser The browser you'd wish to run your test on.
     *                <ul>Supported browsers:
     *                  <li>Chrome</li>
     *                <li>Firefox</li>
     *                <li>Edge</li>
     *                <li>Internet Explorer</li>
     *                <li>Opera</li>
     *                </ul>
     *                <p>
     *                If the string passed doesn't match any of the options listed above, the method will instead fetch the value inside of the
     *                resources/framework_properties/application properties file.
     */
    public void setDriver(String browser) {
        //If browser passed is null or empty, get the browser name from the application properties file.
        if (browser == null || browser.equals("")) {
            browser = PropertyReader
                    .getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "browser");
        }
        //Set the DriverType for this thread to the browser passed/found in properties.
        DRIVER_TYPE.set(DriverType.parseString(browser));

        //Set the Grid URL for this thread.
        GRID_URL.set(getGridURL());

        //If the Grid URL is not empty or null, we are testing with Selenium Grid, so create a RemoteWebDriver instance for this thread.
        if (Objects.nonNull(GRID_URL.get()) && !GRID_URL.get()
                                                        .toString()
                                                        .equals("")) {
            DRIVER.set(RemoteDriverBuilder.createDriverInstance(GRID_URL.get(), DRIVER_TYPE.get()));
        } else {
            //Otherwise, create a WebDriver instance for this thread.
            DRIVER.set(DRIVER_TYPE.get()
                                  .getDriverBuilder()
                                  .createDriverInstance());
        }
    }

    /**
     * Fetches an instance of the WebDriver for this thread.
     *
     * @return WebDriver object for this thread.
     */
    public WebDriver getDriver() {
        return DRIVER.get();
    }

    /**
     * Navigates to a page using a URL.
     * @param url The page to navigate to.
     */
    public void navigateToURL(String url) {
        DRIVER.get()
              .navigate()
              .to(url);
    }

    /**
     * Quits this driver, closing every associated window.
     */
    public void quit() {
        if (DRIVER.get() != null)
            DRIVER.get()
                  .quit();
    }
}
