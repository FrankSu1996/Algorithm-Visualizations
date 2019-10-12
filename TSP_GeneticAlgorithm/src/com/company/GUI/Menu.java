package com.company.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Menu extends JPanel {
    private JFrame frame;
    private int numCities;

    public Menu() {

        //setup Menu JPanel
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.CYAN);
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Genetic Algorithm Visualization");
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title, BorderLayout.PAGE_START);

        //add DNA icon to JPanel
        try {
            BufferedImage dna = read(new File("images/dna.png"));
            JLabel pic = new JLabel(new ImageIcon(dna));
            pic.setLayout(new BorderLayout());
            pic.setVerticalAlignment(JLabel.NORTH);
            this.add(pic, BorderLayout.CENTER);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        JButton start = new JButton("START!");
        this.add(start, BorderLayout.PAGE_END);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startSimulation();
            }
        });
        //setup JFrame
        this.frame = new JFrame();
        frame.add(this);
        frame.setTitle("Genetic Algorithm");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startSimulation() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setContentPane(new GeneticAlgorithmPanel());
                frame.pack();
                frame.revalidate();
            }
        });
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
    }
}
