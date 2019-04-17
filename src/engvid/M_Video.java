package engvid;

import java.io.Serializable;
import java.util.ArrayList;

public class M_Video implements Serializable {
    
    private String url, name;
    private boolean seen;
    private ArrayList<Integer> difficulty;
    private ArrayList<String> topics;

    public M_Video(String url, String name, ArrayList<Integer> difficulty, ArrayList<String> topics) {
        this.url = url;
        this.name = name;
        this.seen = false;
        this.difficulty = difficulty;
        this.topics = topics;
    }
    
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<Integer> getDifficulties() {
        return difficulty;
    }

    public boolean isSeen() {
        return seen;
    }
    
    public void setSeen(boolean seen){
        this.seen = seen;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }
    
}
