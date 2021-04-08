package app.netlify.qaautomationpractice.data_providers.forms_pages;

import app.netlify.qaautomationpractice.utility.data_io.ExcelReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class LoginData {
    @DataProvider(name = "Invalid Logins")
    public static Object[][] invalidLoginsExample() throws IOException {
        ExcelReader.setExcelSheet("src\\test\\resources\\test_xmls\\ui_tests\\forms_pages\\logins.xlsx", "TableWithBound");
        String[][] tableOfWrongLogins = ExcelReader.getTableWithinBounds("#BOUND");
        ExcelReader.close();
        return tableOfWrongLogins;
    }
}
