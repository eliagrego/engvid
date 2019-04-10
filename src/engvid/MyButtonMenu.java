package engvid;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MyButtonMenu extends Button {
    
    private String text;

    public MyButtonMenu(String text) {        
        
        super(text);
        this.text = text;
        
        setBackground(V.MAINBG);
        setMinWidth(V.WIDTH_MENU - 40);
        setTextFill(Paint.valueOf("white"));
        setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 14));
        setCursor(Cursor.HAND);
        setBorder(new Border(new BorderStroke(Paint.valueOf("#245f6c"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setBackground(new Background(new BackgroundFill(Paint.valueOf("#20545f"), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setBackground(V.MAINBG);
            }
        });
        
    }
    
}
