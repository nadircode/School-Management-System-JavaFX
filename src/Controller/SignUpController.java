package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import java.sql.SQLException;

import DBConnection.Connex;

public class SignUpController {
    @FXML
    private JFXTextField firstname , lastname , emailS;

    @FXML
    private JFXPasswordField pswd , pswdconfirm;

    @FXML
    private JFXButton loginbtn , signupbtn;

    @FXML
    private JFXCheckBox agreecheck;

    Connection con ;
    Connex conClass = new Connex();
    PreparedStatement ps ;


    @FXML
    void loginAction(ActionEvent event) throws IOException {
        loginbtn.getScene().getWindow().hide();
        Stage st2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLfiles/Login.fxml"));
        st2.setScene(new Scene(root,734,504));
        st2.show();
    }

    @FXML
    void signupAction(ActionEvent event) throws SQLException, NullPointerException, ClassNotFoundException {
        if(agreecheck.isSelected()){
            con = conClass.getConnection();
            String requette = "INSERT INTO teacher(fname,lname,email,password) VALUES (?,?,?,?)";
            ps = con.prepareStatement(requette);
            ps.setString(1,firstname.getText());
            ps.setString(2,lastname.getText());
            ps.setString(3,emailS.getText());
            ps.setString(4,pswd.getText());

            ps.executeUpdate();
            System.out.println("Data insert");
        }
        else {
            System.out.println("Error agree terms and conditions");
        }

    }
}
