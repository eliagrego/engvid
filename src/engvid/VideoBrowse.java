package engvid;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VideoBrowse extends VBox {
    
    private Main main;
    private VideoBrowse itself;
    private Configuration conf;
    private Label titolo;
    
    public VideoBrowse(Main main, Configuration c) {
        
        this.main = main;
        this.itself = this;
        if(c == null){
            this.conf = new Configuration();
        }
        else{
            this.conf = c;
        }
            
        setPadding(new Insets(20));
        setSpacing(10);
        
        if(EngVid.isReachableByPing("www.engvid.com")){
            
            HBox header = new HBox(10);
            
            titolo = new Label("Videos");
            titolo.setTextFill(Paint.valueOf("white"));
            titolo.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
            
            Button filterMgr = new Button("Manage Filters");
            filterMgr.setBorder(new Border(new BorderStroke(Paint.valueOf("white"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            filterMgr.setBackground(new Background(new BackgroundFill(Paint.valueOf("#a5d6e1"), CornerRadii.EMPTY, Insets.EMPTY)));
            filterMgr.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 14));
            filterMgr.setTextFill(Paint.valueOf("#20545f"));
            filterMgr.setCursor(Cursor.HAND);

            filterMgr.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    filterMgr.setBackground(new Background(new BackgroundFill(Paint.valueOf("#20545f"), CornerRadii.EMPTY, Insets.EMPTY)));
                    filterMgr.setTextFill(Paint.valueOf("white"));
                }
            });
            filterMgr.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    filterMgr.setBackground(new Background(new BackgroundFill(Paint.valueOf("#a5d6e1"), CornerRadii.EMPTY, Insets.EMPTY)));
                    filterMgr.setTextFill(Paint.valueOf("#20545f"));
                }
            });
            filterMgr.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {                    
                    try {
                        Stage stage = new Stage();                     
                        stage.setTitle("My New Stage Title");
                        FilterManager fm = new FilterManager(itself, main.getEngVid().getPrimaryStage(), conf, stage);   
                        stage.setScene(new Scene(fm, 600, 500));                        
                        stage.setResizable(false);
                        stage.getIcons().add(new Image("file:engvid.png"));
                        stage.setTitle(V.TITLE + " [Filters]");
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });            
            header.getChildren().add(titolo);
            header.getChildren().add(filterMgr);
            getChildren().add(header);       
                        
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
    
    public void applyFilter(Configuration c){
        if(c.getAutori().size() > 0){
            titolo.setText(c.getAutori().get(0).getName());
        }
    }

    public void returnFromFilter(Configuration c){
        this.conf = c;
    }
    
    public Main getMain() {
        return main;
    }
    
}