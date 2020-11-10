package view;

import javafx.scene.Scene;

import java.io.FileNotFoundException;

public interface IScreen {
    Scene getScene() throws FileNotFoundException;
}
