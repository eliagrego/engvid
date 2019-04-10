package engvid;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

public class P3_1 extends StackPane {
    
    private Main main;

    public P3_1(Main main) {
        
        this.main = main;
        
        setBackground(new Background(new BackgroundFill(Paint.valueOf("yellow"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        getChildren().add(new Button("Paperino"));
        
    }

    public Main getMain() {
        return main;
    }
    
}