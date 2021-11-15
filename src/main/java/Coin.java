import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {

    public Coin(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        final String color = "#F7E731";
        final String symbol = "o";
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), symbol);
    }
}