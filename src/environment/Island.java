package environment;

import config.EntityImage;
import entity.EcosystemEntity;
import entity.animal.Herbivore.*;
import entity.animal.Predator.*;
import entity.plant.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Island {
    private final Location[][] side;
    public ConcurrentHashMap<Class<? extends EcosystemEntity>, Integer> entityCounts = new ConcurrentHashMap <>();
    private final ConcurrentHashMap <Class<? extends EcosystemEntity>, String> entityEmojiMap = new ConcurrentHashMap <>();

    public Island(int height, int wight) {
        initializeEmojiMap();
        side = new Location[height][wight];
        for (int y = 0; y < side.length; y++) {
            for (int x = 0; x < side[y].length; x++) {
                side[y][x] = new Location(y, x, this);
            }
        }
    }


    public Location[][] getSide() {
        return side;
    }

    private void initializeEmojiMap() {
        entityEmojiMap.put(Buffalo.class, EntityImage.BUFFALO);
        entityEmojiMap.put(Bear.class, EntityImage.BEAR);
        entityEmojiMap.put(Horse.class, EntityImage.HORSE);
        entityEmojiMap.put(Deer.class, EntityImage.DEER);
        entityEmojiMap.put(Boar.class, EntityImage.BOAR);
        entityEmojiMap.put(Sheep.class, EntityImage.SHEEP);
        entityEmojiMap.put(Goat.class, EntityImage.GOAT);
        entityEmojiMap.put(Wolf.class, EntityImage.WOLF);
        entityEmojiMap.put(Python.class, EntityImage.PYTHON);
        entityEmojiMap.put(Fox.class, EntityImage.FOX);
        entityEmojiMap.put(Eagle.class, EntityImage.EAGLE);
        entityEmojiMap.put(Rabbit.class, EntityImage.RABBIT);
        entityEmojiMap.put(Duck.class, EntityImage.DUCK);
        entityEmojiMap.put(Mouse.class, EntityImage.MOUSE);
        entityEmojiMap.put(Caterpillar.class, EntityImage.CATERPILLAR);
        entityEmojiMap.put(Plant.class, EntityImage.PLANT);
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && y >= 0 && x < side[0].length && y < side.length) {
            return side[y][x];
        }
        return null;
    }

    public void countAllEntity() {
        entityCounts.clear();
        for (Location[] row : side) {
            for (Location location : row) {
                Map<Class<? extends EcosystemEntity>, Integer> locationCounts = location.getCountEntity();
                locationCounts.forEach((entityClass, count) ->
                        entityCounts.merge(entityClass, count, Integer::sum)
                );
            }
        }
    }

    public void getInfo() {
        countAllEntity();
        entityCounts.forEach((entityClass, count) -> {
            String emoji = entityEmojiMap.getOrDefault(entityClass, "❓");
            System.out.print(emoji + ":" + count + " ");

        });
        System.out.println();
    }

    public void allGrow() {
        for (Location[] row : side) {
            for (Location location : row) {
                location.growPlant();
            }
        }
    }
}
