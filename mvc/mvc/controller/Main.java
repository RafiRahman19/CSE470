package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        try {
            Runtime.getRuntime().exec("cmd /c start H:\\Work\\\"Shop Managment System\"\\src\\database\\start.bat", null, new File("H:\\Work\\Shop Managment System\\src\\database\\"));

            sample.DbClass dbClass = new sample.DbClass("jdbc:mysql://localhost:3311", "root", "root");


            this.primaryStage = primaryStage;
            Stage window = new Stage();
            loginPage();


        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void loginPage()
    {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            primaryStage.setTitle("LOGIN");
            Scene scene = new Scene(root, 590, 455);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }



        new logInController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
