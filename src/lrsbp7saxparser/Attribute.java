/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrsbp7saxparser;

/**
 *
 * @author Lydia
 */
public class Attribute {
    private String title;
    private String data;
    
    public Attribute() {
        data = "";
        title = "";
    }
    
    public Attribute(String title, String data) {
        this.data = data;
        this.title = title;
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
}
