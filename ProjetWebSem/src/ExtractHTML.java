
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ExtractHTML {
	public static void main(String[] args) throws XPathExpressionException {
		String root = "test.html";
		String result = ExtractParagraphFromHTML(root);
		System.out.println(result);
	}

	public static String ExtractParagraphFromHTML(String source) throws XPathExpressionException {
		String result = new String();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File fileHTML = new File(source);
			Document html = builder.parse(fileHTML);
			Element root = html.getDocumentElement();
			XPathFactory xpf = XPathFactory.newInstance();
			XPath path = xpf.newXPath();
			String expression = "//body//p";
			result = (String) path.evaluate(expression, root);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return result;
	}

}
