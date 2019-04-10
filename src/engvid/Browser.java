package engvid;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    private double width, height;
    private String url;

    public Browser(double width, double height, String url) {
        
        this.width = width;        
        this.height = height;
        this.url = url;
        
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load(url);
        //add the web view to the scene
        getChildren().add(browser);

    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        layoutInArea(browser, 0, 0, width, height, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return width;
    }

    @Override protected double computePrefHeight(double width) {
        return height;
    }
}