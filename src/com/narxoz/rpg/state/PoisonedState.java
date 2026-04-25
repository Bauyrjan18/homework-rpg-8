package com.narxoz.rpg.state;
import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState {
    private int turnsLeft;

    public PoisonedState(int duration) {
        this.turnsLeft = duration;
    }

    @Override public String getName() { return "Poisoned (" + turnsLeft + " turns)"; }

    @Override public int modifyOutgoingDamage(int basePower) {
        return (int) (basePower * 0.7);
    }

    @Override public int modifyIncomingDamage(int rawDamage) {
        return (int) (rawDamage * 1.2);
    }

    @Override public void onTurnStart(Hero hero) {
        System.out.println("    [POISON] " + hero.getName() + " takes 5 poison damage!");
        hero.takeDamage(5);
    }

    @Override public void onTurnEnd(Hero hero) {
        turnsLeft--;
        if (turnsLeft <= 0) {
            System.out.println("    [CURE] " + hero.getName() + " has recovered from poison.");
            hero.setState(new NormalState());
        }
    }

    @Override public boolean canAct() { return true; }
}