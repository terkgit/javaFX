package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class Controller {


    public void initialize() {
    }


    @FXML
    private Button helloBtn;

    @FXML
    private TextField helloTF;
    
    @FXML
    private TextArea countTF;
    
    @FXML
    void sayHello(ActionEvent event) {
    	String str=countTF.getText();
    	str=str+1;
    	countTF.setText(str);
    	helloTF.setText("hello!");
    }
    
}