package com.narxoz.rpg.runner;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.tower.TowerRunResult;
import java.util.List;

public class TowerRunner {
    public TowerRunResult runTower(List<TowerFloor> floors, List<Hero> party) {
        int cleared = 0;

        for (TowerFloor floor : floors) {
            FloorResult result = floor.explore(party);

            if (!result.isCleared() || party.stream().noneMatch(Hero::isAlive)) {
                System.out.println("\n>>> THE PARTY HAS FALLEN! Tower run failed.");
                break;
            }
            cleared++;
        }

        int survivors = (int) party.stream().filter(Hero::isAlive).count();
        boolean reachedTop = (cleared == floors.size());

        return new TowerRunResult(cleared, survivors, reachedTop);
    }
}