package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;
import rx.Observable;

import org.nice.lib.roundcorner.RoundedCorners;
import org.nice.models.PokemonModel;
import org.nice.services.PokemonService;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.jar.Attributes.Name;

import org.nice.Utils;

public class CurrentPokemonDetails extends JPanel {

    PokemonService pokemonService = PokemonService.getInstance();
    Observable<PokemonModel> currentPokemonModel = pokemonService.onCurrentPokemon();
    String Name, Height, Weight, Species, Gender;
    int ID;

    public CurrentPokemonDetails() {

        Optional<PokemonModel> dummyPokemon = pokemonService.getPokemon(10);
        if(dummyPokemon.isPresent()){
            PokemonModel fooModel = dummyPokemon.get();

            Name = fooModel.name().english();
            Species = fooModel.species();
            ID = fooModel.id();

            Gender = fooModel.profile().gender();
            Height = fooModel.profile().height();
            Weight = fooModel.profile().weight();

        }else{System.out.println("NO POKEMON");}


        String dito;
        if(ID < 10){
            dito = "00";
        }
        else if (ID < 100){
            dito = "0";
        }
        else{
            dito ="";
        }

        JPanel PokeImage = new JPanel();
        JPanel PokeBasic = new JPanel();
        RoundedCorners PokeStat = new RoundedCorners();

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Utils.getResource("/images/pokedex/hires/"+ dito +ID +".png")));
        PokeImage.setPreferredSize(new Dimension(400,400));
        PokeImage.setBackground(new Color(0xE9FFFB));
        PokeImage.setBorder(BorderFactory.createLineBorder(new Color(0xD9D9D9), 15));
        PokeImage.setLayout(new MigLayout("align center center"));
        PokeImage.add((image));

        //Basic info
        PokeBasic.setPreferredSize(new Dimension(280,280));
        PokeBasic.setBackground(new Color(0xF6F6F6));
        PokeBasic.setLayout(new MigLayout("","[100%]","[100%]"));
    
            PokeBasic.add(new JLabel("No. " + dito + ID){{setFont(new Font("Arial",Font.BOLD, 16));}}, "wrap");
            PokeBasic.add(new JLabel(Name){{setFont(new Font("Arial",Font.BOLD, 16));}}, "wrap");
            PokeBasic.add(new JLabel(Species){{
                    setFont(new Font("Arial",Font.PLAIN, 16));
                    setForeground(new Color(0xC4C4C4));
                }}, "wrap");


        PokeStat.setPreferredSize(new Dimension(280,280));
        PokeStat.setBackground(new Color(0xFFF3C7));
        PokeStat.setLayout(new MigLayout("","[100%]","[100%]"));
            PokeStat.setRoundTopLeft(20);
            PokeStat.setRoundTopRight(20);
            PokeStat.setRoundBottomLeft(20);
            PokeStat.setRoundBottomRight(20);
        PokeStat.add(new JLabel("Height: " + Height){{setFont(new Font("Arial",Font.PLAIN, 14));}}, "wrap");
        PokeStat.add(new JLabel("Weight: " + Weight){{setFont(new Font("Arial",Font.PLAIN, 14));}}, "wrap");
        PokeStat.add(new JLabel("Gender: "+ Gender){{setFont(new Font("Arial",Font.PLAIN, 14));}}, "wrap");

        setLayout(new MigLayout("", "[50%][50%]", "[50%][50%]"));
        add(PokeBasic,"cell 1 0, grow");
        add(PokeStat,"cell 1 1, grow");
        add(PokeImage,"cell 0 0, span 1 2, grow");
        setPreferredSize(new Dimension(666,586));
        setBackground(new Color(0xF6F6F6)); // 0xF6F6F6
    }
}
