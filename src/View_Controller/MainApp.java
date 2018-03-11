/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.util.Locale;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author William
 */
public class MainApp extends Application {
    
    



    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Locale.getDefault() == Locale.FRANCE);
        if (Locale.getDefault().equals(Locale.FRANCE)) {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreenFR.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
    public void exit() {
        
        Platform.exit();
    }
    

    
    public static void main(String[] args) {
        
        //Locale.setDefault(new Locale("fr", "FR"));
        
        launch(args);
        
        
        
    }
    
    
    
}
