package com.example.restapi4;

import com.example.restapi4.entity.BasicInfo;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.FileOutputStream;

public class testXml {

    @Test
    public BasicInfo main1111() throws Exception {

        BasicInfo bi = new BasicInfo();
        bi.getBasicInfo();
        System.out.println("hello");
        return bi;
    }



    @Test
    public void test() throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(".\\src\\main\\resources\\xmlDir\\a.xml");
        NodeList nodeList = document.getElementsByTagName("name");
        System.out.println("共有："+nodeList.getLength());
        for(int i=0;i<nodeList.getLength();i++){
            Element chapter = (Element) nodeList.item(i);
            String id =     chapter.getAttribute("id");
            if("1".equals(id)){
                chapter.getFirstChild();
                System.out.println(chapter.getFirstChild().getNodeValue());
            }
        }
    }

    @Test
    public void test1() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(".\\src\\main\\resources\\xmlDir\\a.xml");


        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        String ss = "//name[@id='1']//text()";
        XPathExpression expression =xPath.compile(ss);

        Object o = expression.evaluate(document, XPathConstants.NODE);
        Node node = (Node) o;
        System.out.println(node.getNodeValue());


    }

    @Test
    public void test2() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element content = document.createElement("content");
        Element website = document.createElement("website");
        Element website1 = document.createElement("website1");

        website.setTextContent("nice.com");
        website.appendChild(website1);
        content.appendChild(website);

        document.appendChild(content);


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource xmlSource = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream(".\\src\\main\\resources\\static\\b.xml");
        StreamResult streamResult = new StreamResult(fos);
        transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
//        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");

        transformer.transform(xmlSource,streamResult);
        fos.close();

    }

    @Test
    public void test3() throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(".\\src\\main\\resources\\xmlDir\\b.xml");

//        Element content = document.createElement("content");
//        Element website = document.createElement("website");
//        Element website1 = document.createElement("website1");
//
//        website.setTextContent("nice.com");
//        website.appendChild(website1);
//        content.appendChild(website);
//
//        document.appendChild(content);

        NodeList nodeList = document.getElementsByTagName("name");
        System.out.println("共有："+nodeList.getLength());
        for(int i=0;i<nodeList.getLength();i++){
            Element chapter = (Element) nodeList.item(i);
            String id =     chapter.getAttribute("id");
            if("1".equals(id)){
                chapter.getFirstChild();
                System.out.println(chapter.getFirstChild().getNodeValue());
                chapter.setTextContent("ahhaah");

                Node nodeDel=nodeList.item(i);
                nodeDel.getParentNode().removeChild(nodeDel);
            }
        }



        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource xmlSource = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream(".\\src\\main\\resources\\xmlDir\\b.xml");
        StreamResult streamResult = new StreamResult(fos);
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
//        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");

        transformer.transform(xmlSource, streamResult);
        fos.close();
    }
}
