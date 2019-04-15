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

public class FilterButton extends Button {
    
    private int id;
    
    public FilterButton(String str, int id) {
        
        super(str);
        this.id = id;
        setBorder(new Border(new BorderStroke(Paint.valueOf("white"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        setBackground(new Background(new BackgroundFill(Paint.valueOf("#a5d6e1"), CornerRadii.EMPTY, Insets.EMPTY)));
        setTextFill(Paint.valueOf("#20545f"));
        setCursor(Cursor.HAND);
        setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 12));
        
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setBackground(new Background(new BackgroundFill(Paint.valueOf("#20545f"), CornerRadii.EMPTY, Insets.EMPTY)));
                setTextFill(Paint.valueOf("white"));
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setBackground(new Background(new BackgroundFill(Paint.valueOf("#a5d6e1"), CornerRadii.EMPTY, Insets.EMPTY)));
                setTextFill(Paint.valueOf("#20545f"));
            }
        });
        
    }

    public int getIdBtn() {
        return id;
    }
    
}
