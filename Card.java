import javax.swing.*;
import java.awt.*;

public class Card extends JButton {
    private String value;
    private boolean isFlipped = false;

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flip() {
        if (isFlipped) {

            setBackground(Color.GRAY);
            setText("");
            isFlipped = false;
        } else { //unFlipped
            setText(value);
            setBackground(Color.WHITE);
            isFlipped = true;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Card(String text) {
        super(text);
        value = getText();
        setText("");
        setBackground(Color.GRAY);

    }
}
