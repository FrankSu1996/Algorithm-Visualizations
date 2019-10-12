package com.company.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class GeneticAlgorithm {
    private JFrame frame;
    private JPanel mainMenu;
    private int numCities;
    private World world;

    public GeneticAlgorithm() {
        this.mainMenu = createMenu();
        this.frame = new JFrame();
        frame.add(mainMenu);
        frame.setTitle("Genetic Algorithm");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private JPanel createMenu() {
        JPanel menu = new JPanel();
        //setup Menu JPanel
        menu.setPreferredSize(new Dimension(600, 600));
        menu.setBackground(Color.CYAN);
        menu.setLayout(new BorderLayout());
        JLabel title = new JLabel("Genetic Algorithm");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        menu.add(title, BorderLayout.PAGE_START);

        //Left Panel is for configuring the world
        JPanel middle = new JPanel(new BorderLayout());
        middle.setBackground(Color.CYAN);
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(300, 300));
        Border leftInnerBorder = BorderFactory.createTitledBorder("Configure World");
        Border leftOuterBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(leftOuterBorder, leftInnerBorder));
        leftPanel.setBackground(Color.LIGHT_GRAY);
        middle.add(leftPanel, BorderLayout.WEST);

        //Configure layout for left panel
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        JLabel numCities = new JLabel("Number of Cities: ");
        leftPanel.add(numCities, c);
        c.gridx = 1;
        c.gridy = 0;
        JTextField numCitiesField = new JTextField(5);
        leftPanel.add(numCitiesField);

        //Right panel is for description
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(300, 300));
        Border rightInnerBorder = BorderFactory.createTitledBorder("Description");
        Border rightOuterBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        rightPanel.setBorder(BorderFactory.createCompoundBorder(rightInnerBorder, rightOuterBorder));
        middle.add(rightPanel, BorderLayout.CENTER);

        //add DNA icon to JPanel
        try {
            BufferedImage dna = read(new File("images/dna.png"));
            JLabel pic = new JLabel(new ImageIcon(dna));
            pic.setLayout(new BorderLayout());
            pic.setVerticalAlignment(JLabel.NORTH);

            middle.add(pic, BorderLayout.NORTH);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        JButton start = new JButton("START!");
        menu.add(start, BorderLayout.PAGE_END);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startSimulation();
            }
        });
        menu.add(middle, BorderLayout.CENTER);
        return menu;
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

        //option to start the simulation
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

        //option to go back to menu
        final JMenuItem goBack = new JMenuItem("Back to menu");
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        backToMenu();
                    }
                });
            }
        });
        options.add(reset);
        options.add(startGame);
        options.add(goBack);
        return options;
    }

    private void startSimulation() {
        this.world = new World();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(this.world, BorderLayout.CENTER);
        panel.add(createMenuBar(), BorderLayout.NORTH);
        frame.setContentPane(panel);
        frame.pack();
        frame.revalidate();
    }

    private void start() {
        this.world.run();
    }

    private void resetGame() {
        this.world.stop();
        frame.remove(world);
        this.world = null;
        this.world = new World();
        frame.add(this.world);
        frame.revalidate();
    }

    private void backToMenu() {
        this.world = null;
        this.frame.getContentPane().removeAll();
        this.frame.setContentPane(createMenu());
        frame.pack();
        frame.revalidate();
    }

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
    }
}
