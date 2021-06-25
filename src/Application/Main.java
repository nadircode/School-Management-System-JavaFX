package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application{

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLfiles/Login.fxml"));
        primaryStage.setTitle("Login ");
        primaryStage.setScene(new Scene(root, 734, 504));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
