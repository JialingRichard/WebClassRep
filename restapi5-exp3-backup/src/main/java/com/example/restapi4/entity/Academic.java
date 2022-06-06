package com.example.restapi4.entity;

import io.swagger.annotations.ApiModelProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class Academic {

    @ApiModelProperty("主页名称")
    private String academicExp ="pageName";

    public String getOldAcademic() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/page.xml");
        NodeList nodeList = document.getElementsByTagName("academic");
        for(int i=0;i<nodeList.getLength();i++){
            Element temp = (Element) nodeList.item(i);
            //String id =     chapter.getAttribute("id");
            this.academicExp = temp.getFirstChild().getNodeValue();
            System.out.println(this.academicExp);
            return this.academicExp;
        }
        return "error";
    }

    public int setNewAcademic() throws Exception{

        int flag=0;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/page.xml");

        NodeList nodeList = document.getElementsByTagName("academic");

        for(int i=0;i<nodeList.getLength();i++){
            Element temp = (Element) nodeList.item(i);
            //String id =     chapter.getAttribute("id");
            System.out.println(temp.getFirstChild().getNodeValue());
            temp.setTextContent(this.academicExp);
            break;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource xmlSource = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream("./src/main/resources/static/page.xml");
        StreamResult streamResult = new StreamResult(fos);
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        transformer.transform(xmlSource, streamResult);
        fos.close();

        return flag;
    }

    public String getAcademicExp() {
        return academicExp;
    }

    public void setAcademicExp(String academicExp) {
        this.academicExp = academicExp;
    }
}