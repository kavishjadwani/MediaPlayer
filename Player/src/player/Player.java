/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import com.sun.prism.paint.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

/**
 *
 * @author Kavish
 */
public class Player extends Application {

    private double decorationWidth;
    private double decorationHeight;
    Stage primaryStage;
    //C:\Users\Monika\Desktop\CSCI 455 X\Player
    //@Override
    Playerr player;
    FileChooser filechooser;

    public void start(final Stage primaryStage) {

        this.primaryStage = primaryStage;
        MenuItem open = new MenuItem("Open");
        Menu file = new Menu("File");
        MenuBar menu = new MenuBar();
        file.getItems().add(open);
        menu.getMenus().add(file);
        filechooser = new FileChooser();
        open.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //  player.player.pause();
                File file = filechooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        player.player.pause();
                        player = new Playerr(file.toURI().toURL().toExternalForm());
                        Scene scene = new Scene(player, 720, 640, Paint.valueOf("BLACK"));
                        MenuItem open2 = new MenuItem("Open");
                        Menu file2 = new Menu("File");
                        MenuBar menu = new MenuBar();
                        file2.getItems().add(open2);
                        menu.getMenus().add(file2);
                        primaryStage.setScene(scene);
                        //primaryStage.setFullScreen(true);
                        //primaryStage.show();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        player = new Playerr("file:///C:/Users/Monika/Desktop/MediaPlayer/YoungDumbThunder.mp4");
        player.setTop(menu);
        final double initialSceneWidth = 720;
        final double initialSceneHeight = 640;
        Scene scene = new Scene(player, initialSceneHeight, initialSceneWidth, Paint.valueOf("BLACK"));

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        this.decorationWidth = initialSceneWidth - scene.getWidth();
        this.decorationHeight = initialSceneHeight - scene.getHeight();
    }

    public void resizeScene(double width, double height) {
        primaryStage.setWidth(width + this.decorationWidth);
        primaryStage.setHeight(height + this.decorationHeight);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
