package entity.animal.Predator;

import config.EntityStatsWrapper;
import config.XMLParser;
import environment.Location;

import java.util.Optional;

public class Wolf extends PredatorAnimal {
    private static final EntityStatsWrapper.AnimalStats animalStatsOptional = stats.getAnimal()
            .stream()
            .filter(animal -> animal.getType().equalsIgnoreCase(Wolf.class.getSimpleName()))
            .findFirst().get();
    public Wolf(Location actualLocation) {
            super(animalStatsOptional.getWeight(), animalStatsOptional.getMaxSpeed(), animalStatsOptional.getSatiety(), actualLocation, animalStatsOptional.getMaxNumberOnCell());
    }

}
