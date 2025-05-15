package com.ssafy.exam.controller;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class JacksonXmlTest {
    public static void main(String[] args) throws Exception {
        String xml = "<root><value>123</value></root>";
        XmlMapper mapper = new XmlMapper();
        JsonNode node = mapper.readTree(xml);
        System.out.println(node.toPrettyString());
    }
}