package engvid;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class FilterManager extends VBox {
    
    private VideoBrowse video;
    private Stage stage;
    private Configuration config;
    private double xOffset, yOffset;
    private boolean draggable;

    public FilterManager(VideoBrowse video, Stage stage, Configuration conf, Stage thisstage) {
        
        this.video = video;
        this.stage = stage;
        this.config = conf;
        this.draggable = false;
        
        setBackground(V.MAINBG);
        setPadding(new Insets(20));
        setSpacing(10);
                
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(thisstage.getY() - event.getScreenY() >= -60){
                    xOffset = thisstage.getX() - event.getScreenX();
                    yOffset = thisstage.getY() - event.getScreenY();
                    draggable = true;
                }
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(draggable){
                    thisstage.setX(event.getScreenX() + xOffset);
                    thisstage.setY(event.getScreenY() + yOffset);
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggable = false;
            }
        });
        setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(thisstage.getY() - event.getScreenY() >= -60){
                    setCursor(Cursor.MOVE);
                }
                else{
                    setCursor(Cursor.DEFAULT);
                }
            }
        });
        
        Label titolo = new Label("Filters");
        titolo.setTextFill(Paint.valueOf("white"));
        titolo.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));

        HBox treBottoni = new HBox(10);
        
        FilterButton authors = new FilterButton("Authors", 0);
        authors.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {                
                video.returnFromFilter(config);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();                
            }
        });
        
        FilterButton topics = new FilterButton("Topics", 1);
        FilterButton diff = new FilterButton("Difficulties", 2);
        HBox div = new HBox();
        div.setMinWidth(235);
        FilterButton showall = new FilterButton("    Show All    ", 3);
        
        getChildren().add(titolo);
        treBottoni.getChildren().add(authors);
        treBottoni.getChildren().add(topics);
        treBottoni.getChildren().add(diff);
        treBottoni.getChildren().add(div);
        treBottoni.getChildren().add(showall);
        getChildren().add(treBottoni);
        
        thisstage.initStyle(StageStyle.UNDECORATED);
        setBorder(new Border(new BorderStroke(Paint.valueOf("#20545f"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5))));
        
    }

    public VideoBrowse getVideo() {
        return video;
    }    
    
}
