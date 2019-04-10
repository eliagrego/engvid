package engvid;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Auth_GUI extends VBox {
    
    private Main main;

    public Auth_GUI(Main main) {
        
        this.main = main;
                
        setMinWidth(V.WIDTH - V.WIDTH_MENU + 10);
        setPadding(new Insets(20));
        setSpacing(15);
                
        Label titolo = new Label("Authors");
        titolo.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
        titolo.setTextFill(Paint.valueOf("white"));
        
        getChildren().add(titolo);
        
        try{

            ArrayList<M_Author> autori = new ArrayList<M_Author>();

            Document topicsDoc = Jsoup.connect("https://www.engvid.com/").get();       
            Elements topics = topicsDoc.select("#browse_teachers_select .browse_topics_list_select div a");
            
            Model mod = ObjectReader.getModel();
            mod.getAuthors().reset();
            
            for(Element e : topics){  
                
                String name = e.text();
                String url = "https://www.engvid.com" + e.attr("onclick").split("'")[3];
                
                Document detUser = Jsoup.connect(url).get();   
                Elements dets = detUser.select("#teacher_teacher_info");
                
                Element urlYT = detUser.select("#teacher_teacher_info_side a").get(0);
                String YT = "https:" + urlYT.attr("href").replace("?sub_confirmation=1", "");                
                
                String urlfoto = e.children().select("img").attr("src").replace("?s=32&r=x&d=wavatar", "?s=120&r=x&").replace("//", "https://");
                
                String descr = dets.text();
                mod.getAuthors().add(new M_Author(name, descr, url, YT, urlfoto));
                autori.add(new M_Author(name, descr, url, YT, urlfoto));
                        
            }

            ObjectReader.saveModel(mod);
            
            Author_GUI_Manager agm = new Author_GUI_Manager(this, autori, false);
            getChildren().add(agm);
        
        }catch(Exception e){
            
            titolo.setText("Authors (Offline)");
            
            Model m = ObjectReader.getModel();
            if(m.getAuthors().size() > 0){
                
                for(M_Author a : m.getAuthors().getList()){
                    System.out.println(a.getName() + " - " + a.getDescription());
                }
                
            }
            
        }
        
    }

    public Main getMain() {
        return main;
    }
    
}