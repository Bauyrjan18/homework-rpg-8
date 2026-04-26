package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import java.util.List;

public class CombatFloor extends TowerFloor {
    private final String floorName;
    private Monster monster;
    public CombatFloor(String name, Monster monster) {
        this.floorName = name;
        this.monster = monster;
    }

    @Override protected String getFloorName() { return floorName; }

    @Override protected void setup(List<Hero> party) {
        System.out.println("  [Setup] A wild " + monster.getName() + " blocks the path!");
    }

    @Override protected FloorResult resolveChallenge(List<Hero> party) {
        int damageTakenTotal = 0;

        while (monster.isAlive() && party.stream().anyMatch(Hero::isAlive)) {
            for (Hero hero : party) {
                if (!hero.isAlive() || !monster.isAlive()) continue;

                hero.getState().onTurnStart(hero);

                if (hero.getState().canAct() && monster.isAlive()) {
                    int dmg = hero.getState().modifyOutgoingDamage(hero.getAttackPower());
                    monster.takeDamage(dmg);
                    System.out.println("    " + hero.getName() + " attacks " + monster.getName() + " for " + dmg + " DMG.");
                }

                hero.getState().onTurnEnd(hero);
            }
            if (monster.isAlive()) {
                Hero target = party.stream().filter(Hero::isAlive).findFirst().orElse(null);
                if (target != null) {
                    int hpBefore = target.getHp();
                    monster.attack(target);
                    int dmgTaken = hpBefore - target.getHp();
                    damageTakenTotal += dmgTaken;
                    System.out.println("    " + monster.getName() + " attacks " + target.getName() + " for " + dmgTaken + " DMG.");
                }
            }
        }

        boolean cleared = !monster.isAlive();
        return new FloorResult(cleared, damageTakenTotal, cleared ? "Monster defeated" : "Party wiped out");
    }

    @Override protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("  [Loot] Found 50 Gold on the monster's body.");
    }
}