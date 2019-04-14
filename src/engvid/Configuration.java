package engvid;

import java.util.ArrayList;

public class Configuration {
    
    private ArrayList<M_Author> autori;
    private ArrayList<String> topics;
    private boolean[] difficulties;

    public Configuration() {
        autori = new ArrayList<M_Author>();
        topics = new ArrayList<String>();
        difficulties = new boolean[3];        
    }

    public ArrayList<M_Author> getAutori() {
        return autori;
    }

    public boolean[] getDifficulties() {
        return difficulties;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }
    
    public void reset(){
        autori.removeAll(autori);
        topics.removeAll(topics);
        for(boolean b : difficulties){
            b = false;
        }
    }
    
    public boolean isEmpty(){
        return autori.size() == 0 && topics.size() == 0 && !difficulties[0] && !difficulties[1] && !difficulties[2];
    }
    
}
