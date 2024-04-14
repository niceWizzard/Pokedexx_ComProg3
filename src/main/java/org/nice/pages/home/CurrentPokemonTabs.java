package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;
import org.nice.Utils;
import org.nice.components.StatBar;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.Objects;

public class CurrentPokemonTabs extends JPanel{

    JPanel description = new JPanel(new MigLayout("align left top"));
    JPanel evolution = new JPanel(new MigLayout("align center top"));
    JPanel stats = new JPanel(new MigLayout("align center center"));
    
    ImageIcon currentPokemon = new ImageIcon(Utils.getResource("/images/pokedex/hires/002.png"));
    JLabel evolutionName = new JLabel();

    Border border = BorderFactory.createLineBorder(Color.BLACK);

    public CurrentPokemonTabs(){
        setLayout(new MigLayout("align center center", "grow", "grow"));
        setBackground(Color.GRAY);

        //test values
        description.add(new JLabel("Lorem ipsum dolor shit chuchu"));
        stats.add(new JLabel("Lorem ipsum dolor wu"));
        stats.add(new StatBar("Strength", 20, 100, Color.RED));

        evolutionName.setText("Ivysaur");
        evolutionName.setIcon(currentPokemon);
        evolutionName.setHorizontalTextPosition(JLabel.CENTER);
        evolutionName.setVerticalTextPosition(JLabel.BOTTOM);
        evolution.add(evolutionName);

        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tab.setBackground(Color.GRAY);
        tab.add("Description", description);
        tab.add("Evolution", evolution);
        tab.add("Stats", stats);
        tab.setForeground(Color.WHITE);

        add(tab, "span, grow");
        setBorder(border);
    }
}
