package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class logInController {

    @FXML
    private TextField loginUserName=new TextField();

    @FXML
    private PasswordField loginPassword=new PasswordField();

    @FXML
    private Button loginButton=new Button();

    @FXML
    private ImageView loginImage=new ImageView();

    @FXML
    private Label warningLabel=new Label(" ");

    private Stage primaryStage=new Stage();

    private DbClass db=new DbClass();

    public logInController(){

    }

    public logInController(Stage pStage){
        primaryStage=pStage;
    }

    @FXML
    public void setLoginButton(){
        try{
            String userName=loginUserName.getText();
            String password=loginPassword.getText();
            System.out.println(userName+"     "+password);

            String type=db.getLogInInfo(userName, password);

            if(type.equals(null)){
                warningLabel.setText("Error occur contact admin");
            }else if(type.equals("no match")){
                warningLabel.setText("User name and Password didn't match");
            }else if(type.equals("teacher") || type.equals("system admin")){
                warningLabel.setText("Login Successful");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherPage.fxml"));
                Parent root=loader.load();
                teacherPageController tpController = loader.getController();
                primaryStage.setTitle("TEACHER VIEW");
                Scene scene=new Scene(root, 1300, 700);
//            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();

                tpController.setDatabase(db);
                tpController.setUserName(userName);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
