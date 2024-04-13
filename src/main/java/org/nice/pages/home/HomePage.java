package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;
import org.nice.Main;
import org.nice.lib.listview.DynamicListView;
import org.nice.lib.listview.Item;
import org.nice.lib.navigation.Routeable;
import org.nice.models.PokemonType;
import org.nice.services.PokemonService;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class HomePage extends Routeable {
    public HomePage() {
        setLayout(new MigLayout("", "[][grow]", "grow"));

        add(new PokemonListUI(), "width :480:");
        var rightSide = new JPanel(new MigLayout("wrap, gap 0, insets 0", "grow", "[grow]20[]"));
        rightSide.add(new CurrentPokemonDetails(), "grow");
        rightSide.add(new CurrentPokemonTabs(), "grow");
        add(rightSide, "grow");
    }

    @Override
    public void onNavigationEnter(Object... data) {
        Main.getFrame().setTitle("Pokedexx - Home");
    }

    @Override
    public void onNavigationExit(String newRoute) {

    }
}
