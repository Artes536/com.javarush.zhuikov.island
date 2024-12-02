package app;

import config.Settings;
import entity.EcosystemEntity;
import entity.animal.Animal;
import entity.animal.Herbivore.Boar;
import entity.animal.Predator.Wolf;
import environment.Island;
import environment.Location;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(Settings.HEIGHT_ISLAND, Settings.WIDTH_ISLAND);
        Location[][] side = island.getSide();


        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(6);
        scheduledExecutor.scheduleAtFixedRate(() -> {
            try {
                island.getInfo();
                island.allGrow();

                for (Location[] locations : side) {
                    for (Location location : locations) {
                        scheduledExecutor.submit(location::run);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                shutdownExecutor(scheduledExecutor);
            }
        }, 1, 1, TimeUnit.SECONDS);

    }

    private static void shutdownExecutor(ScheduledExecutorService scheduledExecutor) {
        try {
            scheduledExecutor.shutdown();
            if (!scheduledExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduledExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledExecutor.shutdownNow();
        }
    }
    }



