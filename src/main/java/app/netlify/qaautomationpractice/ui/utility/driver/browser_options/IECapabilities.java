package app.netlify.qaautomationpractice.ui.utility.driver.browser_options;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.BrowserPropertyFile;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.HashMap;
import java.util.Map;

public final class IECapabilities implements DriverCapabilities {
    @Override
    public InternetExplorerOptions getOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.destructivelyEnsureCleanSession();
        HashMap<String, Object> capabilities = PropertyReader
                .getFileAsMap(BrowserPropertyFile.IEXPLORER_PROPERTIES);
        if (capabilities == null) {
            return null;
        }
        for (Map.Entry<String, Object> entry : capabilities.entrySet()) {
            options.setCapability(entry.getKey(), entry.getValue());
        }
        return options;
    }
}
