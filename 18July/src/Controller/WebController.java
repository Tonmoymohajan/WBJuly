/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Shawon
 */
public class WebController implements Initializable {

    @FXML
    private TextField txt;
    @FXML
    private Button bt;
    @FXML
    private AnchorPane anc;
    @FXML
    private WebView webView;
    public String[] name = new String[100];
    static int flag;
    String Url;

    /**
     * Initializes the controller class.
     */
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        WebEngine we = webView.getEngine();
        we.setJavaScriptEnabled(true);
flag=0;
        EventHandler<ActionEvent> enter;
        enter = new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //String url = new String(txt.getText().startsWith("http://") ? txt.getText() : "http://" + txt.getText());
                
                if(txt.getText().startsWith("http://")){
                    Url=new String(txt.getText());
                }
                else if(txt.getText().startsWith("www.")){
                    Url=new String("http://"+txt.getText());
                }
                else{
                    Url=new String("http://www."+txt.getText());
                }
                
                // public void readerNewUser() {
                try (BufferedReader reader = new BufferedReader(new FileReader(new File("users.txt")))) {
                    
                    
                    String line;
                    int i = 0;
                    while ((line = reader.readLine()) != null) {
                        name[i] = line;
                        System.out.println("Here "+name[i]);
                        if(Url.equalsIgnoreCase(name[i])){
                            flag=1;
                            
                        }
                        i++;
                        // System.out.println(line);
                    }
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                
                
                //if (url.equalsIgnoreCase("http://xyz.com")) {
                //if (url.equalsIgnoreCase(name[i])) {
                if(flag==1){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Access Denied");
                    alert.setHeaderText("Sorry! This website is blocked for containing vulgar content");
                    alert.setContentText("Try to visit other website!");

                    alert.showAndWait();
                } else {
                     
                    we.load(Url);
                    System.out.println("Sorry to interpt!"+Url);
                }

            }
        };
        txt.setOnAction(enter);
        bt.setOnAction(enter);
        we.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txt.setText(newValue);
            }
        });

    }

    @FXML
    private void handle(ActionEvent event) {

        //txt.setText("Webview");
    }

}
