package app.netlify.qaautomationpractice.ui.utility.driver;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import app.netlify.qaautomationpractice.ui.utility.driver.driver_builder.RemoteDriverBuilder;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public final class Driver {
    private static final ThreadLocal<URL> GRID_URL = new ThreadLocal<>();
    private static Driver instance;
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<DriverType> DRIVER_TYPE = new ThreadLocal<>();

    private Driver() {
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    private static URL getGridURL() {
        try {
            String url = PropertyReader.getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "grid.url");
            if (url.equals("")) {
                return null;
            }
            return new URL(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDriver(String browser) {
        if (browser == null || browser.equals("")) {
            browser = PropertyReader
                    .getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "browser");
        }
        DRIVER_TYPE.set(DriverType.parseString(browser));
        GRID_URL.set(getGridURL());
        if (Objects.nonNull(GRID_URL.get()) && !GRID_URL.get()
                                                        .toString()
                                                        .equals("")) {
            DRIVER.set(RemoteDriverBuilder.createDriverInstance(GRID_URL.get(), DRIVER_TYPE.get()));
        } else {
            DRIVER.set(DRIVER_TYPE.get()
                                  .getDriverBuilder()
                                  .createDriverInstance());
        }
    }

    public WebDriver getDriver() {
        return DRIVER.get();
    }

    public void navigateToURL(String url) {
        Driver
                .getInstance()
                .getDriver()
                .navigate()
                .to(url);
    }

    public void quit() {
        if (DRIVER.get() != null)
            DRIVER.get()
                  .quit();
    }
}
