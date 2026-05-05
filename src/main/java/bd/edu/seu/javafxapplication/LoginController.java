package bd.edu.seu.javafxapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginController {

    @FXML
    public TextField nameFeild;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Label passwordError;

    @FXML
    public Label massage;



    @FXML
    public void setLoginButtonAction() {
        String email = nameFeild.getText().trim();
        String password = passwordField.getText().trim();

           readUser(email,password,"users.txt");
    }

    public void setRegisterLabelClicked() {
        HelloApplication.changeScene("registration");
    }

    public void readUser(String email,String password,String fileName) {
         try{
             RandomAccessFile idk = new RandomAccessFile(fileName,"r");
             String line;
             while((line = idk.readLine())!= null) {
                 String [] A = line.split(",");
                 String Email = A[1];
                 String Password = A[2];

                 if(Email.equals(email)){
                     if(Password.equals(password)){
                         massage.setText("User Logged in");
                         HelloApplication.changeScene("contact");
                         return;
                     }else {
                         passwordError.setText("Password mismatch");
                         return;
                     }
                 }

             }
         }catch(FileNotFoundException e) {

         }catch(IOException e) {

         }
         passwordError.setText("User not Found");
         return;

    }
}
