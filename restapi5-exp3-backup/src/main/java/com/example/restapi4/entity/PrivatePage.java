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

public class PrivatePage {

    @ApiModelProperty("主页名称")
    private String userName="pageName";

    public String getOldUserName() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/page.xml");
        NodeList nodeList = document.getElementsByTagName("pagename");
        System.out.println("共有："+nodeList.getLength());
        for(int i=0;i<nodeList.getLength();i++){
            Element temp = (Element) nodeList.item(i);
            //String id =     chapter.getAttribute("id");
            this.userName = temp.getFirstChild().getNodeValue();
            System.out.println(this.userName);
            return this.userName;
        }
        return "error";
    }

    public int setNewUserName(String newUserName) throws Exception{
        this.userName = newUserName;
        int flag=0;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/page.xml");

        NodeList nodeList = document.getElementsByTagName("pagename");
        System.out.println("共有："+nodeList.getLength());
        for(int i=0;i<nodeList.getLength();i++){
            Element temp = (Element) nodeList.item(i);
            //String id =     chapter.getAttribute("id");
            System.out.println(temp.getFirstChild().getNodeValue());
            temp.setTextContent(newUserName);
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









    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
