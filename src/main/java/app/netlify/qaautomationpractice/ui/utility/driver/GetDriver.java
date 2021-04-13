package app.netlify.qaautomationpractice.ui.utility.driver;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.FrameworkPropertyFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GetDriver {
    private static final ThreadLocal<URL> GRID_URL = new ThreadLocal<>();
    private static GetDriver instance;
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverManager> WEB_DRIVER_MANAGER = new ThreadLocal<>();
    private static final ThreadLocal<DriverManagerType> DRIVER_TYPE = new ThreadLocal<>();
    private static final ThreadLocal<String> BROWSER_TYPE = new ThreadLocal<>();
    private static final ThreadLocal<Capabilities> CAPABILITIES = new ThreadLocal<>();

    private GetDriver() {
    }

    public static GetDriver getInstance() {
        if (instance == null) {
            instance = new GetDriver();
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
            BROWSER_TYPE.set(PropertyReader
                    .getProperty(FrameworkPropertyFile.APPLICATION_PROPERTIES, "browser"));
        } else {
            BROWSER_TYPE.set(browser);
        }

        GRID_URL.set(getGridURL());
        DRIVER_TYPE.set(DriverManagerTypeParser
                .getDriverManagerType(BROWSER_TYPE.get()));
        CAPABILITIES.set(OptionsManager
                .getCapabilities(DRIVER_TYPE.get()));
        if (Objects.nonNull(GRID_URL.get()) && !GRID_URL.get().toString().equals("")) {
            WEB_DRIVER_MANAGER.set(WebDriverManager.seleniumServerStandalone());
            WEB_DRIVER_MANAGER.get().setup();
            DRIVER.set(new RemoteWebDriver(GRID_URL.get(), CAPABILITIES.get()));
        } else {
            WEB_DRIVER_MANAGER.set(WebDriverManager.getInstance(DRIVER_TYPE.get()));
            WEB_DRIVER_MANAGER.get().setup();
            if (DRIVER_TYPE.get() == null)
                throw new AssertionError("Invalid driver type. Check spelling of browser property in your test XML file");
            if (DRIVER_TYPE.get().toString().equalsIgnoreCase("firefox")) {
                DRIVER.set(new FirefoxDriver(
                        (FirefoxOptions) CAPABILITIES.get()));
            } else if (DRIVER_TYPE.get().toString().equalsIgnoreCase("chrome")) {
                DRIVER.set(new ChromeDriver(
                        (ChromeOptions) CAPABILITIES.get()));
            } else if (DRIVER_TYPE.get().toString().equalsIgnoreCase("edge")) {
                DRIVER.set(new EdgeDriver(
                        (EdgeOptions) CAPABILITIES.get()));
            } else if (DRIVER_TYPE.get().toString().equalsIgnoreCase("iexplorer")) {
                DRIVER.set(new InternetExplorerDriver(
                        (InternetExplorerOptions) CAPABILITIES.get()));
            } else if (DRIVER_TYPE.get().toString().equalsIgnoreCase("opera")) {
                DRIVER.set(new OperaDriver(OptionsManager.getOperaOptions()));
            }
        }
    }

    public WebDriver getDriver() {
        return DRIVER.get();
    }

    private WebDriverManager getDriverManager() {
        return WEB_DRIVER_MANAGER.get();
    }

    public String getDriverVersion() {
        if (WEB_DRIVER_MANAGER.get() != null)
            return getDriverManager().getDownloadedDriverVersion();
        return "null";
    }

    public String getBrowserClass() {
        if (WEB_DRIVER_MANAGER.get() == null)
            return "null";
        else
            return formatBrowserClass(
                    getDriverManager()
                            .getDriverManagerType()
                            .browserClass());
    }

    private String formatBrowserClass(String browserDriver) {
        char[] c = browserDriver.toCharArray();
        int index = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '.') {
                index = i;
            }
        }
        if (index > 0) {
            return browserDriver.substring(index + 1);
        }
        return browserDriver;
    }

    public void quit() {
        if (DRIVER.get() != null)
            DRIVER.get().quit();
    }
}
