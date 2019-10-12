package com.company.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneticAlgorithmPanel extends JPanel {

    private World world;

    public GeneticAlgorithmPanel() {
        this.setLayout(new BorderLayout());
        this.world = new World();
        this.add(this.world, BorderLayout.CENTER);

        //creating menu bar
        final JMenuBar tableMenuBar = createMenuBar();
        this.add(tableMenuBar, BorderLayout.NORTH);
    }

    public JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(createOptionsMenu());
        return menuBar;
    }

    /**
     * Creating a Menu bar for the Genetic Algorithm simulation
     * @return a JMenu object
     */
    private JMenu createOptionsMenu() {
        final JMenu options = new JMenu("Options");

        // option to reset the game
        final JMenuItem reset = new JMenuItem("Reset World");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resetGame();
            }
        });

        final JMenuItem startGame = new JMenuItem("Start");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        start();
                    }
                });
            }
        });
        options.add(reset);
        options.add(startGame);
        return options;
    }

    /**
     * Starts the run method for a world thread
     */
    private void start() {
        this.world.run();
    }

    private void resetGame() {
        this.world.stop();
        this.remove(world);
        this.world = null;
        this.world = new World();
        this.add(this.world);
        this.revalidate();
    }
}
