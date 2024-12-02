package entity.animal.Herbivore;

import entity.animal.Animal;
import entity.plant.Plant;
import environment.Location;

public class HerbivoreAnimal extends Animal implements Herbivore {


    public HerbivoreAnimal(double weight, int maxSpeed, double maxSatiety, Location actualLocation, int maxNumberOfCreatures) {
        super(weight, maxSpeed, maxSatiety, actualLocation, maxNumberOfCreatures);
    }

    @Override
    public void eat(Plant plant) {
        if(plant == null){
            return;
        }
        actualSatiety += plant.getWeight();
        if (actualSatiety > maxSatiety) {
            actualSatiety = maxSatiety;
        }
        plant.die();

    }

    @Override
    public void eat(Object object) {
        if (object instanceof Plant) {
            eat((Plant) object);
        }
    }

}

