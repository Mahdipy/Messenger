import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;


public class SignIn {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Label[] label = new Label[5];
    TextField username = new TextField();
    TextField path = new TextField();
    PasswordField password = new PasswordField();
    PasswordField passagain = new PasswordField();
    TextField email = new TextField();
    Pane pane = new Pane();
    Filework filework ;
    Client client;
    public void first(Stage primaryStage){
        int y=307;
        for (int i =0 ; i<5 ;i++){
            label[i] = new Label();
            label[i].setFont(Font.font(null, FontWeight.BOLD,15));
            label[i].setLayoutY(y);
            y+=34;
            label[i].setLayoutX(45);
        }
        label[0].setText("UserName:");
        label[1].setText("Password:");
        label[2].setText("Password Again:");
        label[3].setText("Email: (optional)");
        label[4].setText("Image: ");
        username.setLayoutX(173);
        password.setLayoutX(173);
        passagain.setLayoutX(173);
        email.setLayoutX(173);
        path.setLayoutX(173);
        username.setLayoutY(305);
        password.setLayoutY(338);
        passagain.setLayoutY(372);
        email.setLayoutY(405);
        path.setLayoutY(437);
        Button button = new Button("Sign In");
        button.setLayoutY(485);
        button.setLayoutX(159);
        ImageView imageView = new ImageView(new Image("image/logo.png"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setLayoutY(11);
        imageView.setLayoutX(90);
        Label kk = new Label("KK");
        kk.setFont(Font.font(null,FontWeight.BOLD,77));
        kk.setLayoutX(139);
        kk.setLayoutY(180);
        pane.setStyle("-fx-background-color: rgb(255,30,100);");
        pane.getChildren().addAll(label);
        pane.getChildren().addAll(imageView,kk);
        pane.getChildren().addAll(username,password,passagain,email,path,button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {

                    if ( password.getText().equalsIgnoreCase(passagain.getText()) &&
                    !(username.getText().replaceAll("\\s","").isEmpty()) &&
                    !(password.getText().replaceAll("\\s","").isEmpty())) {
                        filework = new Filework(username.getText(), password.getText(),path.getText());
                        filework.run();
                        filework.addImage();
                        client = new Client(username.getText(),primaryStage);
                        client.run();
                    }
                    else if (!(password.getText().equalsIgnoreCase(passagain.getText()))){
                        alert.setHeaderText("Password");
                        alert.setContentText("Two Password Fields are not same");
                        password.deleteText(0,password.getLength());
                        passagain.deleteText(0,passagain.getLength());
                        alert.show();
                    }
                    else if (username.getText().replaceAll("\\s","").isEmpty()){
                        alert.setHeaderText("Empty Field");
                        alert.setContentText("Enter the username");
                        alert.show();
                    }
                    else if (password.getText().replaceAll("\\s","").isEmpty()){
                        alert.setHeaderText("Empty Field");
                        alert.setContentText("Enter the password");
                        password.deleteText(0,password.getLength());
                        passagain.deleteText(0,passagain.getLength());
                        alert.show();
                    }
                    else if(filework.search()){
                        alert.setContentText("Username or Password Already exist");
                        alert.show();
                    }

                }
                catch (IOException e){
                    System.out.println("IOException happen");
                }
            }
        });
        Scene scene = new Scene(pane,380,540);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
