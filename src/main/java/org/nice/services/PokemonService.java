package org.nice.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nice.models.PokemonModel;
import org.nice.models.PokemonType;
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

    public List<PokemonModel> getPokemonList() {
        return pokemonList;
    }

    public List<PokemonModel> filterPokemons(List<PokemonType> filter, Optional<String> search) {
       return pokemonList
               .stream().filter(pokemon -> {
                   var pattern = Pattern.compile("^"+search.orElseGet(() -> "").toLowerCase() + "\\w+");
                   var appearedOnSearch = search
                           .map(s -> pattern.matcher(pokemon.name().english().toLowerCase()).matches() )
                           .orElse(true);
                   return appearedOnSearch && filter.stream().allMatch(filterType -> pokemon.type().contains(filterType.name()));
               })
               .toList();
    }

    public List<PokemonModel> filterPokemons(List<PokemonType> filter) {
        return filterPokemons(filter, Optional.empty());
    }

    private PokemonService() {
        loadFromFile();
    }

    private void loadFromFile() {
        var path = getClass().getResource("/pokedex.json").getPath();
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        var file = new File(path);

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


    private static List<PokemonModel> parseJsonArray(JSONArray jsonArray) {
        List<PokemonModel> pokemonList = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObj = (JSONObject) obj;
            PokemonModel pokemon = parseJsonObject(jsonObj);
            pokemonList.add(pokemon);
        }
        return pokemonList;
    }

    private static PokemonModel parseJsonObject(JSONObject jsonObj) {
        Optional<List<List<String>>> nextEvol = Optional.empty();
        Optional<List<String>> prevEvol = Optional.empty();
        var evol = jsonObj.getJSONObject("evolution");
        if(evol.has("prev")) {
            prevEvol = Optional.of(
                     evol.getJSONArray("prev").toList().stream().map(Object::toString).toList()
            );
        }
        if(evol.has("next")) {
            nextEvol = Optional.of(
                    jsonArrayToListOfLists(evol.getJSONArray("next"))
            );
        }

        return new PokemonModel(
                jsonObj.getInt("id"),
                new PokemonModel.Name(
                        jsonObj.getJSONObject("name").getString("english"),
                        jsonObj.getJSONObject("name").getString("japanese"),
                        jsonObj.getJSONObject("name").getString("chinese"),
                        jsonObj.getJSONObject("name").getString("french")
                ),
                jsonArrayToList(jsonObj.getJSONArray("type")),
                parseBaseStats(jsonObj),
                jsonObj.getString("species"),
                jsonObj.getString("description"),
                new PokemonModel.Evolution(
                        nextEvol,
                        prevEvol
                ),
                parsePokemonProfile(jsonObj),
                parsePokemonImage(jsonObj)
        );
    }

    private static PokemonModel.Image parsePokemonImage(JSONObject jsonObj) {
        Optional<String> hires = Optional.empty();
        var toReplace = "https://raw.githubusercontent.com/Purukitto/pokemon-data.json/master";
        if(jsonObj.has("hires")) {
            hires = Optional.of(jsonObj.getJSONObject("image").getString("hires").replace(toReplace, ""));
        }
        return new PokemonModel.Image(
                jsonObj.getJSONObject("image").getString("sprite").replace(toReplace, ""),
                jsonObj.getJSONObject("image").getString("thumbnail").replace(toReplace, ""),
                hires
        );
    }

    private static PokemonModel.Profile parsePokemonProfile(JSONObject jsonObj) {
        Optional<List<String>> egg = Optional.empty();
        if(jsonObj.has("egg")) {
            egg = Optional.of(jsonArrayToList(jsonObj.getJSONObject("profile").getJSONArray("egg")));
        }
        return new PokemonModel.Profile(
                jsonObj.getJSONObject("profile").getString("height"),
                jsonObj.getJSONObject("profile").getString("weight"),
                egg,
                jsonArrayToListOfLists(jsonObj.getJSONObject("profile").getJSONArray("ability")),
                jsonObj.getJSONObject("profile").getString("gender")
        );
    }

    private static Optional<PokemonModel.BaseStats> parseBaseStats(JSONObject jsonObj) {
        if(!jsonObj.has("base")) {
            return Optional.empty();
        }
        JSONObject base = jsonObj.getJSONObject("base");
        return Optional.of(new PokemonModel.BaseStats(
                base.getInt("HP"),
                base.getInt("Attack"),
                base.getInt("Defense"),
                base.getInt("Sp. Attack"),
                base.getInt("Sp. Defense"),
                base.getInt("Speed")
        ));
    }

    private static List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (Object obj : jsonArray) {
            list.add((String) obj);
        }
        return list;
    }

    private static List<List<String>> jsonArrayToListOfLists(JSONArray jsonArray) {
        List<List<String>> listOfLists = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONArray innerArray = null;
            innerArray = (JSONArray) obj;
            List<String> innerList = new ArrayList<>();
            for (Object innerObj : innerArray) {
                innerList.add((String)innerObj);
            }
            listOfLists.add(innerList);
        }
        return listOfLists;
    }

}
