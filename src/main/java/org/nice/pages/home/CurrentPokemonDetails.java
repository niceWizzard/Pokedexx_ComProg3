package org.nice.pages.home;

import net.miginfocom.swing.MigLayout;
import rx.Observable;

import org.nice.lib.roundcorner.RoundedCorners;
import org.nice.models.PokemonModel;
import org.nice.models.PokemonTypeColor;
import org.nice.services.PokemonService;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import java.util.List;

import org.nice.Utils;

// TODO: add type

public class CurrentPokemonDetails extends JPanel {

    PokemonService pokemonService = PokemonService.getInstance();
    Observable<PokemonModel> currentPokemonModel = pokemonService.onCurrentPokemon();
    String Name, Height, Weight, Species, Gender;
    int ID;
    List<String> Type;

    public CurrentPokemonDetails() {

        Optional<PokemonModel> dummyPokemon = pokemonService.getPokemon(10);
        if(dummyPokemon.isPresent()){
            PokemonModel fooModel = dummyPokemon.get();

            Name = fooModel.name();
            Species = fooModel.species();
            ID = fooModel.id();

            Gender = fooModel.profile().gender();
            Height = fooModel.profile().height();
            Weight = fooModel.profile().weight();
            Type = fooModel.type();

        }else{System.out.println("NO POKEMON");}


        String zeros = "";
        if(ID < 10){
            zeros = "00";
        }
        else if (ID < 100){
            zeros = "0";
        }

        JPanel PokeImage = new JPanel();
        JPanel PokeBasic = new JPanel();
        RoundedCorners PokeStat = new RoundedCorners();

        JLabel image = new JLabel();
        var imageIcon = new ImageIcon(Utils.getResource("/images/pokedex/hires/"+ zeros +ID +".png"));
        var screenDimension  = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenDimension.height <= 800) {
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
        }
        image.setIcon(imageIcon);
        PokeImage.setPreferredSize(new Dimension(400,400));
        PokeImage.setBackground(new Color(0xE9FFFB));
        PokeImage.setBorder(BorderFactory.createLineBorder(new Color(0xD9D9D9), 15));
        PokeImage.setLayout(new MigLayout("align center center"));
        PokeImage.add((image));

        //Basic info
//        PokeBasic.setPreferredSize(new Dimension(280,280));
        PokeBasic.setBackground(new Color(0xF6F6F6));
        PokeBasic.setLayout(new MigLayout("","[100%]","[100%]"));
    
            PokeBasic.add(new JLabel("No. " + zeros + ID){{setFont(new Font("Arial",Font.BOLD, 16));}}, "wrap");
            PokeBasic.add(new JLabel(Name){{setFont(new Font("Arial",Font.BOLD, 16));}}, "wrap");
            PokeBasic.add(new JLabel(Species){{
                    setFont(new Font("Arial",Font.PLAIN, 16));
                    setForeground(new Color(0xC4C4C4));
                }}, "wrap");


        PokeStat.setPreferredSize(new Dimension(280,280));
        PokeStat.setBackground(new Color(0xFFF3C7));
        PokeStat.setLayout(new MigLayout("","[100%]","[100%]"));
        PokeStat.setAllRound(20);
        PokeStat.add(new JLabel("Height: " + Height){{setFont(new Font("Arial",Font.PLAIN, 14));}}, "wrap");
        PokeStat.add(new JLabel("Weight: " + Weight){{setFont(new Font("Arial",Font.PLAIN, 14));}}, "wrap");
        PokeStat.add(new JLabel("Gender: "+ Gender){{setFont(new Font("Arial",Font.PLAIN, 14));}}, "wrap");

        //type
        JPanel typePanel = new JPanel();
        typePanel.setPreferredSize(new Dimension(100,30));
        typePanel.setBackground(new Color(0xF6F6F6));
        typePanel.setLayout(new MigLayout("align left center"));
        PokeBasic.add(typePanel,"grow");
        RoundedCorners type1 = new RoundedCorners();
        RoundedCorners type2 = new RoundedCorners();
        type1.setPreferredSize(new Dimension(35,18));
        type2.setPreferredSize(new Dimension(35,18));
        type1.setAllRound(20);
        type2.setAllRound(20);
        type1.setLayout(new MigLayout("align center center"));
        type2.setLayout(new MigLayout("align center center"));

        Color typeColor = PokemonTypeColor.getColor(Type.get(0));

        type1.setBackground(typeColor);
        type2.setBackground(new Color(0xf6f6f6)); // if one type (default)

        type1.add(new JLabel(Type.get(0)){{
            setFont(new Font("Arial", Font.BOLD,14));
            setForeground(Color.white);
        }});
        if(Type.size()==2){
            type2.add(new JLabel(Type.get(1)){{
                setFont(new Font("Arial", Font.BOLD,14));
                setForeground(Color.white);
            }});
            typeColor = PokemonTypeColor.getColor(Type.get(1));
            type2.setBackground(typeColor);
        }
        typePanel.add(type1,"grow ");
        typePanel.add(type2,"grow");

        setLayout(new MigLayout("", "[50%][50%]", "[50%][50%]"));
        add(PokeBasic,"cell 1 0, grow");
        add(PokeStat,"cell 1 1, grow");
        add(PokeImage,"cell 0 0, span 1 2,  shrink");
        setPreferredSize(new Dimension(666,586));
        setBackground(new Color(0xF6F6F6)); // 0xF6F6F6
    }
}
