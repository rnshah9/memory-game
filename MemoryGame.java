import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryGame {
    public static void main(String[] args) {
        List<String> l = new ArrayList<String>();
        l.add("one");
        l.add("two");
        l.add("three");
        l.add("four");
        //l.add("five");

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Game", (new MemoryGameGUITab()).createMemoryGameGUITab((ArrayList<String>) l));

        JFrame window = new JFrame("Memory Game");
        window.setContentPane(tabbedPane);
        window.setSize(500, 500);
        window.setVisible(true);
    }
}
