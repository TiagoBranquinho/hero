import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private Position position = new Position(0,0);

    public Wall(int x, int y){
        position.setX(x);
        position.setY(y);
    }
    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#F7E731"));
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), "|");
    }

    public Position getPosition() {
        return position;
    }
}
