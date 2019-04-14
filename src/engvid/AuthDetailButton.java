package engvid;

import java.awt.Desktop;
import java.net.URI;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class AuthDetailButton extends VBox {
    
    private AuthDetail det;
    private Label desc;
    private M_Author mauth;

    public AuthDetailButton(AuthDetail det, M_Author autore, int id) {
        
        this.det = det;
        this.mauth = autore;
        
        setSpacing(10);
        
        StackPane imm = new StackPane();
        imm.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), new CornerRadii(100), Insets.EMPTY)));
        imm.setMaxWidth(200);
        imm.setMinWidth(200);
        imm.setMaxHeight(100);
        imm.setMinHeight(100);
        imm.setBorder(new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(100), new BorderWidths(1))));
        imm.setCursor(Cursor.HAND);
        imm.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imm.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000033"), new CornerRadii(100), Insets.EMPTY)));
                desc.setTextFill(Paint.valueOf("black"));
            }
        });
        imm.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imm.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), new CornerRadii(100), Insets.EMPTY)));
                desc.setTextFill(Paint.valueOf("white"));
            }
        });
        imm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
                    if(EngVid.isReachableByPing("www.engvid.com")){                            
                        if(id == 0){
                            Desktop.getDesktop().browse(new URI(autore.getUrlyoutube())); 
                        }
                        else if(id == 1){
                            Desktop.getDesktop().browse(new URI(autore.getUrl())); 
                        }
                        else if(id == 2){
                            Configuration c_tmp = new Configuration();
                            c_tmp.getAutori().add(mauth);
                            det.getMain().setVideoBrowse();
                            det.getMain().getVideoBrowse().applyFilter(c_tmp);
                        }  
                    }
                    else{
                        new Alert(Alert.AlertType.WARNING, "No Internet Connection, check your connectiity and retry...", ButtonType.OK).show();
                    }                     
                }catch(Exception e){}
            }
        });
        
        String content = "";
        String URL = "";
        double wid = 0, hei = 0;
        
        if(id == 0){
            content = "Youtube\nChannel";
            URL = "https://upload.wikimedia.org/wikipedia/commons/b/b2/YouTube_logo_%282013-2015%29.png";
            wid = 90;
            hei = 90;
        }
        else if(id == 1){
            content = "Personal\nPage";
            URL = "https://www.engvid.com/wp-content/themes/engvid2/images/engvid.png";
            wid = 140;
            hei = 50;
        }
        else if(id == 2){
            content = "Video";
            URL = "https://www.promoteproductions.com/wp-content/uploads/2018/03/video-1364122_960_720-1.png";
            wid = 70;
            hei = 70;
        }
        
        desc = new Label(content);
        
        ImageView imageView = ImageViewBuilder.create()
                .image(new Image(URL))
                .build();

        imageView.setFitHeight(hei);
        imageView.setFitWidth(wid);
        
        imm.getChildren().add(imageView);
        getChildren().add(imm);
        
        desc.setMinWidth(200);
        desc.setTextAlignment(TextAlignment.CENTER);
        desc.setAlignment(Pos.CENTER);
        desc.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 18));
        desc.setTextFill(Paint.valueOf("white"));
        
        getChildren().add(desc);
        
    }

    public AuthDetail getDet() {
        return det;
    }
    
}
