package com.cn.xml;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception {
        FQXmlElement fqe = new FQXmlElement();

        //final String xmlPath = "C:\\data\\xml\\cn_rss_raw-news.ap.politics-20161206.0008.13.xml";
        final String xmlPath = "C:\\data\\xml\\b.xml";
        CnDomXmlParser dxp = new CnDomXmlParser(xmlPath);
    }
}
