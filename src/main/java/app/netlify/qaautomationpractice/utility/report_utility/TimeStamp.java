package app.netlify.qaautomationpractice.utility.report_utility;

import app.netlify.qaautomationpractice.utility.data_io.PropertyReader;
import app.netlify.qaautomationpractice.utility.data_io.PropertyFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {
    public static String getTimeStamp() {
        String timeStampFormat = PropertyReader.getProperty(PropertyFile.GLOBAL_PROPERTIES, "timeStampFormat");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeStampFormat);
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
