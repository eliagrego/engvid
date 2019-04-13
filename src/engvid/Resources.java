package engvid;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class Resources extends VBox {
    
    private Main main;
    private ResourcesCNT cnt;
    private Label titolo;

    public Resources(Main main) {
        
        this.main = main;        
        
        if(EngVid.isReachableByPing("www.engvid.com")){

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
        else{            
            
            // Offline
            main.getChildren().removeAll(main.getChildren());
            main.getEngVid().getPrimaryStage().setMaxWidth(400);
            main.getEngVid().getPrimaryStage().setMaxHeight(200);
            main.getChildren().add(new NoInternetMessage());
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            main.getEngVid().getPrimaryStage().setX((primScreenBounds.getWidth() - main.getEngVid().getPrimaryStage().getWidth()) / 2);
            main.getEngVid().getPrimaryStage().setY((primScreenBounds.getHeight() - main.getEngVid().getPrimaryStage().getHeight()) / 2);
            
        }
        
    }

    public Label getTitolo() {
        return titolo;
    }

    public Main getMain() {
        return main;
    }
    
}
