package engvid;

import java.io.Serializable;

public class Model implements Serializable {
    
    public static final double VERSION = 1.2;
    
    private M_Resources resources;
    private M_Authors authors;
    private double current_version;

    public Model(double vers) {
        resources = new M_Resources();
        authors = new M_Authors();
        current_version = vers;
    }

    public M_Resources getResources() {
        return resources;
    }

    public M_Authors getAuthors() {
        return authors;
    }
    
    public double getCurrent_version() {
        return current_version;
    }
    
}
