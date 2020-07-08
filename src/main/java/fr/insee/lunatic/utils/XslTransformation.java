package fr.insee.lunatic.utils;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main Saxon Service used to perform XSLT transformations
 * 
 * @author gerose
 *
 */
public class XslTransformation {

	final static Logger logger = LoggerFactory.getLogger(XslTransformation.class);

	/**
	 * Main Saxon transformation method
	 * 
	 * @param transformer
	 *            : The defined transformer with his embedded parameters (defined in
	 *            the other methods of this class)
	 * @param xmlInput
	 *            : The input xml file where the XSLT will be applied
	 * @param xmlOutput
	 *            : The output xml file after the transformation
	 * @throws Exception
	 *             : Mainly if the input/output files path are incorrect
	 */
	public void xslTransform(Transformer transformer, InputStream xmlInput, OutputStream xmlOutput) throws Exception {
		logger.debug("Starting xsl transformation -Input : " + xmlInput + " -Output : " + xmlOutput);
		transformer.transform(new StreamSource(xmlInput), new StreamResult(xmlOutput));
	}

	
	public void transformXMLLunaticToXMLLunaticFlat(InputStream inputFile, OutputStream outputFile, InputStream xslSheet) throws Exception {
		logger.info("Converting XMLLunaticXML to a XMLLunaticFlat ...");
		TransformerFactory tFactory = new net.sf.saxon.TransformerFactoryImpl();
		Transformer transformer = tFactory.newTransformer(new StreamSource(xslSheet));

		xslTransform(transformer, inputFile, outputFile);
	}
	
	public void transformJSONLunaticToJSONLunaticClean(InputStream inputFile, OutputStream outputFile, InputStream xslSheet) throws Exception {
		logger.info("Cleaning json output ...");
		TransformerFactory tFactory = new net.sf.saxon.TransformerFactoryImpl();
		Transformer transformer = tFactory.newTransformer(new StreamSource(xslSheet));

		xslTransform(transformer, inputFile, outputFile);
	}
	
	public void transformXMLLunaticDataToJSON(InputStream inputFile, OutputStream outputFile, InputStream xslSheet) throws Exception {
		logger.info("Lunatic data : xml to json ...");
		TransformerFactory tFactory = new net.sf.saxon.TransformerFactoryImpl();
		Transformer transformer = tFactory.newTransformer(new StreamSource(xslSheet));
		transformer.setParameter("javaCall", true);
		xslTransform(transformer, inputFile, outputFile);
	}

	public void transformWithSimpleXSLSheet(InputStream inputFile, OutputStream outputFile, InputStream xslSheet) throws Exception {
		logger.info("Transforming ...");
		TransformerFactory tFactory = new net.sf.saxon.TransformerFactoryImpl();
		Transformer transformer = tFactory.newTransformer(new StreamSource(xslSheet));
		
		xslTransform(transformer, inputFile, outputFile);
	}

}
