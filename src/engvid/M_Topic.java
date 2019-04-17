package engvid;

import java.io.Serializable;

public class M_Topic implements Serializable {
    
    private String nome, url;

    public M_Topic(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public String getUrl() {
        return url;
    }
    
}
