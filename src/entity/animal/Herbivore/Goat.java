package entity.animal.Herbivore;

import config.EntityStatsWrapper;
import config.XMLParser;
import entity.animal.Predator.Wolf;
import environment.Location;

public class Goat extends HerbivoreAnimal{

    private static final EntityStatsWrapper.AnimalStats animalStatsOptional = stats.getAnimal()
            .stream()
            .filter(animal -> animal.getType().equalsIgnoreCase(Goat.class.getSimpleName()))
            .findFirst().get();
    public Goat(Location actualLocation) {
        super(animalStatsOptional.getWeight(), animalStatsOptional.getMaxSpeed(), animalStatsOptional.getSatiety(), actualLocation, animalStatsOptional.getMaxNumberOnCell());

    }
}