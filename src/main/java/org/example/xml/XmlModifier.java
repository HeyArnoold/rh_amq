package org.example.xml;

import lombok.extern.log4j.Log4j2;
import org.example.model.GenParsedResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import static org.example.util.RandomDataUtils.genRandomNumbers;
import static org.example.util.RandomDataUtils.genRandomString;

@Log4j2
class XmlModifier {

    private final GenerateNodeValueParser nodeValueParser = new GenerateNodeValueParser();

    void modifyXml(Document xmlDocument) {
        NodeList nodeList = findNodeListToGen(xmlDocument);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            log.info("Найден тег для генерации значения: {}", node.getNodeName());

            GenParsedResult genResult = nodeValueParser.parseNodeValue(node.getTextContent());
            String genString = genValue(genResult);
            node.setTextContent(genString);

            log.info("Значение тега {} изменено на : {}", node.getNodeName(), genString);
        }
    }

    private NodeList findNodeListToGen(Document document) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "//*[starts-with(text(),'{generate')]";
        try {
            return (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private String genValue(GenParsedResult result) {
        if (result.getType() == TypeToGen.STRING) {
            return genRandomString(result.getMin(), result.getMax());
        }
        if (result.getType() == TypeToGen.INT) {
            return genRandomNumbers(result.getMin(), result.getMax());
        }
        return null;
    }
}
