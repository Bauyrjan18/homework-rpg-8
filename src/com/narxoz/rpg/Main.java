package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.runner.TowerRunner;
import com.narxoz.rpg.state.StunnedState;
import com.narxoz.rpg.tower.TowerRunResult;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 8 Demo: The Haunted Tower ===\n");
        Hero warrior = new Hero("Arthas", 120, 25, 10);
        Hero rogue = new Hero("Valeera", 90, 30, 5);
        rogue.setState(new StunnedState()); // Изначально оглушена

        List<Hero> party = Arrays.asList(warrior, rogue);

        List<TowerFloor> tower = Arrays.asList(
                new CombatFloor("Floor 1: Goblin Guard Room", new Monster("Goblin", 40, 15)),
                new TrapFloor(),
                new RestFloor(),
                new CombatFloor("Floor 4: Boss Chamber", new Monster("Orc Chieftain", 100, 25))
        );

        TowerRunner runner = new TowerRunner();
        TowerRunResult result = runner.runTower(tower, party);

        System.out.println("\n=== TOWER RUN RESULTS ===");
        System.out.println("Floors Cleared: " + result.getFloorsCleared() + " / " + tower.size());
        System.out.println("Heroes Surviving: " + result.getHeroesSurviving());
        System.out.println("Tower Conquered: " + (result.isReachedTop() ? "YES! Glory to the heroes!" : "NO. Try again."));
    }
}
