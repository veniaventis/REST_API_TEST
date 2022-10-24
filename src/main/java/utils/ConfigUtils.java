package utils;

import aquality.selenium.core.utilities.JsonSettingsFile;


public class ConfigUtils {
    public static String getTestDataString(String key) {
        return new JsonSettingsFile("TestData.json").getValue(String.format("/%s", key)).toString();
    }

    public static int getTestDataInt(String key) {
        return (int) new JsonSettingsFile("TestData.json").getValue(String.format("/%s", key));
    }
    public static String getSettingsDataString(String key){
        return new JsonSettingsFile("Settings.json").getValue(String.format("/%s",key)).toString();
    }
}