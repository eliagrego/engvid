package engvid;

import java.io.Serializable;
import java.util.ArrayList;

public class M_Resources implements Serializable {
    
    private ArrayList<M_Res> resources;

    public M_Resources() {
        this.resources = new ArrayList<M_Res>();
    }
    
    public int contains(M_Res m2){
        for(int i = 0; i < resources.size(); i++){            
            if(resources.get(i).getName().equals(m2.getName()) && resources.get(i).getUrl().equals(m2.getUrl())){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<M_Res> getResources() {
        return resources;
    }
    
    public void addRes(M_Res mres){
        this.resources.add(mres);
    }
    
    public ArrayList<M_Res> search(String keyword){
        ArrayList<M_Res> ret = new ArrayList<M_Res>();
        for(M_Res m : resources){
            if(m.getName().toLowerCase().contains(keyword.toLowerCase().trim())){
                ret.add(m);
            }            
        }
        return ret;
    }
    
}
