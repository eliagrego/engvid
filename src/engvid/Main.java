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
    
    private VideoBrowse videoBrowse;
    private boolean videoBrowseflag;
    
    public Main(EngVid engVid) {
        
        this.engVid = engVid;
        
        if(EngVid.isReachableByPing("www.engvid.com")){
            
            welcomeflag = false;
            resourceflag = false;
            authorflag = false;
            videoBrowseflag = false;

            menu = new Menu(this);

            setBackground(V.MAINBG);

            getChildren().add(menu);
            setWelcome();
            setVideoBrowse();
            
        }
        else{
            
            engVid.getPrimaryStage().setMaxWidth(400);
            engVid.getPrimaryStage().setMaxHeight(200);
            getChildren().add(new NoInternetMessage());
            
        }
        
    }
    
    public Auth_GUI getAuthGui() {
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
        if(getChildren().size() == 2){            
           getChildren().remove(1);
        }
        getChildren().add(resource);
    }
    
    public void setAuthors() {
        if(!authorflag){
            auth_gui = new Auth_GUI(this);
            authorflag = true;
        }
        if(getChildren().size() == 2){            
           getChildren().remove(1);
        }
        getChildren().add(auth_gui);
    }
        
    public void setAuthorDetail(M_Author auth){   
        AuthDetail authdet = new AuthDetail(this, auth);     
        getChildren().remove(1);
        getChildren().add(authdet);
    }
    
    public void setVideoBrowse() {
        if(!videoBrowseflag){
            videoBrowse = new VideoBrowse(this, null);
            videoBrowseflag = true;
        }
        getChildren().remove(1);
        getChildren().add(videoBrowse);
    }

    public VideoBrowse getVideoBrowse() {
        return videoBrowse;
    }
    
}
