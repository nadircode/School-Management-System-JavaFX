package Controller.HompePage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import DBConnection.Connex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletestudentsController {
    @FXML
    private JFXTextField idnumb,fnameS,lnameS;

    @FXML
    private JFXCheckBox areyousure;

    @FXML
    private JFXButton deleteS,exitS;

    Connex conClass = new Connex();
    Connection con ;
    PreparedStatement ps;


    @FXML
    void deleteAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(areyousure.isSelected()) {
            con = conClass.getConnection();
            String requette = "DELETE FROM students WHERE id = ?";
            ps = con.prepareStatement(requette);
            ps.setString(1, idnumb.getText());

            ps.executeUpdate();
            deleteS.getScene().getWindow().hide();
        }
        else {
            System.out.println("you are not sure");
        }

    }
}
