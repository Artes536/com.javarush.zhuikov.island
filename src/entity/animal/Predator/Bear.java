package entity.animal.Predator;

import config.EntityStatsWrapper;
import config.XMLParser;
import environment.Location;

public class Bear extends PredatorAnimal{

    private static final EntityStatsWrapper.AnimalStats animalStatsOptional = stats.getAnimal()
            .stream()
            .filter(animal -> animal.getType().equalsIgnoreCase(Bear.class.getSimpleName()))
            .findFirst().get();
    public Bear(Location actualLocation) {
        super(animalStatsOptional.getWeight(), animalStatsOptional.getMaxSpeed(), animalStatsOptional.getSatiety(), actualLocation, animalStatsOptional.getMaxNumberOnCell());

    }
}
