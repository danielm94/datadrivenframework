package app.netlify.qaautomationpractice.ui.utility.driver.browser_options;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.BrowserPropertyFile;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeCapabilities implements DriverCapabilities {
    @Override
    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(PropertyReader
                .getFileAsList(BrowserPropertyFile.CHROME_PROPERTIES));
        return options;
    }
}
