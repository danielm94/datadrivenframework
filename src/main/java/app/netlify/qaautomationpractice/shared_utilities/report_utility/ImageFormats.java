package app.netlify.qaautomationpractice.shared_utilities.report_utility;

public enum ImageFormats {
    APNG(".apng"),
    AVIF(".avif"),
    GIF(".gif"),
    JPEG(".jpg"),
    PNG(".png"),
    SVG(".svg"),
    WEBP(".webp");

    private final String fileFormat;

    ImageFormats(String fileFormat) {
        this.fileFormat = fileFormat;
    }
    public String toString(){
        return fileFormat;
    }
}
