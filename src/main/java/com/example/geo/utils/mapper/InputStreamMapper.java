package com.example.geo.utils.mapper;

import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputStreamMapper {

    public InputStreamMapper() throws ParserConfigurationException {
    }

    public static List<String> convertToListString(InputStream is){
        List<String> list = new ArrayList<>();

        Scanner sc = new Scanner(is);

        while(sc.hasNext()){
            list.add(sc.nextLine());
        }

        if(CollectionUtils.isEmpty(list)){
            list = null;
        }

        return list;
    }

    public static Document convertToXMLDocument(InputStream is) throws ParserConfigurationException, IOException, SAXException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document dc= parser.parse(is);

        return dc;
    }
}
