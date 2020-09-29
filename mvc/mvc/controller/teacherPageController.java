package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class teacherPageController {
    @FXML
    private Button logOutButton;

    @FXML
    private Button classTeacherButton;

    @FXML
    private Button subjectTeacherButton;

    @FXML
    private Label teacherTypeLabel;

    @FXML
    public GridPane classGridPane;

    @FXML
    private Label userNameLabel;

    @FXML
    private AnchorPane centerAnchorPane;

    @FXML
    public Button [] gridPaneButton;

    private DbClass db;
    private String userName;
    private String [][] subjects;

    @FXML
    public void initialize(){
        try{

            classTeacherButton=new Button("CLASS TEACHER");
            subjectTeacherButton=new Button();
            classGridPane=new GridPane();
            centerAnchorPane=new AnchorPane();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void setDatabase(DbClass db){
        this.db=db;
    }
    public void setUserName(String userName){
        this.userName=userName;
        userNameLabel.setText("USERNAME : "+userName);

    }

    @FXML
    public void setSubjectTeacherButton(){
        try{
            subjects= db.getSubjectTeacherSection(userName);
            System.out.println(subjects.length);
            classGridPane=new GridPane();
            gridPaneButton=new Button[subjects.length];
            for(int i=0; i<subjects.length; i++){
                String buttonText=subjects[i][0]+" "+subjects[i][1]+" "+subjects[i][2];
                System.out.println(buttonText);
                final Button temp=new Button(buttonText);
                final int id=i;
 //               gridPaneButton[i]=new Button(""+subjects[i][0]+" "+subjects[i][1]+" "+subjects[i][2]);  REZAUL KARIM

                temp.setId(""+id);
                System.out.println(temp.getId()+" "+id);
//                temp.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        System.out.println(temp.getId());
//                    }
//                });
                gridPaneButton[i]=temp;

                classGridPane.add(temp,0,i);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
