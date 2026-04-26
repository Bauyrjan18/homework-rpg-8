package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.PoisonedState;
import java.util.List;

public class TrapFloor extends TowerFloor {
    @Override protected String getFloorName() { return "Hall of Toxic Darts"; }

    // ПЕРЕОПРЕДЕЛЕНИЕ HOOK-метода
    @Override protected void announce() {
        System.out.println("\n/!\\ WARNING: SUSPICIOUS ROOM DETECTED: " + getFloorName() + " /!\\");
    }

    @Override protected void setup(List<Hero> party) {
        System.out.println("  [Setup] The door slams shut. You hear a clicking mechanism...");
    }

    @Override protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("  [Challenge] Darts fly from the walls!");
        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.takeDamage(10);
                hero.setState(new PoisonedState(2)); // Интеграция State!
            }
        }
        return new FloorResult(party.stream().anyMatch(Hero::isAlive), 10, "Survived the trap");
    }

    // ПЕРЕОПРЕДЕЛЕНИЕ HOOK-метода
    @Override protected boolean shouldAwardLoot(FloorResult result) {
        return false; // В ловушках нет лута
    }

    @Override protected void awardLoot(List<Hero> party, FloorResult result) {
        // Никогда не вызовется из-за хука выше
    }
}