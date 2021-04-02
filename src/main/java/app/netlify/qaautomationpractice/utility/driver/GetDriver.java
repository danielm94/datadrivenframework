package app.netlify.qaautomationpractice.utility.driver;

import app.netlify.qaautomationpractice.utility.data_io.PropertyReader;
import app.netlify.qaautomationpractice.utility.data_io.PropertyFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GetDriver {
    private static final URL GRID_URL = getGridURL();
    private static GetDriver instance;
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final ThreadLocal<WebDriverManager> webDriverManager = new ThreadLocal<>();
    private final ThreadLocal<String> sessionId = new ThreadLocal<>();

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
            String url = PropertyReader.getProperty(PropertyFile.APPLICATION_PROPERTIES, "grid.url");
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
            browser = PropertyReader.getProperty(PropertyFile.APPLICATION_PROPERTIES, "browser");
        }
        DriverManagerType driverType = DriverManagerTypeGetter.getDriverManagerType(browser);
        Capabilities capabilities;

        if (GRID_URL != null && !GRID_URL.toString().equals("")) {
            switch (Objects.requireNonNull(driverType)) {
                case CHROME:
                    capabilities = OptionsManager.getChromeOptions();
                    break;
                case FIREFOX:
                    capabilities = OptionsManager.getFirefoxOptions();
                    break;
                case EDGE:
                    capabilities = OptionsManager.getEdgeOptions();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + driverType);
            }
            webDriverManager.set(WebDriverManager.seleniumServerStandalone());
            getDriverManager().setup();
            driver.set(new RemoteWebDriver(GRID_URL, capabilities));
            sessionId.set(((RemoteWebDriver) driver.get()).getSessionId().toString());
        } else {
            webDriverManager.set(WebDriverManager.getInstance(driverType));
            getDriverManager().setup();
            if (driverType == null) throw new AssertionError("Invalid driver type. Check spelling of browser property in your test XML file");
            if (driverType.toString().equalsIgnoreCase("firefox")) {
                driver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
                sessionId.set(((FirefoxDriver) driver.get()).getSessionId().toString());
            } else if (driverType.toString().equalsIgnoreCase("chrome")) {
                driver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
                sessionId.set(((ChromeDriver) driver.get()).getSessionId().toString());
            } else if (driverType.toString().equalsIgnoreCase("edge")) {
                driver.set(new EdgeDriver(OptionsManager.getEdgeOptions()));
                sessionId.set(((EdgeDriver) driver.get()).getSessionId().toString());
            }
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    private WebDriverManager getDriverManager() {
        return webDriverManager.get();
    }

    public String getSessionID() {
        return sessionId.get();
    }

    public String getDriverVersion() {
        if (webDriverManager.get() != null)
            return getDriverManager().getDownloadedDriverVersion();
        return "null";
    }

    public String getBrowserClass() {
        if (webDriverManager.get() == null)
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
        if (driver.get() != null)
            getDriver().quit();
    }
}
