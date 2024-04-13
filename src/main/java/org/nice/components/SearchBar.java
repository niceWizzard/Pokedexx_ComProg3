package org.nice.components;

import net.miginfocom.swing.MigLayout;
import org.nice.lib.ComponentBorder;
import org.nice.services.SearchService;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class SearchBar extends JPanel {
    public SearchBar() {
        setLayout(new MigLayout("align left", "grow"));
        var btn = new JButton("\uD83D\uDD0D");
        var field = new JTextField();

        var cb = new ComponentBorder(btn);
        cb.install(field);
        add(field, "grow");
        field.addActionListener(e -> {
            onSubmit(field.getText());
        });
        btn.addActionListener(e -> {
            onSubmit(field.getText());
        });
    }


    void onSubmit(String s){
        if(s.isBlank()) {
            return;
        }
        SearchService.getInstance().setSearchString(s);
    }
}
