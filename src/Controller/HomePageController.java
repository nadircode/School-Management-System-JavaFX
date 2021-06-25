package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import Controller.HompePage.StudentsController;
import DBConnection.Connex;

public class HomePageController {
    @FXML
    private AnchorPane anchorpaneinfo,menubar;

    @FXML
    private JFXButton studentsbtn,dashboardbtn,infobtn;

    @FXML
    private Label labelname;
    Connection con ;
    Connex conClass = new Connex();
    PreparedStatement ps;

    @FXML
    void DashboardAction(ActionEvent event) throws IOException{
        Parent root =FXMLLoader.load(getClass().getResource("/FXMLfiles/HomePage/Dashboard.fxml"));
        anchorpaneinfo.getChildren().setAll(root);

    }

    @FXML
    void StudentsAction(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/FXMLfiles/HomePage/Students.fxml"));
        anchorpaneinfo.getChildren().setAll(root);
    }

    @FXML
    void infoAction(ActionEvent event) throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("/FXMLfiles/HomePage/SearchInformations.fxml"));
        anchorpaneinfo.getChildren().setAll(root);
    }
    public void showhomepage(String Fname , String pswd) throws SQLException, ClassNotFoundException {
        con = conClass.getConnection();
        String query = "SELECT fname , lname from teacher WHERE fname = ? AND password = ?";
        ps = con.prepareStatement(query);
        ps.setString(1,Fname);
        ps.setString(2,pswd);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            labelname.setText(""+Fname+"  " + rs.getString("lname"));
        }
    }
}
