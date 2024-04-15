package org.nice.models;

import java.util.List;
import java.util.Optional;

public record PokemonModel(
        int id,
        String name,
        List<String> type,
        Optional<BaseStats> base,
        String species,
        String description,
        Evolution evolution,
        Profile profile,
        Image image
) {


    public record BaseStats(int HP, int Attack, int Defense, int SpAttack, int SpDefense, int Speed) {}

    public record Evolution(List<EvolutionData> next, Optional<EvolutionData> prev) {}

    public record Profile(String height, String weight, Optional<List<String>> egg, List<Ability> ability, String gender) {}

    public record Ability(String name, boolean isHidden){}
    public record Image(String sprite, String thumbnail, Optional<String> hires) {}
    public record EvolutionData(int pokemon, String level){}
}