import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {

    public Coin(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        String color = "#F7E731";
        String symbol = "o";
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), symbol);
    }
}