package engvid;

import java.awt.Desktop;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Resources_Row extends HBox {
    
    private ResourcesCNT resourcesCNT;
    private M_Res mres;
    private int res_id;
    private Button btn;
    private boolean offline;

    public Resources_Row(ResourcesCNT resourcesCNT, M_Res mres, int id, boolean last, boolean off) {
        
        this.resourcesCNT = resourcesCNT;
        this.res_id = id;
        this.offline = off;
        
        setMinWidth(V.WIDTH - V.WIDTH_MENU - 40);
        setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
        if(!last){
            setBorder(new Border(new BorderStroke(Paint.valueOf("black"), Paint.valueOf("black"), Paint.valueOf("black"), Paint.valueOf("black"), BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        }else{
            setBorder(new Border(new BorderStroke(Paint.valueOf("black"), Paint.valueOf("black"), Paint.valueOf("black"), Paint.valueOf("black"), BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        }
        
        Label text = new Label(mres.getName());
        text.setAlignment(Pos.CENTER_LEFT);
        text.setTranslateY(2);
        text.setMinHeight(25);
        text.setMinWidth(V.WIDTH - V.WIDTH_MENU - 180);
        text.setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 15));
        
        btn = new Button("");
        btn.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 12));
        btn.setBorder(new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5))));
        
        String btnText = "Not Visited";
        if(mres.isSeen()){
            btnText = "Visited";
            btn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#56f283"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{            
            btn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ffffa6"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        
        btn.setText(btnText);
        btn.setMinWidth(100);
        
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {                
                Model m = ObjectReader.getModel();
                boolean bo = !m.getResources().getResources().get(res_id).isSeen();
                m.getResources().getResources().get(res_id).setSeen(bo);
                ObjectReader.saveModel(m);

                if(!bo){
                    btn.setText("Not Visited");
                    btn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ffffa6"), CornerRadii.EMPTY, Insets.EMPTY)));
                }
                else{
                    btn.setText("Visited");
                    btn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#56f283"), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });
        
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {   
                if(!offline){
                    try {
                        Desktop.getDesktop().browse(new URI(mres.getUrl()));   
                        Model m = ObjectReader.getModel();
                        m.getResources().getResources().get(res_id).setSeen(true);
                        ObjectReader.saveModel(m);
                        btn.setText("Visited");
                        btn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#56f283"), CornerRadii.EMPTY, Insets.EMPTY)));                    
                    } catch (Exception ex) {
                        Logger.getLogger(Resources_Row.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setBackground(new Background(new BackgroundFill(Paint.valueOf("#afe5f0"), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {                
                setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        
        setPadding(new Insets(7, 5, 7, 10));
        setSpacing(10);
        
        if(!offline){
            setCursor(Cursor.HAND);
        }
        
        getChildren().add(text);
        getChildren().add(btn);
        
    }

    public M_Res getLocalResource() {
        return mres;
    }

    public ResourcesCNT getResourcesCNT() {
        return resourcesCNT;
    }
    
}
