import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("image/logo.png"));
        SignIn signIn = new SignIn();
        LogIn logIn = new LogIn();
        Pane pane = new Pane();
        Label l1 = new Label("Login");
        Label l2 = new Label("Sign Up");
        Label label1 = new Label("Fast & Secure");
        label1.setFont(Font.font("",FontWeight.BOLD,15));
        label1.setLayoutX(140);
        label1.setLayoutY(510);
        l1.setLayoutY(460);
        l2.setLayoutY(460);
        l1.setLayoutX(67);
        l2.setLayoutX(267);
        l1.setFont(Font.font("",FontWeight.BOLD,15));
        l2.setFont(Font.font("",FontWeight.BOLD,15));
        ImageView sign = new ImageView(new Image("image/sign.png"));
        sign.setFitHeight(50);
        sign.setFitWidth(50);
        ImageView log = new ImageView(new Image("image/log.png"));
        log.setFitWidth(50);
        log.setFitHeight(50);
        sign.setLayoutX(265);
        sign.setLayoutY(410);
        log.setLayoutY(410);
        log.setLayoutX(62);
        log.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                log.setFitWidth(58);
                log.setFitHeight(58);
                log.setLayoutY(406);
                log.setLayoutX(58);
                l1.setLayoutY(462);
            }
        });
        log.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                log.setFitWidth(50);
                log.setFitHeight(50);
                log.setLayoutY(410);
                log.setLayoutX(62);
                l1.setLayoutY(460);
            }
        });
        sign.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sign.setLayoutX(261);
                sign.setLayoutY(406);
                sign.setFitHeight(58);
                sign.setFitWidth(58);
                l2.setLayoutY(462);
            }
        });
        sign.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sign.setLayoutX(265);
                sign.setLayoutY(410);
                sign.setFitHeight(50);
                sign.setFitWidth(50);
                l2.setLayoutY(460);
            }
        });
        ImageView imageView = new ImageView(new Image("image/logo.png"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setLayoutY(11);
        imageView.setLayoutX(90);
        Label kk = new Label("KK");
        kk.setFont(Font.font(null, FontWeight.BOLD, 77));
        kk.setLayoutX(139);
        kk.setLayoutY(250);
        Label label = new Label("Welcome To The");
        label.setFont(Font.font(null, FontWeight.BOLD, 19));
        label.setLayoutX(121);
        label.setLayoutY(241);
        pane.getChildren().addAll(imageView, kk, label);
        pane.getChildren().addAll(label1,sign, log,l1,l2);
      //  pane.setStyle("-fx-background-image: url(image/chat.jpg);");
        pane.setStyle("-fx-background-color: rgb(255,30,100);");
        Scene scene = new Scene(pane, 380, 540);
        primaryStage.setScene(scene);
        primaryStage.show();
        sign.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                signIn.first(primaryStage);
            }
        });
        log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logIn.run(primaryStage);
            }
        });
        logIn.button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });

    }
}