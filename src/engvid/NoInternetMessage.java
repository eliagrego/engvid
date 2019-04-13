package engvid;

import java.io.FileInputStream;
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

public class NoInternetMessage extends StackPane {
    public NoInternetMessage() {
        
        setBackground(V.MAINBG);
        setMinWidth(400);        
        setMaxWidth(400);
        setAlignment(Pos.CENTER);
        
        VBox tutto = new VBox();
        tutto.setMinWidth(400);
        tutto.setAlignment(Pos.CENTER);

        try{
            Image image = new Image(new FileInputStream("full.png"));
            ImageView imageView = new ImageView(image); 
            imageView.setX(50); 
            imageView.setY(25); 
            imageView.setFitHeight(100); 
            imageView.setFitWidth(220); 
            imageView.setPreserveRatio(true);
            tutto.getChildren().add(imageView);
        }
        catch(Exception e){}
        
        
        Label msg = new Label("No Internet Connection...");        
        msg.setTextFill(Paint.valueOf("white"));
        msg.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 18));
        
        Label smsg = new Label("This program cannot run offline, we are sorry...");        
        smsg.setTextFill(Paint.valueOf("white"));
        smsg.setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 16));
                
        tutto.getChildren().add(msg);
        tutto.getChildren().add(smsg);
        getChildren().add(tutto);
        
    }    
}
