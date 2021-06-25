package Controller.HompePage;

import BasicClass.Students;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DBConnection.Connex;
import Controller.HompePage.SearchInformationsController;
import BasicClass.Students;
public class ShowInfoStudentsController implements  Initializable{

    @FXML
    private Label firstnam,lastnam,idnumber,datebirth,wilaya,levell,emiall;

    @FXML
    private ImageView shwoimage;

    private File file;
    private Image image;
    private FileInputStream fis;

    Connection con;
    Connex conClass = new Connex();
    PreparedStatement ps;

    int i = 0;
    String FN,LN,ID,DB,W,L,E;
    private String name ;

    public void showInfo(String idnumb) throws SQLException, ClassNotFoundException, IOException {
        con = conClass.getConnection();
        String query = "SELECT id , fnameS ,lnameS,date_birth,wilaya,level,email FROM students WHERE id =?";
        ps = con.prepareStatement(query);
        ps.setString(1, idnumb);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            FN  = rs.getString("fnameS");
            LN = rs.getString("lnameS");
            ID = rs.getString("id");
            DB = rs.getString("date_birth");
            W = rs.getString("wilaya");
            L = rs.getString("level");
            E = rs.getString("email");
        }
        firstnam.setText(""+FN);
        lastnam.setText(""+LN);
        idnumber.setText(""+ID);
        datebirth.setText(""+DB);
        wilaya.setText(""+W);
        levell.setText(""+L);
        emiall.setText(""+E);
        showImage(idnumb);
    }

    public void showImage(String idnumb) throws SQLException, IOException {
        String query = "SELECT image from students where id = ?";
        ps = con.prepareStatement(query);
        ps.setString(1,idnumb);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            InputStream is = rs.getBinaryStream(1);
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] content = new byte[1024];
            int size =0 ;
            while((size = is.read(content))!=-1){
                os.write(content,0,size);
            }

            image = new Image("file:photo.jpg",shwoimage.getFitWidth(),shwoimage.getFitHeight(),true,true);
            shwoimage.setImage(image);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
