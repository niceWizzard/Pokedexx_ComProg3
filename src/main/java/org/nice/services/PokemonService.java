package org.nice.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nice.Utils;
import org.nice.models.PokemonModel;
import org.nice.models.PokemonType;
import rx.Observable;
import rx.subjects.BehaviorSubject;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class PokemonService {
    private final static PokemonService instance = new PokemonService();
    public static PokemonService getInstance() {
        return instance;
    }

    List<PokemonModel> pokemonList = new ArrayList<>();

    private BehaviorSubject<PokemonModel> currentPokemon;

    public Observable<PokemonModel> onCurrentPokemon() {
        return currentPokemon.asObservable();
    }


    /** Sets the currentPokemon field and fires the change event
     * @param model The pokemon model to be the current
     */
    public void setCurrentPokemon(PokemonModel model) {
        currentPokemon.onNext(model);
    }

    public List<PokemonModel> getPokemonList() {
        return pokemonList;
    }

    public List<PokemonModel> filterPokemons(List<PokemonType> filter, Optional<String> search) {
       return pokemonList
               .stream().filter(pokemon -> {
                   var pattern = Pattern.compile("^"+search.orElseGet(() -> "").toLowerCase() + ".*$");
                   var appearedOnSearch = search
                           .map(s -> pattern.matcher(pokemon.name().toLowerCase()).matches() )
                           .orElse(true);
                   return appearedOnSearch && filter.stream().allMatch(filterType -> pokemon.type().contains(filterType.name()));
               })
               .sorted(Comparator.comparingInt(PokemonModel::id))
               .toList();
    }

    public List<PokemonModel> filterPokemons(List<PokemonType> filter) {
        return filterPokemons(filter, Optional.empty());
    }

    public Optional<PokemonModel> getPokemon(int id) {
        for(var p : pokemonList) {
            if(p.id()== id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    private PokemonService() {
        loadFromFile();
        System.out.println("SIZE " + pokemonList.size());
        currentPokemon = BehaviorSubject.create( pokemonList.get(0));
    }

    private void loadFromFile() {
        var file = new File(Utils.getResource("/pokedex.json"));

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(
                    Files.readString(file.toPath())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pokemonList = parseJsonArray(jsonArray);

    }

    private List<PokemonModel> parseJsonArray(JSONArray jsonArray) {
        var list = new ArrayList<PokemonModel>();

        for(var item : jsonArray) {
            list.add(
                    parseAsPokemon((JSONObject)item)
            )            ;
        }
        return list;
    }

    private PokemonModel parseAsPokemon(JSONObject item) {
        var id = item.getInt("id");
        var name = item.getJSONObject("name").getString("english");
        var type = parseAsPokemonType(item.getJSONArray("type"));
        PokemonModel.BaseStats baseStats = parseAsPokemonStats(item.getJSONObject("base"));
        String species = item.getString("species");
        var description = item.getString("description");
        // Evol
        PokemonModel.Profile profile = parseAsPokemonProfile(item.getJSONObject("profile"));
        PokemonModel.Image image = parseAsImages(item.getJSONObject("image"));
        return null;
    }

    private PokemonModel.Image parseAsImages(JSONObject jsonObj) {
//        Optional<String> hires = Optional.empty();
        var toReplace = "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master";
//        if(jsonObj.has("hires")) {
//            hires = Optional.of(jsonObj.getJSONObject("image").getString("hires").replace(toReplace, ""));
//        }
        return new PokemonModel.Image(
                jsonObj.getString("sprite").replace(toReplace, ""),
                jsonObj.getString("thumbnail").replace(toReplace, ""),
                jsonObj.getString("hires").replace(toReplace,"")
        );
    }

    private PokemonModel.Profile parseAsPokemonProfile(JSONObject profile) {
        Optional<List<String>> egg = Optional.empty();
        if(profile.has("egg")) {
            egg = Optional.of(
                    profile.getJSONArray("egg")
                            .toList()
                            .stream().map(v -> (String) v).toList()
            );
        }
        return new PokemonModel.Profile(
                profile.getString("height"),
                profile.getString("weight"),
                egg,
                profile
                        .getJSONArray("ability").toList().stream()
                        .map(v -> {
                            var abilityJson = (JSONArray) v;
                            return new PokemonModel.Ability(
                                    abilityJson.getString(0),
                                    abilityJson.getBoolean(1)
                            );
                            }
                        ).toList(),
                profile.getString("gender")
        );
    }

    private PokemonModel.BaseStats parseAsPokemonStats(JSONObject base) {
        return new PokemonModel.BaseStats(
                base.getInt("HP"),
                base.getInt("Attack"),
                base.getInt("Defense"),
                base.getInt("Sp. Attack"),
                base.getInt("Sp. Defense"),
                base.getInt("Speed")
        );
    }

    private List<String> parseAsPokemonType(JSONArray type) {
        return List.of(
                type.getString(0),
                type.getString(1)
        );
    }


}
