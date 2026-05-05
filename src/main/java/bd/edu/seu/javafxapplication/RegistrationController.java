package bd.edu.seu.javafxapplication;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class RegistrationController {

    @FXML
    public TextField nameFeild;

    @FXML
    public TextField emailFeild;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField confirmPassFeild;


    public void setRegisterButtonAction() {
        String name = nameFeild.getText();

        String email = emailFeild.getText();

        String password = passwordField.getText();

        String confirmpass = confirmPassFeild.getText();

        if(name == "" || email == "" || password =="" || confirmpass == "") {
            IO.println("All Feild are required");
            return;
        }

        if(!password.equals(confirmpass)){
            IO.println("Password mismatch");
            return;
        }

        if(readUser(email,"users.txt")) {
            IO.println("Already register");
            return;
        }

        writeUser(name+","+email + ","+ password+ "\n","users.txt");
        IO.println("Registration completed. Please log in.");
        HelloApplication.changeScene("login");



    }

    public void writeUser(String line , String fileName) {
        try{
            RandomAccessFile idk = new RandomAccessFile(fileName , "rw");
            idk.seek(idk.length());
            idk.writeBytes(line);
        }catch (FileNotFoundException e) {

        }catch(IOException e){

        }
    }

    public boolean readUser(String email,String fileName) {
        try {
            RandomAccessFile idk = new RandomAccessFile(fileName ,"r");
            String line;
            while((line = idk.readLine()) != null) {
                String A[] =line.split(",");
                String Email = A[1];
                if(Email.equals(email)){
                    return true;
                }
            }
        }catch (FileNotFoundException e){

        }catch (IOException e) {

        }
        return false;
    }


}
