package org.nice.components;

import com.formdev.flatlaf.ui.FlatLineBorder;
import net.miginfocom.swing.MigLayout;
import org.nice.services.NavService;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    public Header() {
        setLayout(new MigLayout("", "grow"));
        setBorder(
                new FlatLineBorder(
                        new Insets(0,0,1,0),
                        UIManager.getColor("Component.borderColor"))
        );
        init();

    }

    private void init() {
        add(new JLabel("Pokedexx"), "align left");
        var linkContainer = new JPanel(new MigLayout("align right center, gapx 12"));
        add(linkContainer, "grow");
        var homeLink = new Link("Home", NavService.NAV_HOME);
        var aboutLink = new Link("About", NavService.NAV_ABOUT);
        linkContainer.add(homeLink);
        linkContainer.add(aboutLink);
    }
}
