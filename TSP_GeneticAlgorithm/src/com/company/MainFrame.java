package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private World world;

    public MainFrame() {
        this.setTitle("Genetic Algorithm");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.world = new World();
        this.add(this.world, BorderLayout.CENTER);
        this.pack();

        //creating menu bar
        final JMenuBar tableMenuBar = createMenuBar();
        this.setJMenuBar(tableMenuBar);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
        this.getContentPane().removeAll();
        this.world = null;
        this.world = new World();
        this.getContentPane().add(this.world);
        this.revalidate();
    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame();
    }
}
