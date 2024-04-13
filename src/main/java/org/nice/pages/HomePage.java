package org.nice.pages;

import net.miginfocom.swing.MigLayout;
import org.nice.Main;
import org.nice.components.PokemonCard;
import org.nice.lib.listview.DynamicListView;
import org.nice.lib.listview.Item;
import org.nice.lib.navigation.Routeable;
import org.nice.models.PokemonModel;
import org.nice.models.PokemonType;
import org.nice.services.PokemonService;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class HomePage extends Routeable {
    public HomePage() {
        setLayout(new MigLayout("", "grow"));
        var list = new DynamicListView<>(
                PokemonService.getInstance().filterPokemons(
                        List.of(PokemonType.Ice)
                ),
                pokemonModel -> String.valueOf(pokemonModel.id()),
                v -> new Item<>( new PokemonCard(v), Optional.empty()),
                new Item<>(new JLabel("Nice"), Optional.empty()),
                new MigLayout("wrap 9, align center", "")

        );
        var scroll = new JScrollPane(list);
        add(scroll, "grow");

    }

    @Override
    public void onNavigationEnter(Object... data) {
        Main.getFrame().setTitle("Pokedexx - Home");
    }

    @Override
    public void onNavigationExit(String newRoute) {

    }
}
