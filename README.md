# Tutorial for Custom Components

## DynamicListView

_Item_ - wrapper for JPanel and custom add options.
Example

```java
var view = new JPanel();
var addOptions = Optional.empty();
// Kung gusto mo lagyan ng add options kung malalgay sa container
var addOptions = Optional.of("align center");
var item = new Item(view, addOptions);
```

```java
class Pokemon() {
    public String id;
    public String name;
}
var list = new DynamicListView<Pokemon>(
    // 1st argument, list ng pokemons
    new ArrayList(new Pokemon(), new Pokemon()),
    // 2nd argument, id builder,
    (pokemon) -> pokemon.id // Shortcut: Pokemon::id
    // 3rd argument, view builder
    (pokemon) -> {
        var view = new JPanel(new MigLayout());
        view.add(new JLabel(pokemon.name));
        return new Item(view, Optional.of("align center"))
    },
    // 4th argument, child item if list is empty,
    new Item(new JLabel("No pokemons", Optional.empty())),
    // 5th argument, OPTIONAL, LayoutManager of root
    new MigLayout("align center, wrap", "grow")
    // <-- eto magiging layout manager ng parent ng mga nabuild na child items sa view builder
)

```

**Important**
_DynamicListView_ - by default does not have scroll functionality. Add it as a child of a JScrollPane

```java
var list = new DynamicListView()///  <- example
var scrollPane = new JScrollPane(list);
container.add(scrollPane);
```
