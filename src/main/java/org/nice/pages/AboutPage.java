package org.nice.pages;

import net.miginfocom.swing.MigLayout;
import org.nice.Main;
import org.nice.lib.navigation.Routeable;

import javax.swing.*;

public class AboutPage extends Routeable {
    public AboutPage(){
        setLayout(new MigLayout("align center center"));
        add(new JLabel("About Page"));
    }
    @Override
    public void onNavigationEnter(Object... data) {
        Main.getFrame().setTitle("Pokedexx - About");
    }

    @Override
    public void onNavigationExit(String newRoute) {

    }
}
