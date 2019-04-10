package engvid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Welcome extends VBox {
    
    private Main main;

    public Welcome(Main main) {
        
        this.main = main;
        
        setMinWidth(V.WIDTH - V.WIDTH_MENU + 10);
        setAlignment(Pos.CENTER);
        
        
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
            
            setSpacing(40);
            
            Label offline = new Label("No internet connection\nYou are offline!");
            offline.setTextFill(Paint.valueOf("white"));
            offline.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 30));
            offline.setTextAlignment(TextAlignment.CENTER);
            getChildren().add(offline);
            
        }
        
    }

    public Main getMain() {
        return main;
    }
    
}
