package engvid;

import java.io.Serializable;
import java.util.ArrayList;

public class M_Content implements Serializable {
    
    private ArrayList<M_Item> items;

    public M_Content() {
        this.items = new ArrayList<M_Item>();
    }

    public ArrayList<M_Item> getItems() {
        return items;
    }
    
    public void reset(){
        items.removeAll(items);
    }
    
    public ArrayList<M_Item> getOnlyVideo(){
        ArrayList<M_Item> videos = new ArrayList();
        for(M_Item i : items){
            if(i.isVideo()){
                videos.add(i);
            }
        }
        return videos;
    }
    
    public ArrayList<M_Item> getOnlyResources(){
        ArrayList<M_Item> res = new ArrayList();
        for(M_Item i : items){
            if(i.isResource()){
                res.add(i);
            }
        }
        return res;
    }
    
    public ArrayList<M_Item> getItemsFrom(M_Author auth){
        ArrayList<M_Item> res = new ArrayList();
        for(M_Item i : items){
            if(i.getAuthor().equals(auth)){
                res.add(i);
            }
        }
        return res;
    }
    
    public ArrayList<M_Item> getItemsFrom(M_Author auth, boolean videoOnly){
        ArrayList<M_Item> res = new ArrayList();
        for(M_Item i : items){
            if(i.getAuthor().equals(auth)){
                if(videoOnly && i.isVideo()){
                    res.add(i);
                }
            }
        }
        return res;
    }
    
}
