package view;

import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileNotFoundException;

public interface IScreen {
    Scene getScene() throws FileNotFoundException;
}
