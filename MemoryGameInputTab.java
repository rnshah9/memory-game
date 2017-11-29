import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MemoryGameInputTab {
    public JPanel createMemoryGameInputTab() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Values: "), BorderLayout.WEST);
        inputPanel.add(new JTextField(5));
        JButton createButton = new JButton("Create Game!");
        inputPanel.add(createButton, BorderLayout.WEST);
        List<String> l = new ArrayList<String>();
        l.add("one");
        l.add("two");
        l.add("three");
        l.add("four");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                JTabbedPane tabPane = (JTabbedPane) button.getParent().getParent();

                tabPane.addTab("Game", (new MemoryGameGUITab()).createMemoryGameGUITab((ArrayList<String>) l));
                tabPane.removeTabAt(1);
            }
        });
        return inputPanel;
    }
}
