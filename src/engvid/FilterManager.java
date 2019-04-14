package engvid;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FilterManager extends VBox {
    
    private VideoBrowse video;
    private Stage stage;
    private Configuration config;

    public FilterManager(VideoBrowse video, Stage stage, Configuration conf) {
        
        this.video = video;
        this.stage = stage;
        this.config = conf;
        
        setBackground(V.MAINBG);
                
        Button tmp = new Button("ok");
        tmp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                video.returnFromFilter(config);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                
            }
        });
        getChildren().add(tmp);
        
    }

    public VideoBrowse getVideo() {
        return video;
    }    
    
}
