import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Pane pane = new Pane();
    TextField username = new TextField();
    PasswordField password = new PasswordField();
    Filework filework ;
    Client client;
    Button button = new Button("Log In");
    ImageView log = new ImageView(new Image("image/login.png"));
    Hyperlink sign = new Hyperlink("Sign Up");
    public void run(Stage primaryStage){
        log.setFitWidth(40);
        log.setFitHeight(40);
        log.setLayoutY(440);
        log.setLayoutX(159);
        log.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                log.setFitWidth(50);
                log.setFitHeight(50);
                log.setLayoutY(435);
                log.setLayoutX(154);
            }
        });
        log.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                log.setFitWidth(40);
                log.setFitHeight(40);
                log.setLayoutY(440);
                log.setLayoutX(159);
            }
        });
        Label label = new Label("Username:");
        Label label1 = new Label("Password:");
        label.setLayoutX(45);
        label.setLayoutY(351);
        label1.setLayoutY(385);
        label1.setLayoutX(45);
        label.setFont(Font.font(null, FontWeight.BOLD,15));
        label1.setFont(Font.font(null, FontWeight.BOLD,15));
        username.setLayoutX(173);
        password.setLayoutX(173);
        username.setLayoutY(351);
        password.setLayoutY(385);
        button.setLayoutY(440);
        button.setLayoutX(159);
        sign.setLayoutX(156);
        sign.setLayoutY(490);
        sign.setTextFill(Color.BLACK);
        sign.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SignIn signIn = new SignIn();
                signIn.first(primaryStage);
            }
        });
        ImageView imageView = new ImageView(new Image("image/logo.png"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setLayoutY(11);
        imageView.setLayoutX(90);
        Label kk = new Label("KK");
        kk.setFont(Font.font(null, FontWeight.BOLD,77));
        kk.setLayoutX(139);
        kk.setLayoutY(210);
        pane.setStyle("-fx-background-color: rgb(255,30,100);");
        pane.getChildren().addAll(username,password,label,label1,log,sign,imageView,kk);
        log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    filework = new Filework(username.getText(),password.getText());

                    if (filework.search()){
                        client = new Client(username.getText(),primaryStage);
                        client.run();
                    }
                    else {
                        alert.setContentText("Username or Password is wrong");
                        username.deleteText(0,username.getLength());
                        password.deleteText(0,password.getLength());
                        alert.show();
                    }
                }
                catch (IOException e){
                    System.out.println("Search IOException");
                }
            }
        });
        Scene scene = new Scene(pane,380,540);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
