package peanut.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import peanut.Peanut;

/**
 * A GUI for Peanut using FXML.
 */
public class Main extends Application {

    private Peanut peanut = new Peanut("data/peanut.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Peanut");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(peanut); // inject the Peanut instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




