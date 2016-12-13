package com.cn.xml;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node.*;

public class CnDomXmlParser {
    //private String xmlText = "";
    private FQXmlElement fqe = null;
    private final PrintStream SO = System.out;

    public CnDomXmlParser(String xmlPath) throws Exception {
        //this.xmlText = this.getStrFromFile(xmlPath);
        this.fqe = new FQXmlElement();

        this.parseXmlDataFromFile(xmlPath);
    }

    /**
     * @param pathArg
     * @return String containing all XML data
     * @throws java.io.IOException
     */
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

    private void parseXmlDataFromBuffer(String xmlData) throws Exception {
        // this does not works... try trimming the string to get rid of extraneous stuff after last XML tag
        DocumentBuilderFactory dbFact =      DocumentBuilderFactory.newInstance();
        DocumentBuilder        builder =     dbFact.newDocumentBuilder();
        StringReader           sr =      new StringReader(xmlData);
        InputSource            is =      new InputSource(sr);
        Document               doc =         builder.parse(is);

        this.recursiveParseXmlDataNode(doc, 0);
    }

    private void parseXmlDataFromFile(String xmlPath) throws Exception {
        File fXmlFile =        new File(xmlPath);
        DocumentBuilderFactory     dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder            dbBuilder = dbFactory.newDocumentBuilder();
        Document                   doc =       dbBuilder.parse(fXmlFile);

        this.recursiveParseXmlDataNode(doc, 0);

    }

    private void recursiveParseXmlDataNode(Node node, int level) {
        String indentStr = this.xmlIndent(2, level);
        //SO.println("AAA " + node.toString());

        NodeList nl = node.getChildNodes();
        String   nName = node.getNodeName();
        short    nType = node.getNodeType();

        if (true) {
            String nTypeStr = this.getNodeTypeString(nType);
            SO.println();
            SO.println("RPXD ==========================");
            SO.println("RPXD sz:          " + fqe.size());
            SO.println("RPXD level:       " + level);
            SO.println("RPXD NodeTypeStr: " + nTypeStr);
            SO.println("RPXD NodeName:    " + nName);
            SO.println("RPXD fqe:         " + fqe.getString());
            SO.println("RPXD ");
        }
        if (fqe.size() >= level)
            fqe.pop();

        //if (nType == org.w3.doc.Node.TEXT_NODE) {
        if (nType == Node.TEXT_NODE) {
            String nText = node.getTextContent();
            String trText = nText.trim();
            SO.println(indentStr + "TEXT_NODE");
            SO.println(indentStr + "  sz:" + fqe.size());
            SO.println(indentStr + "  " + ">>>" + trText + "<<<");
        }
        if (nType == Node.ELEMENT_NODE) {
            fqe.push(nName);
            String fqn = fqe.getString();
            SO.println(indentStr + "ELEMENT_NODE + ATTRIBUTES");
            SO.println(indentStr + "  fqe sz: " + fqe.size());
            SO.println(indentStr + "  fqe:    " + fqe.getString());

            SO.println(indentStr + "ATTRIBUTES");
            NamedNodeMap nnm = node.getAttributes();
            if (nnm == null) {
                SO.println(indentStr + "  [none]");
            }
            else {
                int mapSz = nnm.getLength();
                for (int mi = 0; mi < mapSz; mi++) {
                    Node n = nnm.item(mi);
                    String name = n.getNodeName();
                    String value = n.getNodeValue();
                    SO.println(indentStr + "  " + name + "=\"" + value + "\"");
                }
            }

        }


        //
        // iterate on all children
        //
        int childCount = nl.getLength();
        for (int i = 0; i < childCount; i++) {
            recursiveParseXmlDataNode(nl.item(i), level+1);
        }

        int ii = 7;

    }

    /**
     * indentation string for XML debugging
     * @param spl spaces per level of indentation
     * @param level indentation level
     * @return String to use for indentation
     */
    private String xmlIndent(int spl, int level) {
        int  total = spl * level;
        if (total < 0) {
            total = 0;
        }
        else if (total > 100) {
            total = 100;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < total; i++) {
            sb.append(" ");
        }

        return sb.toString();
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
