package engvid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EngVid extends Application {
    
    private Main main;
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
                
        this.primaryStage = primaryStage;
        main = new Main(this);
        
        Scene scene = new Scene(main, V.WIDTH, V.HEIGHT);
                
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:engvid.png"));
        primaryStage.setTitle(V.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Main getMain() {
        return main;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static boolean isReachableByPing(String host) {
        try{
            String cmd = "";
            if(System.getProperty("os.name").startsWith("Windows")) {
                cmd = "ping -n 1 " + host;
            } else {
                cmd = "ping -c 1 " + host;
            }
            Process myProcess = Runtime.getRuntime().exec(cmd);
            myProcess.waitFor();
            return myProcess.exitValue() == 0;
        } catch( Exception e ) {
            return false;
        }
    }
    
}
