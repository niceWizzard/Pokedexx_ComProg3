package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CurrentPokemonTabs extends JPanel {
    public CurrentPokemonTabs() {
        setLayout(new MigLayout("align center center"));
        setBackground(Color.BLUE);
        add(new JLabel("Tabs"));
    }
}
