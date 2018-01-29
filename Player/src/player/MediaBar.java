/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.Observable;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author Monika
 */
public class MediaBar extends HBox{
    Slider time = new Slider();
    Slider vol = new Slider();
    Button playButton = new Button("||");
    Label volume = new Label("Volume: ");
    MediaPlayer player;
    public MediaBar(MediaPlayer play){
     player = play; 
     setAlignment(Pos.CENTER);
     setPadding(new Insets(5,10,5,10));
     vol.setPrefWidth(70);
     vol.setMinWidth(30);
     vol.setValue(100);
//     time.setPrefWidth(70);
//     time.setMinWidth(30);
     HBox.setHgrow(time,Priority.ALWAYS);
     playButton.setPrefWidth(30);
     getChildren().add(playButton);
     getChildren().add(time);
     getChildren().add(volume);
     getChildren().add(vol);
     setStyle("-fx-background-color: #bfc2c7");
     playButton.setOnAction(new EventHandler<ActionEvent>(){
     public void handle(ActionEvent e){
     Status status  = player.getStatus();
     if(status == status.PLAYING){
         if(player.getCurrentTime().greaterThan(player.getTotalDuration())){
             player.seek(player.getStartTime());player.play();
         }
         else{
         player.pause();
         playButton.setText(">");
         }
     }
     
     if(status == status.PAUSED||status == status.HALTED || status == status.STOPPED){
         playButton.setText("||");
         player.play();
     } 
     }
     });
     player.currentTimeProperty().addListener(new InvalidationListener(){
       @Override
        public void invalidated(javafx.beans.Observable ov) {
           updateValues();
         }
     });
     time.valueProperty().addListener(new InvalidationListener(){

         @Override
         public void invalidated(javafx.beans.Observable o) {
             if(time.isPressed()){
             player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
                     }
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
     });
     vol.valueProperty().addListener(new InvalidationListener(){

         @Override
         public void invalidated(javafx.beans.Observable o) {
             if(vol.isPressed()){
             player.setVolume(vol.getValue()/100);
                     }
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
     });
    }
    protected void updateValues(){
    Platform.runLater(new Runnable(){
    public void run(){
    time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
    }
    });
    
    }
   
}
