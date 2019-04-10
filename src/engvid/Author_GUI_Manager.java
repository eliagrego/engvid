package engvid;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
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

public class Author_GUI_Manager extends VBox {
    
    private Auth_GUI auth_GUI;
    private ArrayList<M_Author> autori;
    private boolean offline;

    public Author_GUI_Manager(Auth_GUI auth_GUI, ArrayList<M_Author> autori, boolean offline) {
        
        this.auth_GUI = auth_GUI;
        this.autori = autori;
        
        int COLONNE = 3;
        int RIGHE = (int)Math.ceil(autori.size()/((double)COLONNE));
        setSpacing(15);
        
        int ind[];
        for(int i = 0; i < RIGHE - 1; i++){
            ind = new int[COLONNE];
            for(int j = 0; j < COLONNE; j++){
                ind[j] = j + (i * COLONNE);
            }
            getChildren().add(new Riga(ind));
        }
        
        int n = autori.size() % COLONNE;
        if(n == 0){
            n = COLONNE;
        }        
        ind = new int[n];
        for(int j = 0; j < n; j++){
            ind[j] = j + (COLONNE * (RIGHE - 1));
        }
        getChildren().add(new Riga(ind));
        
    }

    public Auth_GUI getAuth_GUI() {
        return auth_GUI;
    }    
    
    class Riga extends HBox {
        public Riga(int[] indexes) {
            setSpacing(24);
            for(int i : indexes){
                getChildren().add(new Cella(i));
            }            
        }        
    }

    class Cella extends HBox {
        
        private Button ytbtn;
        
        public Cella(int id) {
            
            M_Author auth = autori.get(id);
            
            setMinWidth(240);
            setMinHeight(100);
            
            if(!offline){
                
                ImageView imageView = ImageViewBuilder.create()
                    .image(new Image(auth.getUrlfotina()))
                    .build();

                imageView.setFitHeight(100);
                imageView.setFitWidth(100); 

                setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
                setBorder(new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

                getChildren().add(imageView);
                
                VBox destra = new VBox();
                
                Label nome = new Label(auth.getName());
                nome.setMinWidth(138);
                nome.setTextAlignment(TextAlignment.CENTER);
                nome.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 16));
                nome.setBackground(new Background(new BackgroundFill(Paint.valueOf("#bceaf4"), CornerRadii.EMPTY, Insets.EMPTY)));
                nome.setPadding(new Insets(5, 10, 5, 10));
                nome.setBorder(new Border(new BorderStroke(Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5), Insets.EMPTY)));
                nome.setCursor(Cursor.HAND);
                nome.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try{
                            getAuth_GUI().getMain().setAuthorDetail(auth);
                        }catch(Exception e){}
                    }
                });
                nome.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        nome.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9bddeb"), CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                });
                nome.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        nome.setBackground(new Background(new BackgroundFill(Paint.valueOf("#bceaf4"), CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                });
                
                ytbtn = new Button("   Youtube   ");
                ytbtn.setBorder(new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
                ytbtn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#8dd7e7"), CornerRadii.EMPTY, Insets.EMPTY)));
                ytbtn.setTextFill(Paint.valueOf("black"));
                ytbtn.setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 13));
                ytbtn.setCursor(Cursor.HAND);
                ytbtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ytbtn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#11505d"), CornerRadii.EMPTY, Insets.EMPTY)));
                        ytbtn.setTextFill(Paint.valueOf("white"));
                        ytbtn.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 13));
                    }
                });
                ytbtn.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ytbtn.setBackground(new Background(new BackgroundFill(Paint.valueOf("#8dd7e7"), CornerRadii.EMPTY, Insets.EMPTY)));
                        ytbtn.setTextFill(Paint.valueOf("black"));
                        ytbtn.setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 13));
                    }
                });
                ytbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try{
                            Desktop.getDesktop().browse(new URI(auth.getUrlyoutube()));   
                        }catch(Exception e){}
                    }
                });
        
                destra.getChildren().add(nome);
                StackPane yt = new StackPane();
                yt.setMinWidth(138);
                yt.setMinHeight(63);
                yt.setBorder(new Border(new BorderStroke(Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), Paint.valueOf("#2089a0"), BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5), Insets.EMPTY)));
        
                yt.getChildren().add(ytbtn);                
                destra.getChildren().add(yt);                
                getChildren().add(destra);
                
            }
            
        }        
    }

    
}
