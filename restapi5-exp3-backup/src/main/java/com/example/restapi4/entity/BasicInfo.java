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

public class BasicInfo {

    @ApiModelProperty("基本信息")
    private String name ="pageName";
    private String age= "20";
    private String school="unknown";
    private String studentNum="12345678";
    private String college = "CS";


    public BasicInfo getBasicInfo() throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/page.xml");

        NodeList nodeList = document.getElementsByTagName("name");
        Element temp = (Element) nodeList.item(0);
        this.name = temp.getFirstChild().getNodeValue();

        nodeList = document.getElementsByTagName("age");
        temp = (Element) nodeList.item(0);
        this.age = temp.getFirstChild().getNodeValue();

        nodeList = document.getElementsByTagName("school");
        temp = (Element) nodeList.item(0);
        this.school = temp.getFirstChild().getNodeValue();

        nodeList = document.getElementsByTagName("studentnum");
        temp = (Element) nodeList.item(0);
        this.studentNum = temp.getFirstChild().getNodeValue();

        nodeList = document.getElementsByTagName("college");
        temp = (Element) nodeList.item(0);
        this.college = temp.getFirstChild().getNodeValue();

        return this;
    }

    public int setBasicInfo(BasicInfo bi) throws Exception{
        this.name = bi.name;
        this.age = bi.age;
        this.school = bi.school;
        this.studentNum = bi.studentNum;
        this.college = bi.college;

        int flag=0;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/page.xml");


        NodeList nodeList = document.getElementsByTagName("name");
        Element temp = (Element) nodeList.item(0);
        temp.getFirstChild().setNodeValue(this.name);

        nodeList = document.getElementsByTagName("age");
        temp = (Element) nodeList.item(0);
        temp.getFirstChild().setNodeValue(this.age );

        nodeList = document.getElementsByTagName("school");
        temp = (Element) nodeList.item(0);
        temp.getFirstChild().setNodeValue(this.school );

        nodeList = document.getElementsByTagName("studentnum");
        temp = (Element) nodeList.item(0);
        temp.getFirstChild().setNodeValue(this.studentNum );

        nodeList = document.getElementsByTagName("college");
        temp = (Element) nodeList.item(0);
        temp.getFirstChild().setNodeValue(this.college );



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

    public BasicInfo() {
    }

    public BasicInfo(String name, String age, String school, String studentNum, String college) {
        this.name = name;
        this.age = age;
        this.school = school;
        this.studentNum = studentNum;
        this.college = college;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
