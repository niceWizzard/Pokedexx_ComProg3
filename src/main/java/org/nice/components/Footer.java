package org.nice.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class Footer extends JPanel {
    public Footer() {
        setLayout(new MigLayout("align center center"));
        add(new JLabel("This is the footer"));
    }
}
