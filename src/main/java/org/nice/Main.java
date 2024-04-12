package org.nice;

import com.formdev.flatlaf.FlatDarkLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        FlatDarkLaf.setup();
        var root = new JPanel(new MigLayout("align center center"));
        root.add(new JLabel("Hello worrd"));

        setTitle("Pokedexx");
        setMinimumSize(new Dimension(1080, 720));
        setLocationRelativeTo(null);
        setContentPane(root);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}