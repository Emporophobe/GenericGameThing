package Model.UIObjects;

import Model.GameObjects.World;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

class ConsoleMessageBox extends MessageBox{
    private List<String> messages = new ArrayList<>();

    private int numLines = 5;

    private ConsoleMessageBox(){
        super("This is the console",
                new Font("Consolas", 12),
                new Point2D(0, 500),
                UI.getScreenWidth(),
                100,
                Color.BLACK,
                Color.WHITE);
    }

    ConsoleMessageBox(int numLines){
        this();
        this.numLines = numLines;
    }

    @Override
    public void draw(GraphicsContext gc){
        String text;
        int len = messages.size();

        if(len <= numLines){
            text = String.join("\n", messages);
        }
        else{
            text = String.join("\n", messages.subList(len - numLines, len));
        }

        super.setMessage(text);
        super.draw(gc);
    }

    @Override
    public void onTick(World w){
        setConsoleMessages(w.getMessages());
    }

    private void setConsoleMessages(List<String> messages){
        this.messages = messages;
    }

}
