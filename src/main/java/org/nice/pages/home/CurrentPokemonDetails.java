package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CurrentPokemonDetails extends JPanel {
    public CurrentPokemonDetails() {
        setLayout(new MigLayout("align center center"));
        setBackground(Color.CYAN);
        add(new JLabel("Balbasaur"));
    }
}
