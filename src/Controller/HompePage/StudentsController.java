package Controller.HompePage;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import BasicClass.Students;
import DBConnection.Connex;

public class StudentsController implements Initializable {
    @FXML
    private AnchorPane anchorpane,hbox;


    @FXML
    private Label studentslabel;

    @FXML
    private JFXButton addbtn,deletebtn;


    @FXML
    private TableView<Students> TableshowS;

    @FXML
    private TableColumn<Students , String> id_S;

    @FXML
    private TableColumn<Students , String> fname_S;

    @FXML
    private TableColumn<Students , String> lname_S;

    @FXML
    private TableColumn<Students , String> levelT;

    Connection con ;
    Connex conClass = new Connex();
    PreparedStatement ps;


    @FXML
    void addAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLfiles/HomePage/Addstudents.fxml"));
        stage.setScene(new Scene(root,600,645));
        stage.show();

    }
    @FXML
    void deleteAction(ActionEvent event) throws IOException {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLfiles/HomePage/Deletestudents.fxml"));
        st.setScene(new Scene(root,506,400));
        st.show();
    }

    public void showStudents() throws SQLException, ClassNotFoundException {
        con = conClass.getConnection();
        ObservableList <Students> data = FXCollections.observableArrayList();
        TableshowS.setItems(data);

        String requette = "SELECT * FROM students";
        ps = con.prepareStatement(requette);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Students S = new Students(rs.getString("id"), rs.getString("fnameS"),rs.getString("lnameS"), rs.getString("level"));
            data.add(S);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showStudents();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        id_S.setCellValueFactory(new PropertyValueFactory<Students, String>("id"));
        fname_S.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        lname_S.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));
        levelT.setCellValueFactory(new PropertyValueFactory<Students, String>("level"));

    }
}
