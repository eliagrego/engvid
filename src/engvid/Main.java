package engvid;

import javafx.scene.layout.HBox;

public class Main extends HBox {
    
    private EngVid engVid;
    private Menu menu;
    
    private Welcome welcome;
    private boolean welcomeflag;
    
    private Resources resource;
    private boolean resourceflag;
    
    private Auth_GUI auth_gui;
    private boolean authorflag;
    
    private P3_1 p3;
    private boolean p3flag;
    
    public Main(EngVid engVid) {
        
        this.engVid = engVid;
        
        welcomeflag = false;
        resourceflag = false;
        authorflag = false;
        p3flag = false;
        
        menu = new Menu(this);
        
        setBackground(V.MAINBG);
        
        getChildren().add(menu);
        setWelcome();
        setAuthors();
        
    }

    public P3_1 getP3() {
        return p3;
    }
    
    public Auth_GUI getP2() {
        return auth_gui;
    }

    public Resources getResource() {
        return resource;
    }

    public EngVid getEngVid() {
        return engVid;
    }
    
    public void setWelcome() {
        if(!welcomeflag){
            welcome = new Welcome(this);
            welcomeflag = true;
        }        
        if(getChildren().size() == 2){            
            getChildren().remove(1);
        }
        getChildren().add(welcome);
    }

    public void setResource() {
        if(!resourceflag){
            resource = new Resources(this);
            resourceflag = true;
        }        
        getChildren().remove(1);
        getChildren().add(resource);
    }
    
    public void setAuthors() {
        if(!authorflag){
            auth_gui = new Auth_GUI(this);
            authorflag = true;
        }
        getChildren().remove(1);
        getChildren().add(auth_gui);
    }
        
    public void setAuthorDetail(M_Author auth){   
        AuthDetail authdet = new AuthDetail(this, auth);     
        getChildren().remove(1);
        getChildren().add(authdet);
    }
    
    public void setP3() {
        if(!p3flag){
            p3 = new P3_1(this);
            p3flag = true;
        }
        getChildren().remove(1);
        getChildren().add(p3);
    }
    
}