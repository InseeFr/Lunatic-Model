package fr.insee.lunatic.test;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.data.JSONLunaticDataToXML;
import fr.insee.lunatic.conversion.data.XMLLunaticDataToJSON;
import fr.insee.lunatic.conversion.data.XMLLunaticToXMLEmptyData;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.diff.Diff;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LunaticXmlToDataTest {

    private XMLDiff xmlDiff = new XMLDiff();

    private static final Logger logger = LoggerFactory.getLogger(LunaticXmlToDataTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }


    @Test
    public void testLunaticXml2EmptyXmlData() {
        logger.debug("Launch test : lunaticXML to empty xml data");
        try {
            String basePath = Constants.RESOURCES_FOLDER_DUMMY_PATH;

            File in = new File(String.format("%s/form.xml",  basePath));

            XMLLunaticToXMLEmptyData xmlLunaticToXMLEmptyData = new XMLLunaticToXMLEmptyData();
            File xmlOut = xmlLunaticToXMLEmptyData.transform(in);
            logger.debug("File generated at : "+xmlOut.toString());

            File expectedFile = new File(String.format("%s/out/questionnaire-data.xml", Constants.RESOURCES_FOLDER_DATA_PATH));
            Diff diff = xmlDiff.getDiff(xmlOut,expectedFile);
            Assert.assertFalse(getDiffMessage(diff, basePath), diff.hasDifferences());

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void testLunaticXml2EmptyJsonData() {
        logger.debug("Launch test : lunaticXML to empty json data");
        try {
            String basePath = Constants.RESOURCES_FOLDER_DUMMY_PATH;

            File in = new File(String.format("%s/form.xml",  basePath));

            XMLLunaticToXMLEmptyData xmlLunaticToXMLEmptyData = new XMLLunaticToXMLEmptyData();
            XMLLunaticDataToJSON xmlLunaticDataToJSON = new XMLLunaticDataToJSON();
            File xmlOut = xmlLunaticToXMLEmptyData.transform(in);
            logger.debug("File generated at : "+xmlOut.toString());
            File jsonData = xmlLunaticDataToJSON.transform(xmlOut);
            logger.debug("File generated at : "+jsonData.toString());

            JSONObject jsonOut = new JSONObject(FileUtils.readFileToString(jsonData, StandardCharsets.UTF_8));


            Path expectedFile = Paths.get(String.format("%s/out/questionnaire-data.json", Constants.RESOURCES_FOLDER_DATA_PATH));
            String jsonExpectedString = new String(Files.readAllBytes(expectedFile),StandardCharsets.UTF_8);

            JSONAssert.assertEquals(jsonExpectedString, jsonOut, true);

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }



    private String getDiffMessage(Diff diff, String path) {
        return String.format("Transformed output for %s should match expected XML document:\n %s", path,
                diff.toString());
    }
}
