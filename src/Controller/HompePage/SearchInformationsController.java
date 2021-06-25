package Controller.HompePage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import DBConnection.Connex;
import BasicClass.Students;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import BasicClass.Students;

public class SearchInformationsController implements Initializable {
    @FXML
    private AnchorPane anchorpaneInfo,hbox;

    @FXML
    private JFXTextField fnameS,lnameS,idnumbS;

    @FXML
    private JFXButton searchbtn;
    private AnchorPane root;
    FXMLLoader loader ;

    Connection con ;
    Connex conClass = new Connex();
    PreparedStatement ps;
    int k = 0;
    int i = 0;



    @FXML
    void searchAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        con = conClass.getConnection();
        String query = "SELECT id , fnameS , lnameS FROM students where id = ?";
        ps = con.prepareStatement(query);
        ps.setString(1,idnumbS.getText());
        String idnew = idnumbS.getText();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            k++;
        }
        if(k == 1){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLfiles/HomePage/ShowInfoStudents.fxml"));
            root = loader.load();
            ShowInfoStudentsController show = loader.getController();
            show.showInfo(idnumbS.getText());
            anchorpaneInfo.getChildren().setAll(root);

        }
        else {
            System.out.println("votre compte n'existe pas");
        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
