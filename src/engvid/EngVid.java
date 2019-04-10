package engvid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EngVid extends Application {
    
    private Main main;
    
    @Override
    public void start(Stage primaryStage) {
                
        main = new Main(this);
        Scene scene = new Scene(main, V.WIDTH, V.HEIGHT);
                
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:engvid.png"));
        primaryStage.setTitle(V.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public Main getMain() {
        return main;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
