package entitie.animal;

import entitie.plant.Plant;

public class HerbivoreAnimal extends Animal implements Herbivore{

    @Override
    public void eat(Plant plant) {

    }
    @Override
    public void eat(Object object) {
            if (object instanceof Plant) {
                eat((Plant) object);
            }
        }
    }

