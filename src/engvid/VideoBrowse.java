package engvid;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
            
            ArrayList<M_Video> videos = new ArrayList<M_Video>();
            
            String content = null;
            URLConnection connection = null;
            try {
                                
                connection =  new URL("https://www.engvid.com/english-lesson-browser/").openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content = scanner.next();
                scanner.close();                
                
                content = content.replace("&#038;", "&");
                content = content.replace("&#8216;", "\'");
                content = content.replace("&#8217;", "\'");
                content = content.replace("&#8211;", "-");
                content = content.replace("&#8212;", "-");
                content = content.replace("&#8221;", "\"");
                content = content.replace("&#8220;", "\"");
                content = content.replace("&#8230;", "...");            
                content = content.replace("&nbsp;", "");
                content = content.replace("&bull;", "");
                content = content.replace("&#x1f494;", "");
                content = content.replace("️", "");
                content = content.replace("&amp;", "&");            
                String[] tmp = content.split("<div");
                for(int i = 2; i < tmp.length; i++){
                    String[] tutto = tmp[i].split("</div>")[0].split("<a")[2].split("</a>")[0].split("\n");                
                    String link = tutto[0].split("\"")[1].trim();
                    String titolo = tutto[1].split(">")[1].split("<")[0].trim();                
                    ArrayList<Integer> levels = new ArrayList<Integer>();
                    ArrayList<String> topics = new ArrayList<String>();    
                    tutto[3] = tutto[3].trim();
                    String[] descr = tutto[3].split(">");                
                    for(int j = 0; j < descr.length; j++){
                        if(j%2 == 1){
                            String item = descr[j].replace("</span", "").trim();
                            if(item.charAt(0) >= '1' && item.charAt(0) <= '3'){
                                levels.add(Integer.parseInt(String.valueOf(item.charAt(0))));
                            }
                            else{                            
                                String[] words = item.split(" ");
                                String newItem = "";
                                for(int k = 0; k < words.length; k++){
                                    newItem += words[k].substring(0, 1).toUpperCase().concat(words[k].substring(1).toLowerCase()) + " ";
                                }
                                newItem = newItem.trim();                           
                                topics.add(newItem);
                            }
                        }
                    }

                    if(!link.contains("english-resource")){
                        M_Video video = new M_Video(link, titolo, levels, topics);
                        videos.add(video);
                    }

                }

                header.getChildren().add(titolo);
                header.getChildren().add(filterMgr);
                getChildren().add(header);  
                
            }catch ( Exception ex ) {
                
                main.getChildren().removeAll(main.getChildren());
                main.getEngVid().getPrimaryStage().setMaxWidth(400);
                main.getEngVid().getPrimaryStage().setMaxHeight(200);
                main.getEngVid().getPrimaryStage().setTitle("EngVid - Exception");
                main.getChildren().add(new ExceptionError(ex));
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