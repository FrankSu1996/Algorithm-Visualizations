package com.company.GUI;

import com.company.algorithm.TSPUtils;

import javax.imageio.ImageIO;
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
    private World world;
    private JTextField numCities;
    private JTextField popSize;
    private JTextField mutSeverity;

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
        JLabel numCitiesLabel = new JLabel("Number of Cities: ");
        leftPanel.add(numCitiesLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        this.numCities = new JTextField(5);
        leftPanel.add(this.numCities);

        c.gridy = 1;
        c.gridx = 0;
        JLabel popSizeLabel = new JLabel("Population Size: ");
        leftPanel.add(popSizeLabel, c);

        c.gridy = 1;
        c.gridx = 1;
        this.popSize = new JTextField(5);
        leftPanel.add(this.popSize, c);

        c.gridy = 2;
        c.gridx = 0;
        JLabel mutSeverityLabel = new JLabel("Mutation Severity: ");
        leftPanel.add(mutSeverityLabel, c);

        c.gridy = 2;
        c.gridx = 1;
        mutSeverity = new JTextField(5);
        leftPanel.add(mutSeverity, c);

        //Right panel is for description
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(300, 300));
        Border rightInnerBorder = BorderFactory.createTitledBorder("Description");
        Border rightOuterBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        rightPanel.setBorder(BorderFactory.createCompoundBorder(rightInnerBorder, rightOuterBorder));
        middle.add(rightPanel, BorderLayout.CENTER);

        StringBuilder sb1 = new StringBuilder(64);
        sb1.append("<html>This program will use a Genetic Algorithm to solve the \"Travelling Salesman Problem: \" Given n number of cities,").
                append(" what is the shortest possible route that visits each city and returns to the origin city.</html>");
        JLabel description1 = new JLabel(sb1.toString());
        rightPanel.add(description1, BorderLayout.NORTH);

        StringBuilder sb2 = new StringBuilder(64);
        sb2.append("<html>Population size: the size of the population that is used in the algorithm.</html>");
        JLabel description2 = new JLabel(sb2.toString());
        rightPanel.add(description2, BorderLayout.CENTER);

        StringBuilder sb3 = new StringBuilder(64);
        sb3.append("<html>Mutation Severity: How much of the DNA is mutated for each mutation that occurs. (1-15) optimal </html>");
        JLabel description3 = new JLabel(sb3.toString());
        rightPanel.add(description3, BorderLayout.PAGE_END);

        //add DNA icon to JPanel
        try {
            BufferedImage dna = ImageIO.read(new File("images/dna.png"));
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

    /**
     * Method is invoked when the start button is clicked on main menu. Transitions
     * into world JPanel
     */
    private void startSimulation() {
        String numberCities = this.numCities.getText().strip();
        String popSize = this.popSize.getText().strip();
        String mutSeverity = this.mutSeverity.getText().strip();

        //check if user has entered a valid input for number of cities
        if (numberCities == "" || !isInteger(numberCities)) {
            JOptionPane.showMessageDialog(this.mainMenu, "You must enter a positive integer value for Number of Cities!!");
            return;
        }

        //check if user has entered a valid input for population size
        if (popSize == "" || !isInteger(popSize)) {
            JOptionPane.showMessageDialog(this.mainMenu, "You must enter a positive integer value for Population Size!!");
            return;
        }

        //check if user has entered a valid input for population size and that it is less than number of cities
        if(mutSeverity == "" || !isInteger(mutSeverity) || Integer.parseInt(mutSeverity) >= Integer.parseInt(numberCities) - 1) {
            JOptionPane.showMessageDialog(this.mainMenu, "You must enter a positive integer value for Mutation Severity " +
                    "and it must be smaller than Number of Cities!!");
            return;
        }

        //set parameters in TSPUtils
        TSPUtils.CITIES = TSPUtils.generateData(Integer.parseInt(numberCities));
        TSPUtils.POPULATION_SIZE = Integer.parseInt(popSize);
        TSPUtils.MUTATION_SEVERITY = Integer.parseInt(mutSeverity);

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
        this.world.stop();
        this.world = null;
        this.frame.getContentPane().removeAll();
        this.frame.setContentPane(createMenu());
        frame.pack();
        frame.revalidate();
    }

    /**
     * Helper method to determine if string is an integer
     * @param s a input string
     * @return true if string is an Integer, false otherwise
     */
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
    }
}
