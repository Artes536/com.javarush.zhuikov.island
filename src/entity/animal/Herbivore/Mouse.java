package entity.animal.Herbivore;

import config.EntityStatsWrapper;
import config.XMLParser;
import entity.animal.Predator.Wolf;
import environment.Location;

public class Mouse extends HerbivoreAnimal{

    private static final EntityStatsWrapper.AnimalStats animalStatsOptional = stats.getAnimal()
            .stream()
            .filter(animal -> animal.getType().equalsIgnoreCase(Mouse.class.getSimpleName()))
            .findFirst().get();
    public Mouse(Location actualLocation) {
        super(animalStatsOptional.getWeight(), animalStatsOptional.getMaxSpeed(), animalStatsOptional.getSatiety(), actualLocation, animalStatsOptional.getMaxNumberOnCell());

    }
}
