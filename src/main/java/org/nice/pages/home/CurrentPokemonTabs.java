package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;
import org.nice.Utils;
import org.nice.components.StatBar;
import org.nice.models.PokemonModel;
import org.nice.services.PokemonService;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.Objects;

public class CurrentPokemonTabs extends JPanel{

    JPanel description = new JPanel(new MigLayout("align left top"));
    JPanel evolution = new JPanel(new MigLayout("align center center"));
    JPanel stats = new JPanel(new MigLayout("align center center, wrap 1", "", ""));
    int maxStat = 300;
    
    
    
    ImageIcon currentPokemon = new ImageIcon(Utils.getResource("/images/pokedex/thumbnails/00" + 2 + ".png"));
    JLabel evolutionName = new JLabel();

    Border border = BorderFactory.createLineBorder(Color.BLACK);

    public CurrentPokemonTabs(){
        setLayout(new MigLayout("align center center", "grow", "grow"));
        setBackground(Color.GRAY);

        //test values

        //description
        description.add(new JLabel("Lorem ipsum dolor shit chuchu"));

        //stat bars
        stats.add(new StatBar("HP", 100, maxStat, new Color(0xFFDF6D)));
        stats.add(new StatBar("ATK", 100, maxStat, new Color(0xE46666)));
        stats.add(new StatBar("DEF", 100, maxStat, new Color(0x7480ED)));
        stats.add(new StatBar("SP. ATK", 100, maxStat, new Color(0xF2A6A6)));
        stats.add(new StatBar("SP. DEF", 100, maxStat, new Color(0x7DA6CC)));
        stats.add(new StatBar("SPD", 100, maxStat, new Color(0x796CC9)));

        //evolution
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
        //setBorder(border);
    }
}
