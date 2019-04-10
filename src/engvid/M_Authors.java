package engvid;

import java.io.Serializable;
import java.util.ArrayList;

public class M_Authors implements Serializable {
    
    private ArrayList<M_Author> authors;

    public M_Authors() {
        authors = new ArrayList<M_Author>();
    }
    
    public M_Author get(String name){
        for(M_Author auth : authors){
            if(auth.getName().toLowerCase().trim().equals(name.toLowerCase().trim())){
                return auth;
            }
        }
        return null;
    }
    
    public void add(M_Author auth){
        authors.add(auth);
    }
    
    public void reset(){
        authors.removeAll(authors);
    }
    
    public int size(){
        return authors.size();
    }

    public ArrayList<M_Author> getList() {
        return authors;
    }
    
}
