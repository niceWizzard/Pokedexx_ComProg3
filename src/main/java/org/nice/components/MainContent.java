package org.nice.components;

import net.miginfocom.swing.MigLayout;
import org.nice.services.NavService;

import javax.swing.*;

public class MainContent extends JPanel {



    public MainContent() {
        setLayout(new MigLayout("", "grow"));

        add(NavService.getInstance(), "grow");
    }
}
