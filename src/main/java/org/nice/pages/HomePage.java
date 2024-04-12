package org.nice.pages;

import net.miginfocom.swing.MigLayout;
import org.nice.Main;
import org.nice.lib.navigation.Routeable;

import javax.swing.*;

public class HomePage extends Routeable {
    public HomePage() {
        setLayout(new MigLayout("align center center"));
        add(new JLabel("Home Page"));
    }

    @Override
    public void onNavigationEnter(Object... data) {
        Main.getFrame().setTitle("Pokedexx - Home");
    }

    @Override
    public void onNavigationExit(String newRoute) {

    }
}
