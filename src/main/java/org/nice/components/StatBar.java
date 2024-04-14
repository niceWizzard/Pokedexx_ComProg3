package org.nice.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class StatBar extends JPanel {
    public StatBar(String statLabel, int statValue, int statMax, Color fillColor) {
        setLayout(new MigLayout("gapx 12", "[][grow]"));
        add(new JLabel(statLabel));

        var pb = new JProgressBar();
        pb.setMaximum(statMax);
        pb.setMinimum(0);
        pb.setValue(statValue);
        pb.setForeground(fillColor);
        add(pb, "grow");
    }
}
