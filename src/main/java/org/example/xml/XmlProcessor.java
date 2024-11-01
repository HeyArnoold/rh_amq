package org.example.xml;

import org.w3c.dom.Document;

public class XmlProcessor {

    private static final XmlParser parser = new XmlParser();
    private static final XmlModifier modifier = new XmlModifier();

    public static String processXml(String xmlString){
        Document document = parser.xmlStringToDocument(xmlString);
        modifier.modifyXml(document);
        return parser.xmlDocumentToString(document);
    }
}
