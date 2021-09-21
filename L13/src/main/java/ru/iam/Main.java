package ru.iam;

import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.MapperFeature;
//import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        System.out.print("Please enter search keyword: ");
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        URL url = new URL("https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" + name);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        WikiSearch searchResultObj = objectMapper.readValue(url, WikiSearch.class);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        System.out.println("POJO to JSON: " + objectMapper.writeValueAsString(searchResultObj));
//
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String xml = xmlMapper.writeValueAsString(searchResultObj);
//        System.out.println("POJO to XML: " + xml);

 //       System.out.println("POJO to JSON: " + objectMapper.writeValueAsString(searchResultObj.getQuery().getSearch().get(0)));
        System.out.println("Title: " + objectMapper.writeValueAsString(searchResultObj.getQuery().getSearch().get(0).getTitle()));
        System.out.println("Snippet: " + objectMapper.writeValueAsString(searchResultObj.getQuery().getSearch().get(0).getSnippet()));

    }
}
