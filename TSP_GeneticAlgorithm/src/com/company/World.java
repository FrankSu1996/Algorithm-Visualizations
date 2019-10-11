package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class World extends JPanel {

    private final AtomicInteger generation;
    private final Population population;

    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    private World() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        this.generation = new AtomicInteger(0);
        this.population = new Population(TSPUtils.CITIES, 1000);
        final Timer timer = new Timer(5, (ActionEvent e) -> {
            this.population.update();
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.CYAN);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString("gen : " + this.generation.incrementAndGet(), 350, 15);
        g.drawString("shortest path : " + String.format("%.2f", this.population.getAlpha().calculateDistance()), 500, 15);
        drawBestChromosome(g);
    }

    /**
     * Iterates through chromosome, draws line between neighboring genes (cities)
     * @param g graphics parameter
     */
    private void drawBestChromosome(final Graphics2D g) {
        final java.util.List<Gene> chromosome = this.population.getAlpha().getChromosome();
        g.setColor(Color.WHITE);

        // iterate through chromosome, draw line between each neighbor
        for(int i = 0; i < chromosome.size() - 1; i++) {
            Gene gene = chromosome.get(i);
            Gene neighbor = chromosome.get(i+1);
            g.drawLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }

        // draw each city as red dot
        g.setColor(Color.RED);
        for(final Gene gene : chromosome) {
            g.fillOval(gene.getX(), gene.getY(), 5, 5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("Genetic Algorithm");
            frame.setResizable(false);
            frame.add(new World(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}