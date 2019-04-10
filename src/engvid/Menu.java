package engvid;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class Menu extends VBox {
    
    private Main main;

    public Menu(Main main) {
        
        this.main = main;
        
        setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
        setMinWidth(V.WIDTH_MENU);
        setPadding(new Insets(20));
        setSpacing(10);
        
        MyButtonMenu welcome = new MyButtonMenu("Home");
        MyButtonMenu resource = new MyButtonMenu("Resources");
        MyButtonMenu autori = new MyButtonMenu("Authors");
        MyButtonMenu b3 = new MyButtonMenu("b3");
        
        welcome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.setWelcome();
            }
        });
        resource.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.setResource();
            }
        });
        autori.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.setAuthors();
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.setP3();
            }
        });
        
        getChildren().add(welcome);
        getChildren().add(resource);
        getChildren().add(autori);
        getChildren().add(b3);
        
    }

    public Main getMain() {
        return main;
    }
        
}
