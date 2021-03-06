package engvid;

import java.io.Serializable;

public class M_Res implements Serializable {
    
    private String url, name;
    private boolean seen;

    public M_Res(String url, String name) {
        this.url = url;
        this.name = name;
        this.seen = false;
    }
    
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public boolean isSeen() {
        return seen;
    }
    
    public void setSeen(boolean seen){
        this.seen = seen;
    }
    
}
