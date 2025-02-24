package utils.uiCommonComponents.buttonActions;

public enum ButtonName {
    ACTIVATE("CONFIRMATION_POP_UP_SCREEN_confirm_text", "CONFIRMATION_POP_UP_SCREEN_confirm_text"),
    HOME_ICON_BUTTON("Home", "الرئيسية");

    public final String english;
    public final String englishAlt;
    public final String arabic;
    public final String arabicAlt;

    ButtonName(String english, String arabic) {
        this.english = english;
        this.englishAlt = english;
        this.arabic = arabic;
        this.arabicAlt = arabic; // Default alt to the main Arabic name if not provided
    }

    ButtonName(String english, String arabic, String arabicAlt) {
        this.english = english;
        this.englishAlt = english;
        this.arabic = arabic;
        this.arabicAlt = arabicAlt;
    }

    ButtonName(String english, String englishAlt, String arabic, String arabicAlt) {
        this.english = english;
        this.englishAlt = englishAlt;
        this.arabic = arabic;
        this.arabicAlt = arabicAlt;
    }

    // Method to get button name based on language
    public String getName(String language) {
        if (language.equalsIgnoreCase("ar")) {
            return arabic;
        } else return english;
    }
}
