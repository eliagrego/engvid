package engvid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Welcome extends VBox {
    
    private Main main;

    public Welcome(Main main) {
        
        this.main = main;
        
        setMinWidth(V.WIDTH - V.WIDTH_MENU + 10);
        setAlignment(Pos.CENTER);
        
        if(EngVid.isReachableByPing("www.engvid.com")){

            try {            
                Image image = new Image(new FileInputStream("full.png"));            
                ImageView imageView = new ImageView(image); 
                imageView.setX(50); 
                imageView.setY(25); 
                imageView.setFitHeight(455); 
                imageView.setFitWidth(500); 
                imageView.setPreserveRatio(true);                        
                getChildren().add(imageView);            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                Label ehiBox = new Label("---------------- Last video uploaded ----------------");
                ehiBox.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 22));
                ehiBox.setTextFill(Paint.valueOf("white"));

                Document newVideo = Jsoup.connect("https://www.engvid.com/").get();       
                Elements titolo = newVideo.select(".featured_title").get(0).children();
                String title = titolo.get(0).text();            
                String content = titolo.get(1).text();
                String url = titolo.get(0).child(0).attr("href");

                Elements auth = newVideo.select(".featured_author");
                String author = auth.get(0).children().text();

                String imgAuth = auth.select("img").get(0).attr("src");
                if(!imgAuth.contains("https://")){
                    imgAuth = "https:" + imgAuth;
                }

                setSpacing(20);

                BoxLastVideo blv = new BoxLastVideo(this, author, imgAuth, title, content, url);

                HBox div = new HBox();

                getChildren().add(div);
                getChildren().add(ehiBox);
                getChildren().add(blv);

            } catch (Exception ex) {
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

    public Main getMain() {
        return main;
    }
    
}
