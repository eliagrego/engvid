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
                    getChildren().add(new Resources_Row(this, model.getResources().getResources().get(x), i, l));
                }
                                
            }
                        
            ObjectReader.saveModel(model);
            
        } catch (Exception ex) {}
        
    }

    public Resources getRes() {
        return res;
    }
    
}
