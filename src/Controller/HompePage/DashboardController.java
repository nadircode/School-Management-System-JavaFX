package Controller.HompePage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DBConnection.Connex;

public class DashboardController implements Initializable {
    @FXML
    private AnchorPane hbox;

    @FXML
    private Label studentslabel,nmbrelabel;

    Connection con;
    Connex conClass = new Connex();
    PreparedStatement ps;

    public void Connexio() throws SQLException, ClassNotFoundException {
        con = conClass.getConnection();
        String requette = "SELECT * FROM students";
        ps = con.prepareStatement(requette);
        int count = 0;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            count ++ ;
        }
        nmbrelabel.setText(""+count);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connexio();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
