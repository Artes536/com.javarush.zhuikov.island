package entitie.animal;

import config.EatingRules;
import entitie.plant.Plant;

public class PredatorAnimal extends Animal implements Carnivore{
    @Override
    public void eat(Animal animal) {
        while (true){
            int index = 0;
            EatingRules.Rule rule = eatingRules.getRules().get(index);
//            if(rule.getAnimal())
        }
    }
    @Override
    public void eat(Object object) {
            if (object instanceof Plant) {
                eat((Animal) object);
            }
        }

    }

