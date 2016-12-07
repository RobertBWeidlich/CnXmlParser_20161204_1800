package com.cn.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ExDom01 {

    public ExDom01(String xmlPath) throws Exception {
        File fXmlFile = new File(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder        dbBuilder = dbFactory.newDocumentBuilder();
        Document               doc =       dbBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nl =       doc.getChildNodes();
        String   nodeName = doc.getNodeName();
        short    nType =    doc.getNodeType();

        System.out.println(doc.toString());

    }



    public static void main(String[] args) throws Exception {
        ExDom01 ed = new ExDom01("C:\\data\\xml\\b.xml");


    }

}

