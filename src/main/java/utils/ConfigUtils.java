package utils;

import aquality.selenium.core.utilities.JsonSettingsFile;



public  class ConfigUtils {
    public static String getConfigString(String key){
        return new JsonSettingsFile("TestConfig.json").getValue(String.format("/%s",key)).toString();
    }

    public static int getConfigInt(String key){
        return (int) new JsonSettingsFile("TestConfig.json").getValue(String.format("/%s",key));
    }
}