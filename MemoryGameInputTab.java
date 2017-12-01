import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryGameInputTab {
    public JPanel createMemoryGameInputTab() {
        final int messageDisplayTime = 2000;

        JPanel inputPanel = new JPanel();
        JTextField inputTextField = new JTextField(10);
        JButton createButton = new JButton("Generate Game!");

        inputPanel.add(new JLabel("Enter values separated by commas: "), BorderLayout.WEST);
        inputPanel.add(inputTextField);
        inputPanel.add(createButton, BorderLayout.EAST);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextField.getText();
                JButton button = (JButton) e.getSource();
                JTabbedPane tabPane = (JTabbedPane) button.getParent().getParent();

                if (!input.trim().equals("")) { //if input is not empty
                    List<String> values = new ArrayList<>(Arrays.asList(input.split(","))); //ArrayList of values

                    JPanel newGameTab = (new MemoryGameGUITab()).createMemoryGameGUITab((ArrayList<String>) values);
                    tabPane.addTab("Game", newGameTab);
                    if (!tabPane.getComponentAt(1).equals(newGameTab)) { //if game has already been created previously
                        tabPane.removeTabAt(1); //remove previous game
                    }

                    JLabel label = new JLabel("Game successfully generated! Click the \"Game\" tab to play.");

                    //show disappearing message dialog
                    Timer timer = new Timer(messageDisplayTime, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                SwingUtilities.getWindowAncestor(label).dispose();
                            } catch (NullPointerException e1) { //if dialog is closed prematurely
                            }
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    JOptionPane.showMessageDialog(tabPane, label);
                } else {
                    //show disappearing message dialog
                    JLabel label = new JLabel("Please enter some input.");
                    Timer timer = new Timer(messageDisplayTime, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            SwingUtilities.getWindowAncestor(label).dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    JOptionPane.showMessageDialog(tabPane, label);
                }

            }
        });
        return inputPanel;
    }
}
