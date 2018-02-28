/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrsbp7saxparser;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

/**
 *
 * @author Lydia
 */
public class SAXParserFXMLController implements Initializable {
    
        @FXML
    private TextArea textArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file != null) {
            try
            {
                Node rootNode = XMLLoader.load(file);
                Node tree = rootNode.getChildren().get(0);
                traverseTree(tree, -4);  
                
            } catch (Exception ex) {
                displayExceptionAlert("Exception parsing XML file.", ex);
            }
        }
    }
    
    private int traverseTree(Node tree, int indent){
        if(tree.getChildren().isEmpty() == true )
        {
            return indent;
        }
        
        indent +=4;
        ArrayList<Node> children = tree.getChildren();
        int count = children.size();
        
        for ( int i = 0; i < count; i++ ) {
            
            if(indent == 0)
            {
                textArea.appendText("\n-------------------------\n"); 
            }
            
            for( int j = 0; j < indent; j++ )
            {
                textArea.appendText(" ");
            }
            
            Node child = children.get(i);
            int test = child.getData().length();
            System.out.println(child.getTitle() + " length of string: " + test + child.getData());
            
            textArea.appendText(child.getTitle() + ": " + child.getData() + "\n");
            
            if( child.getAttributes().isEmpty() == false){
                for( int j = 0; j < child.getAttributes().size(); j++){
                    for( int q = 0; q < indent; q++ )
                    {
                        textArea.appendText(" ");
                    }
                    Attribute attribute = child.getAttributes().get(j);
                    textArea.appendText("->" + attribute.getTitle() + ": " + attribute.getData() + "\n");
                }
            }
            
            traverseTree(child, indent);
        }
        return indent-=4;
    }
    
    @FXML
    private void handleAbout(ActionEvent event) {
        displayAboutAlert();
    }
    
    private void displayAboutAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("XML DOM Parser Example");
        alert.setContentText("This application was developed by Dale Musser for CS4330 at the University of Missouri.");
        
        TextArea textArea = new TextArea("This example illustrates how to parse XML using the DOM.");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    
}
