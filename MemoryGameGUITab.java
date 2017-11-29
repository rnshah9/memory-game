import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGameGUITab extends JPanel {
    private List values;
    private JPanel gamePanel;
    private Card compareCard = null;
    private boolean cardFlipped = false;
    private boolean timerDone = true;
    private final int cardDelay = 500;

    public JPanel createMemoryGameGUITab(ArrayList<String> values) {
        this.values = values;

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4, 4));

        //duplicate each element in values
        int ogSize = values.size();
        for (int i = 0; i < ogSize; i++) {
            values.add(values.get(i));
        }

        Collections.shuffle(values); //randomize order of cards

        //add a card to values for each element in values
        for (int i = 0; i < values.size(); i++) {
            Card card = new Card(values.get(i));
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
                            values.remove(clickedCard.getValue());
                            values.remove(compareCard.getValue());
                            if (values.isEmpty()) { //if game is over
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