package com.example.restapi4.entity;

import com.example.restapi4.common.ReturnPasswd;
import io.swagger.annotations.ApiModelProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Login {
    @ApiModelProperty()
    private String acountName ;
    private String acountPasswd;

    public ReturnPasswd loginConfirm() throws Exception{
        String tempName ;
        String tempPasswd ;
        ReturnPasswd rp = new ReturnPasswd();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/login.xml");


        NodeList nodeList = document.getElementsByTagName("acountname");
        Element temp = (Element) nodeList.item(0);
        tempName=temp.getFirstChild().getNodeValue();

        nodeList = document.getElementsByTagName("acountpasswd");
        temp = (Element) nodeList.item(0);
        tempPasswd=temp.getFirstChild().getNodeValue();

        System.out.println("correctAcount:"+tempName);
        System.out.println("inputAcount:"+this.acountName);
        if(!tempName.equals(this.acountName)){
            rp.statusCode="405";
            rp.description="账号错误";
        }else{
            rp.nameCorrect="yes";
            if(!tempPasswd.equals(this.acountPasswd)){
                rp.statusCode = "505";
                rp.description="密码错误";
            }else{
                rp.passwdCorrect="yes";
                rp.statusCode = "200";
                rp.description="校验成功";
            }
        }
        return rp;
    }

    public String getAcountName() {
        return acountName;
    }

    public void setAcountName(String acountName) {
        this.acountName = acountName;
    }

    public String getAcountPasswd() {
        return acountPasswd;
    }

    public void setAcountPasswd(String acountPasswd) {
        this.acountPasswd = acountPasswd;
    }
}
