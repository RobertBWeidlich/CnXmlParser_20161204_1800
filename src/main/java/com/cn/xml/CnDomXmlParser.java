package com.cn.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.io.BufferedReader;

public class CnDomXmlParser {
    private String xmlText = "";
    private FQXmlElement fqe = null;

    public CnDomXmlParser(String xmlPath) throws Exception {
        this.xmlText = this.getStrFromFile(xmlPath);
        this.fqe = new FQXmlElement();

        System.out.println(">>>" + this.xmlText + "<<<");

        this.parseXmlData(this.xmlText);
    }

    private String getStrFromFile(String pathArg) throws java.io.IOException {
        final int BufSz = 4096;
        StringBuilder sb = new StringBuilder(BufSz);
        BufferedReader reader = new BufferedReader(new FileReader(pathArg));

        char[] buf = new char[BufSz];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            sb.append(buf);
        }
        reader.close();
        return sb.toString();
    }

    private void parseXmlData(String xmlData) throws Exception {
        DocumentBuilderFactory dbFact =      DocumentBuilderFactory.newInstance();
        DocumentBuilder        builder =     dbFact.newDocumentBuilder();

        StringReader           sr =      new StringReader(xmlData);
        InputSource            is =      new InputSource(sr);

        Document               doc =         builder.parse(is);

        this.recursiveParseXmlDataNode(doc, 0);
    }

    private void recursiveParseXmlDataNode(Node node, int level) {

    }


    /**
     * convert the org.w3c.dom.Node Enums to string for debugging
     *
     * @param typeI enum value
     * @return string representing typeI
     */
    private String getNodeTypeString(short typeI) {
        switch (typeI) {
            case Node.ELEMENT_NODE:
                return "ELEMENT_NODE";
            case Node.ATTRIBUTE_NODE:
                return "ATTRIBUTE_NODE";
            case Node.TEXT_NODE:
                return "TEXT_NODE";
            case Node.CDATA_SECTION_NODE:
                return "CDATA_SECTION_NODE";
            case Node.ENTITY_REFERENCE_NODE:
                return "ENTITY_REFERENCE_NODE";
            case Node.ENTITY_NODE:
                return "ENTITY_NODE";
            case Node.PROCESSING_INSTRUCTION_NODE:
                return "PROCESSING_INSTRUCTION_NODE";
            case Node.COMMENT_NODE:
                return "COMMENT_NODE";
            case Node.DOCUMENT_NODE:
                return "DOCUMENT_NODE";
            case Node.DOCUMENT_TYPE_NODE:
                return "DOCUMENT_TYPE_NODE";
            case Node.DOCUMENT_FRAGMENT_NODE:
                return "DOCUMENT_FRAGMENT_NODE";
            case Node.NOTATION_NODE:
                return "NOTATION_NODE";
            default:
                return "UNKNOWN_NODE";
        }

    }
}
