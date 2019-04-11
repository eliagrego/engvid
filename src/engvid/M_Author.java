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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof M_Author){
            M_Author m2 = (M_Author)obj;
            if(m2.getName().trim().toLowerCase().equals(name.trim().toLowerCase())){
                if(m2.getDescription().trim().toLowerCase().equals(description.trim().toLowerCase())){
                    if(m2.getUrl().trim().toLowerCase().equals(url.trim().toLowerCase())){
                        if(m2.getUrlfotina().trim().toLowerCase().equals(urlfotina.trim().toLowerCase())){
                            if(m2.getUrlyoutube().trim().toLowerCase().equals(urlyoutube.trim().toLowerCase())){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
}
