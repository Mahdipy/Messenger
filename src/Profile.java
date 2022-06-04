import javafx.application.Platform;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Profile implements Runnable{
    String name;
    ImageView button = new ImageView(new Image("image/send2.png"));
    ImageView back = new ImageView(new Image("image/back.png"));
    TextField textField = new TextField();
    Pane pane = new Pane();
    Label nameLb = new Label();
    Label online = new Label("Online");
    Pane mainPane = new Pane();
    ScrollPane scrollPane = new ScrollPane();
    int m=80;
    Scene scene = new Scene(mainPane,380,540);
    ImageView avatar = new ImageView();
    public void run(){
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(471);
        btn.setVisible(false);
        pane.getChildren().add(btn);
        Rectangle rectangle = new Rectangle(378,60,Color.rgb(255,30,100));
                nameLb.setFont(Font.font("", FontWeight.BOLD,20));
                nameLb.setLayoutX(170);
                nameLb.setLayoutY(10);
                name = name.substring(0,1).toUpperCase() + name.substring(1);
                nameLb.setText(name);
                online.setLayoutX(170);
                online.setLayoutY(40);
                online.setFont(Font.font(15));
                avatar.setFitHeight(50);
                avatar.setFitWidth(50);
                avatar.setLayoutX(70);
                avatar.setLayoutY(5);
                pane.getChildren().addAll(rectangle,online,nameLb,avatar);
                textField.setLayoutX(10);
                textField.setLayoutY(506);
                textField.setPrefSize(325,25);
                back.setLayoutX(7);
                back.setLayoutY(7);
                back.setFitWidth(30);
                back.setFitHeight(24);
                button.setFitHeight(65);
                button.setFitWidth(75);
                button.setLayoutY(485);
                button.setLayoutX(321);
                pane.getChildren().addAll(back);
                scrollPane.setContent(pane);
                scrollPane.setPrefSize( 380,499);
                //scrollPane.setStyle("-fx-background-image: url(image/chat.jpg);");
                pane.setStyle("-fx-background-image: url(image/chat.jpg);");
                mainPane.setStyle("-fx-background-image: url(image/chat.jpg);");
                mainPane.getChildren().addAll(scrollPane,textField,button);
    }
    public void add(String message ,boolean a){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                ImageView imageView = new ImageView();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Text text = new Text(message + " ("+sdf.format(date)+")");
                text.setFont(Font.font(null,15));
                text.setTextAlignment(TextAlignment.RIGHT);
                scrollPane.setVvalue(scrollPane.getVmax()+5);
                if (!a) {
                    imageView.setImage(new Image("image/bub.png"));
                    imageView.setFitHeight(30);
                    imageView.setFitWidth((message.length()*10)+50);
                    imageView.setLayoutY(m-20);
                    imageView.setLayoutX(240);
                    text.setFill(Color.BLACK);
                    text.setLayoutX(250);
                    text.setLayoutY(m);
                    m+=20;
                    pane.getChildren().addAll(imageView,text);
                }
                else {
                    imageView.setImage(new Image("image/bub1.png"));
                    imageView.setFitHeight(30);
                    imageView.setFitWidth((message.length()*10)+50);
                    imageView.setLayoutY(m-20);
                    imageView.setLayoutX(12);
                    text.setFill(Color.BLACK);
                    text.setLayoutX(20);
                    text.setLayoutY(m);
                    m+=20;
                    pane.getChildren().addAll(imageView,text);
                }
                textField.setText("");
            }

        });

    }
}
