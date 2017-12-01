import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGameGUITab extends JPanel {
    private List<String> values;
    private List<String> duplicateValues;
    private JPanel gamePanel;
    private Card compareCard = null;
    private boolean cardFlipped = false;
    private boolean timerDone = true;
    private final int cardDelay = 500;

    /**
     * Creates and returns a "Game" tab
     * @param strings the values that will be on the cards
     */
    public JPanel createMemoryGameGUITab(ArrayList<String> strings) {
        values = strings;
        duplicateValues = new ArrayList<>();

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 4));

        //duplicate each element in values
        int ogSize = values.size();
        for (int i = 0; i < ogSize; i++) {
            duplicateValues.add(values.get(i));
            duplicateValues.add(values.get(i));
        }

        Collections.shuffle(duplicateValues); //randomize order of cards

        //add a card to values for each element in values
        for (int i = 0; i < duplicateValues.size(); i++) {
            Card card = new Card(duplicateValues.get(i));
            card.addActionListener(new FlipListener());
            gamePanel.add(card);
        }

        return gamePanel;
    }

    private class FlipListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!timerDone) { //if user is trying to click before cards have been flipped back/cleared
                return;
            }
            Card clickedCard = (Card) e.getSource();
            if (cardFlipped && clickedCard.equals(compareCard)) { //if user clicks same card, do nothing
                return;
            }
            clickedCard.flip();
            if (!cardFlipped) { //if no card yet flipped
                compareCard = clickedCard;
                cardFlipped = true;
            } else { //if card has been flipped
                if (clickedCard.getValue().equals(compareCard.getValue())) { //if cards match
                    Timer timer = new Timer(cardDelay, new ActionListener() { //momentarily show cards before deleting
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            //delete both cards and replace with empty JPanels
                            int cardIndex = gamePanel.getComponentZOrder(clickedCard);
                            int compareCardIndex = gamePanel.getComponentZOrder(compareCard);
                            gamePanel.remove(clickedCard);
                            gamePanel.add(new JPanel(), cardIndex); //empty JPanel to fill space in GridLayout
                            gamePanel.remove(compareCard);
                            gamePanel.add(new JPanel(), compareCardIndex); //empty JPanel to fill space in GridLayout
                            gamePanel.repaint();

                            //removes values twice because it exists as a pair in values
                            duplicateValues.remove(clickedCard.getValue());
                            duplicateValues.remove(compareCard.getValue());
                            if (duplicateValues.isEmpty()) { //if game is over
                                System.out.println("game over1");
                                gamePanel.removeAll();
                                gamePanel.setLayout(new FlowLayout());
                                gamePanel.add(new JLabel("Game over!"));
                                gamePanel.revalidate();
                            }
                            timerDone = true;
                        }
                    });
                    timer.setRepeats(false);
                    timerDone = false;
                    timer.start();

                } else { //if cards don't match
                    Timer timer = new Timer(cardDelay, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            clickedCard.flip();
                            compareCard.flip();
                            timerDone = true;
                        }
                    });
                    timer.setRepeats(false);
                    timerDone = false;
                    timer.start();
                }
                cardFlipped = false;
                //compareCard = null;
            }
        }
    }
}
