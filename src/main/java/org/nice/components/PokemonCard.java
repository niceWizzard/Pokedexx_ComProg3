package org.nice.components;

import com.formdev.flatlaf.ui.FlatRoundBorder;
import net.miginfocom.swing.MigLayout;
import org.nice.models.PokemonModel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PokemonCard extends JPanel {
    public PokemonCard(PokemonModel model){
        setLayout(new MigLayout("wrap", "grow"));
        setBorder(new FlatRoundBorder());
        init(model);
    }

    private void init(PokemonModel model) {

        var image = new ImageIcon(
                Objects.requireNonNull(
                        getClass().getResource(
                                model.image().thumbnail()
                        )
                )
        );
        // Resize image
        image = new ImageIcon(image.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH));
        var imageLabel = new JLabel(image);
        add(imageLabel);

        var name = new JLabel(model.name().english());
        name.setFont(new Font("Arial", Font.BOLD, 16));
        add(name, "align left");
    }
}
