package engvid;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ResourcesCNT extends VBox {
    
    private Resources res;

    public ResourcesCNT(Resources res) {
                        
        this.res = res;
        
        fillResources();
        
    }
        
    private void fillResources(){
        try {
                        
            Document doc = Jsoup.connect("http://www.engvid.com/english-resources/").get();
            Elements list = doc.select(".lessonlinks_all_row");
            
            Model model = ObjectReader.getModel();
            
            for(int i = 0; i < list.size(); i++){
                
                String url = list.get(i).children().get(1).attr("href");
                String nome = list.get(i).children().select(".lessonlinks_all_lessontitle").text();
                
                M_Res mres = new M_Res(url, nome);                
                        
                int x = model.getResources().contains(mres);
                if(x < 0){                
                    model.getResources().addRes(mres);                                                
                }else{                    
                    boolean l = false;
                    if(i == list.size() - 1){
                        l = true;
                    }                    
                    getChildren().add(new Resources_Row(this, model.getResources().getResources().get(x), i, l, false));
                }
                                
            }
                        
            ObjectReader.saveModel(model);
            
        } catch (Exception ex) {
                       
            res.getTitolo().setText("Resources - (Offline)");
            
            Model m_offline = ObjectReader.getModel();
            if(m_offline.getResources().getResources().size() == 0){
                
                Label warn = new Label("No resource available.\nCheck your internet connection and retry...");
                warn.setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 14));
                getChildren().add(warn);
                
                Button retry = new Button("Retry now");
                retry.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        getChildren().removeAll(getChildren());
                        fillResources();
                    }
                });
                getChildren().add(retry);
                
            }
            else{             
                Model a = ObjectReader.getModel();                
                for(int i = 0; i < a.getResources().getResources().size(); i++){                    
                    boolean l = false;
                    if(i == a.getResources().getResources().size() - 1){
                        l = true;
                    }                    
                    getChildren().add(new Resources_Row(this, m_offline.getResources().getResources().get(i), i, l, true));                    
                }                
            }
            
        }
    }

    public Resources getRes() {
        return res;
    }
    
}
