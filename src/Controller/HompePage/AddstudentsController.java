package Controller.HompePage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;

import java.awt.*;


import javafx.scene.ImageCursor;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DBConnection.Connex;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddstudentsController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXTextField fnameS,lnameS,emailS,idnumb;

    @FXML
    private ChoiceBox<String> wilaya,leveLS;

    @FXML
    private JFXDatePicker datebirth=null;

    @FXML
    private JFXButton uploadphoto,addS,exitS;

    @FXML
    private JFXRadioButton manS,womenS;

    @FXML
    private ImageView imageview;

    @FXML
    private Label imglabel;


    String[] wilayachoice = {"Tlemcen","Oran","Alger"} ;
    String[] levelchoice = {"L1","L2","L3"};

    private FileChooser fileChooser;
    private File file;
    private Image image;
    Stage stage;
    private FileInputStream fis;

    Connection con ;
    Connex conClass = new Connex();
    PreparedStatement ps;


    @FXML
    void AddSAction(ActionEvent event) throws SQLException, ClassNotFoundException, FileNotFoundException {
        if(manS.isSelected()||womenS.isSelected()){
            con = conClass.getConnection();
            String requette = "INSERT INTO students(id,fnameS,lnameS,date_birth,wilaya,level,email,sexe,image) VALUES (?,?,?,?,?,?,?,?,?)";
            ps =  con.prepareStatement(requette);
            ps.setString(1,idnumb.getText());
            ps.setString(2,fnameS.getText());
            ps.setString(3,lnameS.getText());
            ps.setString(4,getDate());
            ps.setString(5,getChoicew());
            ps.setString(6,getChoicel());
            ps.setString(7,emailS.getText());
            if(manS.isSelected()){
                ps.setString(8,"Man");
            }
            else {
                ps.setString(8,"Women");
            }
            fis = new FileInputStream(file);
            ps.setBinaryStream(9,fis,file.length());

            ps.executeUpdate();
        }
        else {
            System.out.println("choose one choice Man if you are girls or a boys");
        }
        addS.getScene().getWindow().hide();
    }

    @FXML
    void uploadphotoAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) anchorpane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);

        if(file!=null){
            System.out.println(""+file.getAbsolutePath());
            image = new Image(file.getAbsoluteFile().toURL().toString(),223,218,true,true);
            imageview.setImage(image);
            imageview.setPreserveRatio(true);
        }
    }


    public String getDate(){
        LocalDate dates = datebirth.getValue();
        String date = dates.toString();
        return date ;
    }
    public String getChoicew(){
        String choicew = wilaya.getValue();
        return choicew.toString();
    }
    public String getChoicel(){
        String choicel = leveLS.getValue();
        return choicel.toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wilaya.getItems().addAll(wilayachoice);
        leveLS.getItems().addAll(levelchoice);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL Files","*"),
                new FileChooser.ExtensionFilter("Images","*png","*jpg","*jpeg"),
                new FileChooser.ExtensionFilter("Files","*txt")
        );
    }
}
