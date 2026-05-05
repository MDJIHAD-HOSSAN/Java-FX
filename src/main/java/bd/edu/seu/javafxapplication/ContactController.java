package bd.edu.seu.javafxapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.temporal.Temporal;

public class ContactController {

    @FXML
    public TextField nameFeild;

    @FXML
    public TextField addressFeild;

    @FXML
    public TextField mobileFeild;

    @FXML
    public Label massage;

    @FXML
    public Label mobileError;



    @FXML
    public void setSubmitButtonAction() {
        String name = nameFeild.getText();
        String address = addressFeild.getText();
        String mobile = mobileFeild.getText();

        if(name.isEmpty() || address.isEmpty() || mobile.isEmpty()){
            massage.setText("Please Fill all Feild");
            return;
        }

        if(mobile.length()<11) {
            mobileError.setText("number invalid");
            return;
        }

        String sub = mobile.substring(0,3);

        if(!sub.equals("013") && !sub.equals("017") && !sub.equals("019") ) {
             mobileError.setText("number invalid");
             return;
        }

        if(readContact(mobile,"contacts.txt")){
            mobileError.setText("number already exits");
            return;
        }

        writeContact(name+","+address+","+mobile+"\n" , "contacts.txt");
        massage.setText("Contact Saved");





    }

    public void setSignOutAciton() {
        HelloApplication.changeScene("login");
    }



    public void writeContact(String line , String fileName) {
        try{
            RandomAccessFile idk = new RandomAccessFile(fileName , "rw");
            idk.seek(idk.length());
            idk.writeBytes(line);
        }catch (FileNotFoundException e) {

        }catch(IOException e){

        }
    }

    public boolean readContact(String Mobile,String fileName) {
        try {
            RandomAccessFile idk = new RandomAccessFile(fileName ,"r");
            String line;
            while((line = idk.readLine()) != null) {
                String A[] =line.split(",");
                String mobile = A[2];
                if(Mobile.equals(mobile)){
                    return true;
                }
            }
        }catch (FileNotFoundException e){

        }catch (IOException e) {

        }
        return false;
    }

}
