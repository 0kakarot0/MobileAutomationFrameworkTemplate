package utils.driverUtils;

public enum MobileElementAttributes {
    MOBILE_ATTRIBUTE_BOUNDS("bounds"),
    MOBILE_ATTRIBUTE_CLASS("class"),
    MOBILE_ATTRIBUTE_PACKAGE("package"),
    MOBILE_ATTRIBUTE_CONTENT_DESCRIPTION("content-desc"),
    MOBILE_ATTRIBUTE_TEXT("text");

    private final String mobileElementAttribute;

    MobileElementAttributes(String mobileElementAttribute) {
        this.mobileElementAttribute = mobileElementAttribute;
    }

    public String getName() {
        return mobileElementAttribute;
    }
}
