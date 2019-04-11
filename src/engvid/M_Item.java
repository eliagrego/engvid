package engvid;

import java.io.Serializable;

public class M_Item implements Serializable {

    private boolean isResource;
    private M_Res resource;
    private M_Video video;
    private M_Author auth;

    public M_Item(M_Video video, M_Author auth) {
        this.isResource = false;
        this.resource = null;
        this.video = video;
        this.auth = auth;
    }
    
    public M_Item(M_Res res, M_Author auth) {
        this.isResource = true;
        this.resource = res;
        this.video = null;
        this.auth = auth;
    }

    public boolean isResource() {
        return isResource;
    }
    
    public boolean isVideo() {
        return !isResource;
    }

    public M_Res getResource() {
        return resource;
    }

    public M_Video getVideo() {
        return video;
    }

    public M_Author getAuthor() {
        return auth;
    }
    
}
