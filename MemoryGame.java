import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryGame {
    public static void main(String[] args) {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Input", (new MemoryGameInputTab()).createMemoryGameInputTab());

        JFrame window = new JFrame("Memory Game");
        window.setContentPane(tabbedPane);
        window.setSize(500, 500);
        window.setVisible(true);
    }
}
