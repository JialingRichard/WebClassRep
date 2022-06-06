package com.example.restapi4.entity;

import com.example.restapi4.common.Project;
import com.example.restapi4.common.Projects;
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

import static com.example.restapi4.common.PageUtil.startPage;

public class Grade {
    public Projects getExp() throws Exception {
        Projects p = new Projects();

        int flag=0;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/grade.xml");

        NodeList nodeList = document.getElementsByTagName("name");
        NodeList nodeList2 = document.getElementsByTagName("description");
        for(int i=0;i<nodeList.getLength();i++){
            Element temp = (Element) nodeList.item(i);
            Project pp = new Project();
            pp.name = temp.getFirstChild().getNodeValue();
            temp = (Element) nodeList2.item(i);
            pp.description = temp.getFirstChild().getNodeValue();
            p.ll.add(pp);
        }

        //分页
        p.ll = startPage(p.ll, 9, 2);

        return p;
    }


    public Projects setExp(Projects p) throws Exception {

        int flag=0;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        //Document document = documentBuilder.parse("./src/main/resources/static/project.xml");
        Document document = documentBuilder.newDocument();
        Element elementP = document.createElement("p");

        for(int i=0;i<p.ll.size();i++){
            String name = p.ll.get(i).name;
            String description = p.ll.get(i).description;

            Project pp = new Project();
            //  pp.name = temp.getElementsByTagName("name").item(0).getNodeValue();
            //  pp.description = temp.getElementsByTagName("description").item(0).getNodeValue();
            pp.name = name;
            pp.description = description;
            Element elementProject = document.createElement("project");
            Element elementName = document.createElement("name");
            elementName.setTextContent(pp.name);
            Element elementDes = document.createElement("description");
            elementDes.setTextContent(pp.description);
            elementProject.appendChild(elementName);
            elementProject.appendChild(elementDes);
            elementP.appendChild(elementProject);
            System.out.println(pp.name+" "+pp.description);


        }

        document.appendChild(elementP);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource xmlSource = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream("./src/main/resources/static/grade.xml");
        StreamResult streamResult = new StreamResult(fos);
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlSource, streamResult);
        fos.close();

        return p;
    }

}
