import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;


public class Hero{
    public Position position = new Position(0,0);

    public Hero(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position moveUp(){
        return new Position(position.getX(),position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(),position.getY() + 1);
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1,position.getY());
    }

    public Position moveRight(){
        return new Position(position.getX() + 1,position.getY());
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#B52914"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),position.getY()), "X");
    }
    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }
}
