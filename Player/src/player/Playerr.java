/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

/**
 *
 * @author Monika
 */
import java.io.File;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Playerr extends BorderPane {

    Media media;
    MediaPlayer player;
    MediaView view;
    Pane mpane;
    MediaBar bar;

    public Playerr(String file) {
        File f = new File(file.substring(8));
        if (f.exists()) {

            media = new Media(file);
            System.out.println("Media is created");
            player = new MediaPlayer(media);
            view = new MediaView(player);
            mpane = new Pane();
            mpane.getChildren().add(view);
            setCenter(mpane);
            bar = new MediaBar(player);
            setBottom(bar);
            //   setStyle("-fx-background-color: #ff0000");
            player.play();
        }else{
         
            mpane = new Pane();
            
            setCenter(mpane);
            
        
        }
     }
}
