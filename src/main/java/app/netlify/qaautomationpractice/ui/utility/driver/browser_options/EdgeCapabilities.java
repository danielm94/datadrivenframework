package app.netlify.qaautomationpractice.ui.utility.driver.browser_options;

import app.netlify.qaautomationpractice.shared_utilities.data_readers.PropertyReader;
import app.netlify.qaautomationpractice.shared_utilities.data_readers.property_file.BrowserPropertyFile;
import org.openqa.selenium.edge.EdgeOptions;

public final class EdgeCapabilities implements DriverCapabilities {
    @Override
    public EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(PropertyReader
                .getFileAsList(BrowserPropertyFile.EDGE_PROPERTIES));
        return options;
    }
}
