package org.nice;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import org.nice.components.Footer;
import org.nice.components.Header;
import org.nice.components.MainContent;

import java.awt.Dimension;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    private static JFrame frame;

    public static JFrame getFrame() {
        return frame;
    }

    public Main() {
        frame = this;
        FlatIntelliJLaf.setup();
        var root = new JPanel(new MigLayout("", "grow", "grow"));
        init(root);

        setTitle("Pokedexx");
        setMinimumSize(new Dimension(1080, 720));
        setLocationRelativeTo(null);
        setContentPane(root);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void init(JPanel root) {
        root.add(new Header(), "dock north");
        root.add(new MainContent(), "grow");
        root.add(new Footer(), "dock south");
    }
}