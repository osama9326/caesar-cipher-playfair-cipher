/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osama;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class connect {

    public Button decryptButton;
    public Button encryptButtom;
    public Label decryotedLabel;
    public TextField textfield;
    public ChoiceBox choiceBox;
    public CaesarCipher caesarCipher;
    public playfair playfair;
    public TextField key ; 
    
    @FXML
    private void initialize() {
        caesarCipher = new CaesarCipher();
      playfair = new playfair();
      
        choiceBox.getItems().add("CaesarCipher");
        choiceBox.getItems().add("PlayFairCipher");

        encryptButtom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleEncryptButton();
            }
        });

        decryptButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleDecryptButton();
            }
        });

    }

    private void handleEncryptButton() {
        String message = textfield.getText();
        String method = choiceBox.getValue().toString();
        String encryptedMessage = "";

        if (method.equals("CaesarCipher")) {
            caesarCipher.setKey(Integer.parseInt(key.getText()));
            encryptedMessage = caesarCipher.encrypt(message);
        } else if (method.equals("PlayFairCipher")) {
             playfair.setKey(key.getText());
             encryptedMessage = playfair.encrypt(message);
        }

        decryotedLabel.setText(encryptedMessage);//decryotedLabel.getText() +
        textfield.clear();
    }

    private void handleDecryptButton() {
        String encryptedMessage = decryotedLabel.getText();
        String method = choiceBox.getValue().toString();
        String realMessage = "";

        if (method.equals("CaesarCipher")) {
            realMessage = caesarCipher.decrypt(encryptedMessage);
        } else if (method.equals("PlayFairCipher")) {
            realMessage = playfair.decrypt(encryptedMessage);
        }

        textfield.setText(realMessage);
        decryotedLabel.setText("");
    }

}