package com.project.zeidot.controller.mail;

import com.project.zeidot.controller.popups.SelectMailController;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class SendMailController {
    @FXML
    private TextArea mailContent;
    @FXML
    private TextField mailSubject;
    @FXML
    private Button mailBtn;

    private final String myAccountEmail = "prabashmperera01@gmail.com"; // Your email
    private final String myAppPassword = "pugr bwyx qwgh yfzt"; // App password from Google

    // Method to handle selecting an email address
    public void selectMailOnAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/popups/selectMailPage.fxml"));
            Parent root = loader.load();

            SelectMailController controller = loader.getController();
            controller.setSendMailController(this);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to set the recipient email in the button
    public void setMailToBtn(String mailAddress) {
        mailBtn.setText(mailAddress); // Set the email address on the button
    }

    // Method to send the email
    public void sendMail() {
        try {
            // Setting up mail server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            // Create session with email credentials
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, myAppPassword);
                }
            });

            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail)); // Sender's email
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailBtn.getText())); // Recipient email
            message.setSubject(mailSubject.getText()); // Email subject
            message.setText(mailContent.getText()); // Email body content

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").showAndWait();
        } catch (MessagingException e) {
            new Alert(Alert.AlertType.ERROR, "Not Sent!!");
        }
    }

    // Event handler for sending mail
    @FXML
    public void sendMailOnAction(ActionEvent event) {
        if (mailContent.getText().isEmpty() || mailSubject.getText().isEmpty() || mailContent.getText() == null || mailSubject.getText() == null) {
            new Alert(Alert.AlertType.ERROR , "Fill All THe Fields").show();
            return;
        }
        sendMail();
    }
}
