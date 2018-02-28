/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrsbp7saxparser;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Lydia
 */
public class XMLLoader {
    public static Node load(File xmlFile) throws Exception {
        Node rootNode = new Node();
        rootNode.setTitle("Root");
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                Node parentNode = null;
                Node currentNode = rootNode;
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    //System.out.println("Start element:" + qName);
                    
                    parentNode = currentNode;
                    currentNode = new Node();
                    currentNode.setParent(parentNode);
                    currentNode.setTitle(qName);
                    //System.out.println(qName);
                    int attributeNum = attributes.getLength();
                    int i;
                    for(i = 0; i < attributeNum; i++ ){
                        Attribute attribute = new Attribute();
                        attribute.setTitle(attributes.getQName(i));
                        attribute.setData(attributes.getValue(i));
                        //System.out.println("Attribute" + i +": " + attribute.getTitle() + ", " + attribute.getData() + "\n");
                        currentNode.addAttribute(attribute);
                    }
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    // at some point add child node to parent
                    //System.out.println("End element:" + qName);
                    if(parentNode != null) {
                        parentNode.addChild(currentNode);
                        System.out.println("Parent node is not null. Parent node:" + parentNode.getTitle() + "\n");
                        currentNode = parentNode;
                        parentNode = currentNode.getParent();
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                   currentNode.setData(new String(ch, start, length));
                }
            };
            
            saxParser.parse(xmlFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
      System.out.println("Size here:" + rootNode.getChildren().size());
      return rootNode; 
    }
}
