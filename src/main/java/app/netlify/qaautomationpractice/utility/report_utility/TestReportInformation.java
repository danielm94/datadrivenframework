package app.netlify.qaautomationpractice.utility.report_utility;

import java.lang.annotation.Retention;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface TestReportInformation {
    String title() default "Test";

    String description() default "Description";
}
