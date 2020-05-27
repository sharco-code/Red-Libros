package utiles.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifyXMLFile {

	public static void modifyXML(String filepath,String ip, String port, String username, String password) {

	   try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		
		
		NodeList elements = doc.getElementsByTagName("property");
		
		for (int i = 0; i < elements.getLength(); i++) {
			NamedNodeMap attr = elements.item(i).getAttributes();
			Node nodeAttr = attr.getNamedItem("name");
			if(nodeAttr.getTextContent().equals("connection.username")) {
				elements.item(i).setTextContent(username);
			}
			if(nodeAttr.getTextContent().equals("connection.password")) {
				elements.item(i).setTextContent(password);
			}
			if(nodeAttr.getTextContent().equals("connection.url")) {
				elements.item(i).setTextContent("jdbc:mysql://"+ip+":"+port+"/instituto");
			}
			
			
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
		
		
	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
	}
}
