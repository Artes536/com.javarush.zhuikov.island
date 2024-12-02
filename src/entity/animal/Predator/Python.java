package entity.animal.Predator;

import config.EntityStatsWrapper;
import config.XMLParser;
import environment.Location;

public class Python extends PredatorAnimal{

    private static final EntityStatsWrapper.AnimalStats animalStatsOptional = stats.getAnimal()
            .stream()
            .filter(animal -> animal.getType().equalsIgnoreCase(Python.class.getSimpleName()))
            .findFirst().get();
    public Python(Location actualLocation) {
        super(animalStatsOptional.getWeight(), animalStatsOptional.getMaxSpeed(), animalStatsOptional.getSatiety(), actualLocation, animalStatsOptional.getMaxNumberOnCell());

    }
}
