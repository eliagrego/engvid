package engvid;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Resources extends VBox {
    
    private Main main;
    private ResourcesCNT cnt;
    private Label titolo;

    public Resources(Main main) {
        
        this.main = main;
        
        setMinWidth(V.WIDTH - V.WIDTH_MENU + 10);
        setPadding(new Insets(20));
        setSpacing(10);
        
        titolo = new Label("Resources");
        titolo.setTextFill(Paint.valueOf("white"));
        titolo.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
        
        cnt = new ResourcesCNT(this);
        
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setBackground(V.MAINBG);
        sp.setContent(cnt);
        
        getChildren().add(titolo);
        getChildren().add(sp);
        
    }

    public Label getTitolo() {
        return titolo;
    }

    public Main getMain() {
        return main;
    }
    
}
