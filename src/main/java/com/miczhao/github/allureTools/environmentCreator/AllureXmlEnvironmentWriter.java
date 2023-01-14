package com.miczhao.github.allureTools.environmentCreator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllureXmlEnvironmentWriter {

    private Document allureXmlEnvironment;

    public AllureXmlEnvironmentWriter(){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            allureXmlEnvironment = documentBuilder.newDocument();
        } catch (ParserConfigurationException parserConfigurationException){
            System.out.println("Сannot create DocumentBuilder which satisfies the configuration requested");
            parserConfigurationException.printStackTrace();
        }
    }

    /**
     * Creates environment.xml file in default folder "/target/allure-results/"
     * @param allureEnvironment - map of environment properties
     */
    public void createAllureEnvironment(HashMap<String, String> allureEnvironment) {
        createAllureEnvironment(allureEnvironment, System.getProperty("user.dir") + "/target/allure-results");
    }

    /**
     * Creates environment.xml file in custom folder
     * @param allureEnvironment - map of environment properties
     * @param pathToAllureResults - path to folder "allure-results"
     */
    public void createAllureEnvironment(HashMap<String, String> allureEnvironment, String pathToAllureResults) {
        writeAllPropertiesToAllureEnvironment(allureEnvironment);
        saveAllureEnvironmentXml(pathToAllureResults);
    }

    private void writeAllPropertiesToAllureEnvironment(Map<String, String> allureEnvironment){
        Element environment = allureXmlEnvironment.createElement("environment");
        allureXmlEnvironment.appendChild(environment);

        Element parameter;
        Element key;
        Element value;

        for (Map.Entry<String, String> environmentVariable : allureEnvironment.entrySet()) {
            parameter = allureXmlEnvironment.createElement("parameter");

            key = allureXmlEnvironment.createElement("key");
            key.setTextContent(environmentVariable.getKey());
            parameter.appendChild(key);

            value = allureXmlEnvironment.createElement("value");
            value.setTextContent(environmentVariable.getValue());
            parameter.appendChild(value);

            environment.appendChild(parameter);
        }
    }

    private void saveAllureEnvironmentXml(String pathToAllureResults){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource allureResultsDirectorySource = new DOMSource(allureXmlEnvironment);

            File allureResultsDirectory = new File(pathToAllureResults);
            createAllureResultsDirectoryIfNotExists(allureResultsDirectory);

            StreamResult resultOfCreatingEnvironment = new StreamResult(allureResultsDirectory + "/environment.xml");
            transformer.transform(allureResultsDirectorySource, resultOfCreatingEnvironment);

            System.out.println("Allure environment data successfully saved in " + pathToAllureResults);
        } catch (TransformerConfigurationException transformerConfigurationException) {
            System.out.println("Сannot create TransformerFactory which satisfies the configuration requested");
            transformerConfigurationException.printStackTrace();
        } catch (TransformerException transformerException) {
            System.out.println("Cannot write data into file");
            transformerException.printStackTrace();
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
