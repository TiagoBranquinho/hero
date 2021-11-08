import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private TextGraphics graphics;
    private List<Wall> walls;

    public Arena(int width, int height, Hero hero, TextGraphics graphics) {
        this.width = width;
        this.height = height;
        this.hero = hero;
        this.graphics = graphics;
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#0F19A3"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
    }

    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowDown: {
                moveHero(hero.moveDown());
                break;
            }
            case ArrowUp: {
                moveHero(hero.moveUp());
                break;
            }
            case ArrowLeft: {
                moveHero(hero.moveLeft());
                break;
            }
            case ArrowRight: {
                moveHero(hero.moveRight());
                break;
            }
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls){
            if (wall.getPosition().equals(position))
                return false;
        }
        return true;
    }
}