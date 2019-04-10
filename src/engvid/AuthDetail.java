package engvid;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class AuthDetail extends VBox {
    
    private Main main;

    public AuthDetail(Main main, M_Author autore) {
        
        this.main = main;
        
        setMinWidth(V.WIDTH - V.WIDTH_MENU + 10);
        setPadding(new Insets(20));
        setSpacing(15);
                
        Label titolo = new Label("Authors Detail - " + autore.getName());
        titolo.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
        titolo.setTextFill(Paint.valueOf("white"));
                
        getChildren().add(titolo);
        
        HBox top = new HBox();
        top.setMinHeight(280);
        top.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        try{
            
            ImageView imageView = ImageViewBuilder.create()
                    .image(new Image(autore.getUrlfotina()))
                    .build();

            imageView.setFitHeight(180);
            imageView.setFitWidth(180);
                      
            top.setBorder(new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            VBox destra = new VBox();

            Label nome = new Label(autore.getName());
            nome.setMinWidth(589);
            nome.setTextAlignment(TextAlignment.CENTER);
            nome.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 16));
            nome.setBackground(new Background(new BackgroundFill(Paint.valueOf("#bceaf4"), CornerRadii.EMPTY, Insets.EMPTY)));
            nome.setPadding(new Insets(5, 10, 5, 10));
            nome.setBorder(new Border(new BorderStroke(Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5), Insets.EMPTY)));

            
            StackPane yt = new StackPane();
            yt.setAlignment(Pos.TOP_CENTER);
            yt.setPadding(new Insets(10));
            
            yt.setMinWidth(589);
            yt.setMinHeight(280);
            yt.setBorder(new Border(new BorderStroke(Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5), Insets.EMPTY)));

            Label desc = new Label(autore.getDescription());
            desc.setWrapText(true);
            
            HBox bottoniSotto = new HBox();
            bottoniSotto.setSpacing(30);
            bottoniSotto.setPadding(new Insets(10, 0, 0, 0));
            bottoniSotto.setAlignment(Pos.CENTER);
            
            AuthDetailButton adb1 = new AuthDetailButton(this, autore, 0);
            AuthDetailButton adb2 = new AuthDetailButton(this, autore, 1);
            AuthDetailButton adb3 = new AuthDetailButton(this, autore, 2);
            
            top.getChildren().add(imageView);  
            destra.getChildren().add(nome);
            top.getChildren().add(destra);
            yt.getChildren().add(desc);  
            destra.getChildren().add(yt);   
            getChildren().add(top);            
            bottoniSotto.getChildren().add(adb1);
            bottoniSotto.getChildren().add(adb2);
            bottoniSotto.getChildren().add(adb3);
            getChildren().add(bottoniSotto);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    public Main getMain() {
        return main;
    }
    
}