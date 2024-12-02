package entity.animal.Predator;

import config.EatingRules;
import config.XMLParser;
import entity.animal.Animal;
import entity.plant.Plant;
import environment.Location;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class PredatorAnimal extends Animal implements Carnivore {
    private static final EatingRules EATING_RULES = XMLParser.parseEatingRules();
    private static final int MIN_CHANCE_OF_CAPTURE = 0;
    private static final int MAX_CHANCE_OF_CAPTURE = 100;

    public PredatorAnimal(double weight, int maxSpeed, double maxSatiety,  Location actualLocation, int maxNumberOfCreatures) {
        super(weight, maxSpeed, maxSatiety,actualLocation,  maxNumberOfCreatures);
    }

    @Override
    public void eat(Animal animal) {
        if(animal == null){
            return;
        }
        Optional<EatingRules.Rule> predator = EATING_RULES
                .getRules()
                .stream()
                .filter(rule -> rule
                        .getAnimal()
                        .equalsIgnoreCase(this.getClass()
                                .getSimpleName()))
                .findFirst();

        Optional<EatingRules.Prey> prey = predator.get()
                .getPreys()
                .stream()
                .filter(prey1 -> prey1
                        .getPrey()
                        .equalsIgnoreCase(animal
                                .getClass()
                                .getSimpleName()))
                .findFirst();
        if (prey.isEmpty()) {
            return;
        }
        if(prey.get().getChance() == 0){
            return;
        }
        if(tryCatchPrey(prey.get().getChance())){
            animal.die();
            actualSatiety = animal.getWeight();
            if(actualSatiety > maxSatiety){
                actualSatiety = maxSatiety;
            }
        }
    }


    @Override
    public void eat(Object object) {
        if (object instanceof Plant) {
            eat((Animal) object);
        }
    }
    private boolean tryCatchPrey(double chance) {
        double randomValue = ThreadLocalRandom.current().nextDouble(MIN_CHANCE_OF_CAPTURE, MAX_CHANCE_OF_CAPTURE);
        return randomValue < chance;
    }


}

