import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;
    private final Hero hero;
    private final TextGraphics graphics;
    private final List<Wall> walls;
    private List<Coin> coins;
    private final List<Monster> monsters;
    private final Screen screen;

    public Arena(int width, int height, Screen screen) {
        this.width = width;
        this.height = height;
        hero = new Hero(10,10);
        this.screen = screen;
        graphics = screen.newTextGraphics();
        walls = createWalls();
        int coinsNumber = 5;
        coins = createCoins(coinsNumber);
        int monstersNumber = 2;
        monsters = createMonsters(monstersNumber);
    }

    private List<Monster> createMonsters(int monstersNumber) {
        List<Monster> monsters = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < monstersNumber; i++) {
            monsters.add(new Monster(random.nextInt(width - 2) + 1,random.nextInt(height - 2) + 1));
        }
        return monsters;
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

    private List<Coin> createCoins(int coinsNumber) {
        int coin_x,coin_y;
        Random random = new Random();
        List<Coin> coins = new ArrayList<>();
        for (int i = 0; i < coinsNumber; i++) {
            do{
                coin_x = random.nextInt(width - 2) + 1;
                coin_y = random.nextInt(height - 2) + 1;
            } while(new Position(coin_x,coin_y).equals(hero.getPosition()));
            coins.add(new Coin(coin_x,coin_y));
        }
        for(int i = 0; i < coinsNumber - 1; i++){
            for(int j = i+1; j < coinsNumber; j++){
                if(coins.get(i).getPosition().equals(coins.get(j).getPosition())){
                    createCoins(coinsNumber);
                }
            }
        }
        return coins;
    }

    public void draw(TextGraphics graphics) {
        final String color = "#6F7080";
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);             // draw the Hero
        for(Coin coin : coins)
            coin.draw(graphics);         // draw the Coins
        for (Wall wall : walls)
            wall.draw(graphics);         // draw the Walls
        for (Monster monster : monsters)
            monster.draw(graphics);      // draw the Monsters
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
        moveMonsters();
    }

    private void moveMonsters() throws IOException {
        if(!verifyMonsterCollisions()){                // if no Monster collided with the Hero
            for(Monster monster : monsters)
                monster.setPosition(monster.move(hero.getPosition()));
        }
        if(verifyMonsterCollisions()){
            while(true) {
                graphics.putCSIStyledString(1,height / 2 , "You Lost!'q' to exit or 'r' to restart");
                screen.refresh();
                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                    break;
                }
                if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'r'){
                    screen.close();
                    Game game = new Game();
                    game.run();
                    break;
                }
            }
            screen.close();
        }
    }

    private boolean verifyMonsterCollisions() {
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                return true;
            }
        }
        return false;
    }

    public void moveHero(Position position) throws IOException {
        if (canHeroMove(position)) {
            retrieveCoins(position);
            hero.setPosition(position);
        }
    }

    private List<Coin> retrieveCoins(Position position) throws IOException {
        for(Coin coin : coins){
            if(coin.getPosition().equals(position)) {
                coins.remove(coin);
                if(coins.size() == 0){
                    while(true) {
                        screen.refresh();
                        graphics.putCSIStyledString(1, height/ 2, "You Won! Press 'q' to exit");
                        screen.refresh();
                        KeyStroke key = screen.readInput();
                        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                            screen.close();
                            break;
                        }
                    }
                }
                return coins;
            }
        }
        return coins;
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls){
            if (wall.getPosition().equals(position))
                return false;
        }
        return true;
    }
}