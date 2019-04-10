package engvid;

import java.io.Serializable;

public class M_Author implements Serializable {
    
    private String name, description, url, urlyoutube, urlfotina;

    public M_Author(String name, String description, String url, String urlyoutube, String urlfotina) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.urlyoutube = urlyoutube;
        this.urlfotina = urlfotina;
    }

    public String getUrlfotina() {
        return urlfotina;
    }    

    public String getUrlyoutube() {
        return urlyoutube;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
