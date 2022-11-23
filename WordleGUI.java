import java.awt.*;
import javax.swing.JComponent;
import java.io.File;
import javax.imageio.ImageIO;

public class WordleGUI extends JComponent
{
    Image[] keyImages;
    Keyboard keyboard;
    char[][] letters;
    int KEY_WIDTH;
    int KEY_HIGHT;
    public WordleGUI() throws Exception
    {
        KEY_WIDTH = 46;
        KEY_HIGHT = 69;
        
        keyboard = new Keyboard();
        letters = keyboard.getLetters();
        
        setKeys();
    }
    
    private void setKeys() throws Exception {
        keyImages = new Image[26];
        for(int i = 0; i < 26 ; i = i + 1) {
            String path = "keys/" + keyboard.getLetterFromNumber(i) + ".png";
            keyImages[i] = ImageIO.read(new File(path)).getScaledInstance(KEY_WIDTH, KEY_HIGHT, ABORT);
        }
    }
    
    private void drawKey(Graphics g, int row, int col, int rowLength) {
        char letter = letters[row][col];
        int index = keyboard.getNumberInAlphabet(letter);
        int x = 590+(KEY_WIDTH+4)*col+(15-rowLength)*row*5;
        int y = 640+(KEY_HIGHT+20)*row;
        Image image = keyImages[index];
        
        g.drawImage(image,x, y, this);
    }
    
    @Override
    public void paint(Graphics g) {
        drawKeys(g);
    }
    
    private void drawKeys(Graphics g) {
        for (int row = 0; row < letters.length; row = 1+row) {
            int rowLength = letters[row].length;
            for (int col = 0; col < rowLength; col = 1+col) {
                drawKey(g, row, col, rowLength);
            }
        }
    }

}
