package com.example.restapi4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String tempName ;
        String tempPasswd ;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse("./src/main/resources/static/login.xml");
        NodeList nodeList = document.getElementsByTagName("acountname");
        Element temp = (Element) nodeList.item(0);
        tempName=temp.getFirstChild().getNodeValue();
        nodeList = document.getElementsByTagName("acountpasswd");
        temp = (Element) nodeList.item(0);
        tempPasswd=temp.getFirstChild().getNodeValue();

        auth.inMemoryAuthentication()
                .withUser(tempName)
                .password("{noop}"+tempPasswd) //123
                .roles("admin")
                .and()
                .withUser("sang")
                .password("{noop}333") //123
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**").authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and().csrf().disable();
    }
}
