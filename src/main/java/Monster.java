import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element{

    public Monster(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        final String color = "#BFFF1F";
        final String symbol = "M";
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), symbol);
    }

    public Position move(Position hero_position){
        if(getPosition().getX() > hero_position.getX() && getPosition().getY() > hero_position.getY())
            return (new Position(getPosition().getX() - 1,getPosition().getY() - 1));
        else if(getPosition().getX() > hero_position.getX() && getPosition().getY() < hero_position.getY())
            return (new Position(getPosition().getX() - 1,getPosition().getY() + 1));
        else if(getPosition().getX() > hero_position.getX() && getPosition().getY() == hero_position.getY())
            return (new Position(getPosition().getX() - 1,getPosition().getY()));
        else if(getPosition().getX() < hero_position.getX() && getPosition().getY() > hero_position.getY())
            return (new Position(getPosition().getX() + 1,getPosition().getY() - 1));
        else if(getPosition().getX() < hero_position.getX() && getPosition().getY() < hero_position.getY())
            return (new Position(getPosition().getX() + 1,getPosition().getY() + 1));
        else if(getPosition().getX() < hero_position.getX() && getPosition().getY() == hero_position.getY())
            return (new Position(getPosition().getX() + 1,getPosition().getY()));
        else if(getPosition().getX() == hero_position.getX() && getPosition().getY() > hero_position.getY())
            return (new Position(getPosition().getX(),getPosition().getY() - 1));
        else
            return (new Position(getPosition().getX(),getPosition().getY() + 1));
    }
}