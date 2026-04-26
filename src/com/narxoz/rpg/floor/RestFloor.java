package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.NormalState;
import java.util.List;

public class RestFloor extends TowerFloor {
    @Override protected String getFloorName() { return "Fountain of Purity"; }

    @Override protected void setup(List<Hero> party) {
        System.out.println("  [Setup] A glowing fountain sits in the center of the room.");
    }

    @Override protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("  [Challenge] The party rests and drinks the glowing water.");
        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.heal(40);
                System.out.println("    " + hero.getName() + " heals 40 HP.");
                hero.setState(new NormalState());
            }
        }
        return new FloorResult(true, 0, "Party fully rested and cured");
    }

    @Override protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("  [Loot] Collected some pure water in flasks.");
    }
}