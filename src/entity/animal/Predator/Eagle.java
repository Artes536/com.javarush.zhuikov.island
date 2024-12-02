package entity.animal.Predator;

import config.EntityStatsWrapper;
import config.XMLParser;
import environment.Location;

public class Eagle extends PredatorAnimal{

    private static final EntityStatsWrapper.AnimalStats animalStatsOptional = stats.getAnimal()
            .stream()
            .filter(animal -> animal.getType().equalsIgnoreCase(Eagle.class.getSimpleName()))
            .findFirst().get();
    public Eagle(Location actualLocation) {
        super(animalStatsOptional.getWeight(), animalStatsOptional.getMaxSpeed(), animalStatsOptional.getSatiety(), actualLocation, animalStatsOptional.getMaxNumberOnCell());

    }
}
