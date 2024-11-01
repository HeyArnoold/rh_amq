package org.example.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

class XmlParser {

    Document xmlStringToDocument(String xmlString) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));
            document.setXmlStandalone(true);
            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String xmlDocumentToString(Document xmlDocument) {
        StringWriter writer = new StringWriter();
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }
}
