package com.miczhao.github.allureTools.environmentCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AllurePropertiesEnvironmentWriter {

    /**
     * Creates environment.prop file in default folder "/target/allure-results/"
     * @param allureEnvironment - map of environment properties
     */
    public void createAllureEnvironment(HashMap<String, String> allureEnvironment) {
        createAllureEnvironment(allureEnvironment, System.getProperty("user.dir") + "/target/allure-results");
    }

    /**
     * Creates environment.prop file in custom folder
     * @param allureEnvironment - map of environment properties
     * @param pathToAllureResults - path to folder "allure-results"
     */
    public void createAllureEnvironment(HashMap<String, String> allureEnvironment, String pathToAllureResults) {
        createAllureResultsDirectoryIfNotExists(new File(pathToAllureResults));
        createAndWriteEnvironmentProperties(allureEnvironment,pathToAllureResults);
    }

    private void createAndWriteEnvironmentProperties(HashMap<String, String> allureEnvironment, String pathToAllureResults){
        try (OutputStream outputStream = new FileOutputStream(pathToAllureResults)) {
            Properties allureProperties = new Properties();

            for (Map.Entry<String, String> property : allureEnvironment.entrySet()){
                allureProperties.setProperty(property.getKey(),property.getValue());
            }

            allureProperties.store(outputStream,null);
            System.out.println("Allure environment data successfully saved in " + pathToAllureResults);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    private void createAllureResultsDirectoryIfNotExists(File allureResultsDirectory){
        try {
            if (!allureResultsDirectory.exists()) {
                boolean resultOfCreatingDirectory = allureResultsDirectory.mkdir();
                if (!resultOfCreatingDirectory) throw new IOException("Cannot create allure-result directory");
            }
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
