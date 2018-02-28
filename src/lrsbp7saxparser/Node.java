/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrsbp7saxparser;

import java.util.ArrayList;

/**
 *
 * @author Lydia
 */
public class Node {
    private ArrayList<Attribute> attributes;
    private ArrayList<Node> children;
    private String title;
    private String data;
    private Node parent;
    
    public Node() {
        attributes = new ArrayList<>();
        children = new ArrayList<>();
        title = "";
        data = "";
        parent = null;
    }
    
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }
    
    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }
    
    public ArrayList<Node> getChildren() {
        return children;
    }
    
    public void addChild(Node child) {
        children.add(child);
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getData() {
        return this.data;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public Node getParent() {
        return this.parent;
    }
}
