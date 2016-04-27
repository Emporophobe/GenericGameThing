package app;

import Controller.KeyHandler;
import Model.GameObjects.World;
import Model.UIObjects.UI;
import View.DrawBackground;
import View.DrawUI;
import View.DrawWorld;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up Javafx objects
        Group root = new Group();
        Scene theScene = new Scene(root);
        Canvas canvas = new Canvas(UI.getScreenWidth(), UI.getScreenHeight());

        root.getChildren().add(canvas);
        primaryStage.setScene(theScene);
        primaryStage.show();

        // the gc is what is drawn to
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Make the world and populate it
        World w = new World();
        w.initWorld();

        UI ui = new UI();
        ui.initUI();

        // Key handlers
        theScene.setOnKeyPressed(KeyHandler::handleKeyPressed);
        theScene.setOnKeyReleased(KeyHandler::handleKeyReleased);

        // Animation loop
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                w.onTick();
                ui.onTick(w);

                KeyHandler.processKeys(w);

                gc.clearRect(0, 0, UI.getScreenWidth(), UI.getScreenHeight());
                DrawBackground.draw(gc);
                DrawWorld.draw(w, gc);
                DrawUI.draw(ui, gc);
            }
        }.start();
    }
}