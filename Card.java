import javax.swing.*;
import java.awt.*;

public class Card extends JButton {
    private String value;
    private boolean isFlipped = false;

    /**
     * "Flips" card i.e. shows the value or hides it depending on initial state
     */
    public void flip() {
        if (isFlipped) {
            setBackground(Color.GRAY);
            setText("");
            setEnabled(true);
            isFlipped = false;
        } else { //if unFlipped
            setText(value);
            setBackground(Color.WHITE);
            setEnabled(false);
            isFlipped = true;
        }
    }

    public String getValue() {
        return value;
    }

    public Card(String text) {
        super(text);
        value = getText();
        setText("");
        setFont(new Font("Arial", Font.PLAIN, 36));
        setBackground(Color.GRAY);

    }
}
