package app.netlify.qaautomationpractice.ui.utility.driver.browser_options;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.BrowserPropertyFile;
import org.openqa.selenium.opera.OperaOptions;

public final class OperaCapabilities implements DriverCapabilities {
    @Override
    public OperaOptions getOptions() {
        OperaOptions options = new OperaOptions();
        options.addArguments(PropertyReader
                .getFileAsList(BrowserPropertyFile.OPERA_PROPERTIES));
        return options;
    }
}
