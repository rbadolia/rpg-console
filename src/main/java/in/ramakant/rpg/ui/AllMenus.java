package in.ramakant.rpg.ui;

import in.ramakant.rpg.ui.menu.*;

public class AllMenus {
    private final MainMenu mainMenu;
    private final PlayerConfigurationMenu playerConfigurationMenu;
    private final WorldConfigurationMenu worldConfigurationMenu;
    private final ExplorationMenu explorationMenu;
    private final BeforeFightMenu beforeFightMenu;
    private final FightMenu fightMenu;

    public AllMenus(MainMenu mainMenu, PlayerConfigurationMenu playerConfigurationMenu, WorldConfigurationMenu worldConfigurationMenu,
                    ExplorationMenu explorationMenu, BeforeFightMenu beforeFightMenu, FightMenu fightMenu) {
        this.mainMenu = mainMenu;
        this.playerConfigurationMenu = playerConfigurationMenu;
        this.worldConfigurationMenu = worldConfigurationMenu;
        this.explorationMenu = explorationMenu;
        this.beforeFightMenu = beforeFightMenu;
        this.fightMenu = fightMenu;
    }

    public MainMenu mainMenu() {
        return mainMenu;
    }

    public PlayerConfigurationMenu playerConfigMenu() {
        return playerConfigurationMenu;
    }

    public WorldConfigurationMenu worldConfigMenu() {
        return worldConfigurationMenu;
    }

    public ExplorationMenu explorationMenu() {
        return explorationMenu;
    }

    public BeforeFightMenu beforeFightMenu() {
        return beforeFightMenu;
    }

    public FightMenu fightMenu() {
        return fightMenu;
    }
}
