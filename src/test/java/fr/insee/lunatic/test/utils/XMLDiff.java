package fr.insee.lunatic.test.utils;
import java.io.File;

import javax.xml.transform.stream.StreamSource;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;

/**
 * Created by I6VWID on 15/01/18.
 */
public class XMLDiff { 

    public XMLDiff() {
        
    }
      
    public Diff getDiff(File input, File expected) throws Exception {
        System.out.println(String.format("Diff  %s with %s", input.getAbsolutePath(), expected.getAbsolutePath()));
        StreamSource inputStream = null;
        StreamSource expectedStream = null; 
        try {
        	inputStream = new StreamSource(input);
        	expectedStream = new StreamSource(expected);
        	
            return DiffBuilder
                    .compare(expectedStream)
                    .withAttributeFilter(attr -> !attr.getName().equals("enoCoreVersion"))
                    .withAttributeFilter(attr -> !attr.getName().equals("lunaticModelVersion"))
                    .withTest(inputStream)
                    .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName))
                    .withAttributeFilter(att -> !(att.getName().equals("min") || att.getName().equals("max")))
                    .normalizeWhitespace()
                    .ignoreWhitespace()
                    .build();
        } catch (Exception e) {
            throw e;
        } 
    }

    public Diff getDiff(String inputFilePath, String expectedFilePath) throws Exception {
        File inputFile = new File(inputFilePath);
        File expectedFile = new File(expectedFilePath);
        try {
            return getDiff(inputFile, expectedFile);
        } catch (Exception e) {
            throw e;
        }
    }

}