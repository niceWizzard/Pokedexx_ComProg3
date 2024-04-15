package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;
import org.nice.lib.listview.DynamicListView;
import org.nice.lib.listview.Item;
import org.nice.models.PokemonType;
import org.nice.services.PokemonService;
import org.nice.lib.roundcorner.RoundedCorners;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class PokemonListUI extends JScrollPane {

    Color typeColor = Color.gray;

    public PokemonListUI() {
        var list = new DynamicListView<>(
                PokemonService.getInstance().filterPokemons(
                        List.of()
                ),
                pokemonModel -> String.valueOf(pokemonModel.id()),
                v -> {
                    RoundedCorners view = new RoundedCorners();
                    view.setRoundBottomLeft(10);
                    view.setRoundBottomRight(10);
                    view.setRoundTopLeft(10);
                    view.setRoundTopRight(10);
                    view.setPreferredSize(new Dimension(380,68));
                    view.setBackground(typeColor);
                    view.setLayout(new MigLayout("align center center","[10%][80%][10%]"));

                    //id
                    RoundedCorners idPanel = new RoundedCorners();
                    idPanel.setSize(new Dimension(50,28));
                    idPanel.setMinimumSize(new Dimension(50,28));
                    idPanel.setMaximumSize(new Dimension(50,28));
                    idPanel.setLayout(new MigLayout("align center center"));
                    idPanel.setBackground(Color.white);
                    idPanel.setRoundBottomLeft(5);
                    idPanel.setRoundTopLeft(5);
                    idPanel.setRoundTopRight(5);
                    idPanel.setRoundBottomRight(5);
                    JLabel idLabel = new JLabel(String.valueOf(v.id())) {{
                        setFont(new Font("Arial", Font.BOLD,20));
                        setForeground(typeColor);
                    }};
                    idPanel.add(idLabel, BorderLayout.CENTER);
                    view.add(idPanel, "grow");

                    //name
                    JPanel Pname = new JPanel();
                    Pname.setPreferredSize(new Dimension(200,60));
                    Pname.setLayout(new MigLayout("align center center"));
                    Pname.setBackground(typeColor);
                    JLabel pokeName = new JLabel(v.name().english()){{
                        setFont(new Font("Arial", Font.BOLD,20));
                        setForeground(Color.white);
                    }};
                    Pname.add(pokeName);
                    view.add(Pname,"grow");

                    return new Item<>(view, Optional.empty());
                },
                new Item<>(new JLabel("Nice"), Optional.empty()),
                new MigLayout("wrap, align center", "grow")
        );
        setViewportView(list);
        getVerticalScrollBar().setUnitIncrement(20);
    }
}
