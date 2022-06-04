import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class First {

    Filework filework = new Filework();
    ArrayList<String> members;
    ArrayList<Profile> profiles = new ArrayList<>();
    Pane pane = new Pane();
    Scene scene = new Scene(pane, 380, 540);
    ImageView logout = new ImageView(new Image("image/exit.png"));
    public void run(Stage primaryStage) {
        try {
            members = filework.userSearch();
        } catch (Exception e) {
        }
        Text[] text = new Text[members.size()];
        logout.setFitWidth(40);
        logout.setFitHeight(40);
        logout.setLayoutY(460);
        logout.setLayoutX(170);
        Label l1 = new Label("Logout");
        l1.setLayoutY(500);
        l1.setLayoutX(170);
        Profile[] p = new Profile[members.size()];
        String m;
        pane.getChildren().addAll(l1,logout);
        for (int i = 0; i < members.size(); i++) {
            p[i] = new Profile();
            p[i].name = members.get(i);
            if ((m = filework.getImage(i)) != null)
                p[i].avatar.setImage(new Image(m));
            p[i].run();
            profiles.add(p[i]);
            text[i] = new Text(members.get(i));
            text[i].setFont(Font.font(null, FontWeight.BOLD, 15));
            text[i].setLayoutY(120 + (30 * i));
            text[i].setLayoutX(50);
            pane.getChildren().addAll(text[i]);
            int finalI = i;
            text[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    profiles.get(finalI).name = text[finalI].getText();
                    primaryStage.setScene(profiles.get(finalI).scene);
                }
            });
            profiles.get(finalI).back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setScene(scene);
                }

            });
        }
            Label label = new Label("Your Contacts");
            label.setLayoutX(46);
            label.setLayoutY(14);
            Label label1 = new Label("Your Groups");
            label1.setLayoutY(14);
            label1.setLayoutX(241);
            label.setFont(Font.font(15));
            label1.setFont(Font.font(15));
            Line line = new Line(190, 30, 190, 380);
            TextField search = new TextField();
            ImageView searchpic = new ImageView(new Image("image/search.png"));
            searchpic.setFitHeight(35);
            searchpic.setFitWidth(35);
            searchpic.setLayoutX(260);
            searchpic.setLayoutY(420);
            search.setLayoutY(420);
            search.setLayoutX(101);
            searchpic.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    searchpic.setFitHeight(41);
                    searchpic.setFitWidth(41);
                    searchpic.setLayoutX(257);
                    searchpic.setLayoutY(417);
                }
            });
            searchpic.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    searchpic.setFitHeight(35);
                    searchpic.setFitWidth(35);
                    searchpic.setLayoutX(260);
                    searchpic.setLayoutY(420);
                }
            });
            pane.setStyle("-fx-background-image: url(image/chat.jpg);");
            pane.getChildren().addAll(searchpic, search, label, label1, line);
            primaryStage.setScene(scene);
            primaryStage.show();
            searchpic.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Profile profile = new Profile();
                    profile.name = search.getText();
                    profile.run();
                    profiles.add(profile);
                    primaryStage.setScene(profiles.get(profiles.size() - 1).scene);
                    primaryStage.setTitle(profiles.get(profiles.size() - 1).name);

                    profile.back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            primaryStage.setScene(scene);
                        }
                    });
                }
            });

    }
}
