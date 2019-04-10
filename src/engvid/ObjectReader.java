package engvid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectReader {
    
    private static final String FILE_NAME = "engvid.model";
    
    public static Model createModel(){
        Model m = new Model(Model.VERSION);
        saveModel(m);
        return m;
    }
    
    public static boolean saveModel(Model m){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(FILE_NAME)));
            oos.writeObject(m);
            oos.flush();
            oos.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static Model getModel(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FILE_NAME)));
            Object o = ois.readObject();
            if(o instanceof Model){
                ois.close();
                Model tmp = (Model)o;
                if(tmp.getCurrent_version() == Model.VERSION){
                    return tmp;
                }
            }
        } catch (Exception ex) {}
        return createModel();
    }
    
}
