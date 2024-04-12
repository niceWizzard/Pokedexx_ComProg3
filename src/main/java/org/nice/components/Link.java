package org.nice.components;

import org.nice.services.NavService;

import javax.swing.*;

public class Link extends JButton {
    public String link = "";
    public Link(String t, String link) {
        super(t);
        this.link = link;
        addActionListener(v -> {
            NavService.getInstance().navigateTo(this.link);
        });
    }
}
