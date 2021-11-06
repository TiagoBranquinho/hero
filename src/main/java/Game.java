import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.ArrowDown;

public class Game {
        private Screen screen;
        private int x = 10;
        private int y = 10;
        public Game() {
        int columns = 40;
        int rows = 20;
        try {
            TerminalSize terminalSize = new TerminalSize(columns, rows);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            this.screen = screen;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.EOF)
                break;
            processKey(key);
        }
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowDown: {
                y++;
                break;
            }
            case ArrowUp: {
                y--;
                break;
            }
            case ArrowLeft: {
                x--;
                break;
            }
            case ArrowRight: {
                x++;
                break;
            }
            case Character:
            {
                if(key.getCharacter() == 'q')
                    screen.close();
                break;
            }
        }
    }
}
