package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.Connex;

public class LoginController {
    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXPasswordField paswd;

    @FXML
    private JFXButton loginbtn,signupbtn;

    private Parent root;
    Connection con;
    Connex conClass = new Connex();
    PreparedStatement ps;


    @FXML
    void loginAction(ActionEvent event) throws NullPointerException , SQLException ,  ClassNotFoundException , IOException{
        con = conClass.getConnection();
        String requette = "SELECT * FROM teacher WHERE fname = ? AND password = ?";
        ps = con.prepareStatement(requette);
        ps.setString(1,firstname.getText());
        ps.setString(2,paswd.getText());

        ResultSet rs = ps.executeQuery();
        int count =0;


        while(rs.next()){
            count = count + 1;
        }
        if(count==1){
            loginbtn.getScene().getWindow().hide();
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLfiles/HomePage.fxml"));
            root = loader.load();
            HomePageController home = loader.getController();
            home.showhomepage(firstname.getText() , paswd.getText());
            st.setScene(new Scene(root,900,600));
            st.show();
        }
        else {
            System.out.println("Count not found");
        }

    }

    @FXML
    void signUpAction(ActionEvent event) throws IOException {
        signupbtn.getScene().getWindow().hide();
        Stage st = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLfiles/SignUp.fxml"));
        st.setScene(new Scene(root,734,504));
        st.show();



    }
}
