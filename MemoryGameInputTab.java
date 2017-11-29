import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoryGameInputTab {
    public JPanel createMemoryGameInputTab() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Values: "), BorderLayout.WEST);
        inputPanel.add(new JTextField());
        JButton createButton = new JButton("Create Game!");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}
