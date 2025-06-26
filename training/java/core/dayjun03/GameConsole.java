package dayjun03;


import java.util.*;
import java.util.function.Predicate;

interface Player {
    String name();
}

record GameAction(char key, String prompt, Predicate<Integer> action) {}

abstract class Game<T extends Player> {
    private final String gameName;
    protected final List<T> players = new ArrayList<>();
    protected final Map<Character, GameAction> standardActions = new HashMap<>();

    protected Game(String gameName) {
        this.gameName = gameName;
        standardActions.put('P', new GameAction('P', "Print Player", this::printPlayer));
        standardActions.put('Q', new GameAction('Q', "Quit Game", this::quitGame));
    }

    public abstract T createNewPlayer(String name);
    public abstract Map<Character, GameAction> getGameActions(int playerIndex);

    public int addPlayer(String name) {
        T player = createNewPlayer(name);
        players.add(player);
        return players.size() - 1;
    }

    public boolean executeGameAction(int player, GameAction action) {
        return action.action().test(player);
    }

    public boolean printPlayer(int player) {
        System.out.println(players.get(player).name());
        return false;
    }

    public boolean quitGame(int player) {
        return true;
    }
}

enum Weapon {
    KNIFE(5, 1), AXE(10, 2), GUN(15, 3), CANNON(20, 4);

    private final int hitPoints;
    private final int level;

    Weapon(int hitPoints, int level) {
        this.hitPoints = hitPoints;
        this.level = level;
    }

    public int getHitPoints() { return hitPoints; }
    public int getLevel() { return level; }
}

class Pirate implements Player {
    private final String name;
    private final Set<String> townsVisited = new HashSet<>();
    private Weapon currentWeapon;

    public Pirate(String name) {
        this.name = name;
        this.currentWeapon = Weapon.KNIFE;
    }

    public String name() { return name; }
    public Weapon getCurrentWeapon() { return currentWeapon; }
    public void setCurrentWeapon(Weapon weapon) { this.currentWeapon = weapon; }
    public void visitTown(String town) { townsVisited.add(town); }
}

class PirateGame extends Game<Pirate> {
    private static final List<List<String>> levelMap = new ArrayList<>();

    static {
        levelMap.add(List.of("Port Royal", "Tortuga", "Nassau"));
        levelMap.add(List.of("Havana", "Kingston", "Charles Towne"));
        levelMap.add(List.of("Portobelo", "Cartagena", "Maracaibo"));
    }

    public PirateGame() {
        super("Pirate Game");
    }

    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        Map<Character, GameAction> actions = new HashMap<>(standardActions);
        actions.put('F', new GameAction('F', "Find Treasure", p -> false));
        actions.put('W', new GameAction('W', "Change Weapon", p -> false));
        return actions;
    }
}

public class GameConsole {
    private static Scanner scanner = new Scanner(System.in);
    private Game<? extends Player> game;

    public GameConsole(Game<? extends Player> game) {
        this.game = game;
    }

    public int addPlayer() {
        System.out.print("Enter player name: ");
        String name = scanner.nextLine();
        return game.addPlayer(name);
    }

    public void playGame(int playerIndex) {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nAvailable Actions:");
            Map<Character, GameAction> actions = game.getGameActions(playerIndex);
            actions.forEach((k, v) -> System.out.println(k + ": " + v.prompt()));

            System.out.print("Select action: ");
            char input = scanner.nextLine().toUpperCase().charAt(0);
            GameAction action = actions.get(input);
            if (action != null) {
                quit = game.executeGameAction(playerIndex, action);
            }
        }
    }

    public static void main(String[] args) {
        PirateGame pirateGame = new PirateGame();
        GameConsole console = new GameConsole(pirateGame);
        int player = console.addPlayer();
        console.playGame(player);
    }
}
