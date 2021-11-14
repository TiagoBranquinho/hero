import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{

    public Wall(int x, int y){
        super(x,y);
    }
    public void draw(TextGraphics graphics)
    {
        String color = "#FF17F3";
        String symbol = "|";
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), symbol);
    }
}
