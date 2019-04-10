package engvid;

import java.awt.Desktop;
import java.net.URI;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class BoxLastVideo extends HBox {
    
    private Welcome welcome;
    private String author;
    private String IMGauthor;
    private String title;
    private String content;
    private String url;

    public BoxLastVideo(Welcome welcome, String author, String IMGauthor, String title, String content, String url) {
        
        this.welcome = welcome;
        this.author = author;
        this.IMGauthor = IMGauthor;
        this.title = title;
        this.content = content;
        this.url = url;
        
        setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
        setMaxWidth(V.WIDTH - V.WIDTH_MENU - 160);
        setBorder(new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        
        setAlignment(Pos.CENTER);
                
        VBox autore = new VBox();
        autore.setSpacing(10);
        autore.setPadding(new Insets(41, 5, 40, 15));
        
        ImageView imageView = ImageViewBuilder.create()
                .image(new Image(IMGauthor))
                .build();
        
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);     
        
        StackPane sp = new StackPane();
        sp.setMinWidth(102);
        sp.setMaxWidth(102);
        sp.setMinHeight(102);
        sp.setMaxHeight(102);
        sp.setBorder(new Border(new BorderStroke(Paint.valueOf("#444"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        
        sp.getChildren().add(imageView);
                     
        Label nome = new Label(author);
        nome.setMinWidth(100);
        nome.setMaxWidth(100);
        nome.setAlignment(Pos.CENTER);
        nome.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 18));
        
        autore.getChildren().add(sp);   
        autore.getChildren().add(nome);
        
        VBox right = new VBox();
        right.setPadding(new Insets(10, 20, 20, 10));
        right.setSpacing(10);
        
        Label rightTit = new Label(title);
        rightTit.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 15));
        rightTit.setCursor(Cursor.HAND);
        rightTit.setAlignment(Pos.CENTER);
        rightTit.setTextAlignment(TextAlignment.CENTER);
        rightTit.setMinWidth(500);
        
        rightTit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {                
                try {                    
                    Desktop.getDesktop().browse(new URI(url));                      
                }catch(Exception e){}
            }
        });
        
        TextArea description = new TextArea(content);
        description.setWrapText(true);
        description.setEditable(false);
        description.setStyle("-fx-focus-color: white; -fx-faint-focus-color: white; -fx-background-color: #88ffee;");
        description.setPadding(new Insets(2));
        description.setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 13));        
        description.setMaxHeight(200);
        description.setBorder(new Border(new BorderStroke(Paint.valueOf("#26b1cf"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        description.setBackground(new Background(new BackgroundFill(Paint.valueOf("#88ffee"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        right.getChildren().add(rightTit);
        right.getChildren().add(description);
        
        getChildren().add(autore);
        getChildren().add(right);
        
    }
    
    public String get_title(){
        return this.title;
    }
    public String get_content(){
        return this.content;
    }
    public String get_url(){
        return this.url;
    }

}
