package entity.animal.Predator;

import config.EntityStatsWrapper;
import config.XMLParser;
import environment.Location;

public class Fox extends PredatorAnimal{

    private static final EntityStatsWrapper.AnimalStats animalStatsOptional = stats.getAnimal()
            .stream()
            .filter(animal -> animal.getType().equalsIgnoreCase(Fox.class.getSimpleName()))
            .findFirst().get();
    public Fox(Location actualLocation) {
        super(animalStatsOptional.getWeight(), animalStatsOptional.getMaxSpeed(), animalStatsOptional.getSatiety(), actualLocation, animalStatsOptional.getMaxNumberOnCell());
    }
}
