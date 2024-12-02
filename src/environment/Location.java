package environment;

import config.Settings;
import entity.EcosystemEntity;
import entity.animal.Animal;
import entity.animal.Herbivore.*;
import entity.animal.Predator.*;
import entity.plant.Plant;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Location implements Runnable {
    private final ConcurrentHashMap<Class<? extends Animal>, CopyOnWriteArrayList<Animal>> numberCreaturesAnimal = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<Plant> numberCreaturesPlant = new CopyOnWriteArrayList<>();
    private final Island island;
    private final int y;
    private final int x;

    public Location(int y, int x, Island island) {
        this.y = y;
        this.x = x;
        this.island = island;
        addCreatureAnimal(Boar.class);
        addCreatureAnimal(Wolf.class);
        addCreatureAnimal(Buffalo.class);
        addCreatureAnimal(Caterpillar.class);
        addCreatureAnimal(Deer.class);
        addCreatureAnimal(Duck.class);
        addCreatureAnimal(Goat.class);
        addCreatureAnimal(Horse.class);
        addCreatureAnimal(Mouse.class);
        addCreatureAnimal(Rabbit.class);
        addCreatureAnimal(Bear.class);
        addCreatureAnimal(Eagle.class);
        addCreatureAnimal(Fox.class);
        addCreatureAnimal(Python.class);
        addCreatureAnimal(Sheep.class);
        numberCreaturesPlant.add(new Plant(this));
        for (Map.Entry<Class<? extends Animal>, CopyOnWriteArrayList<Animal>> entry : numberCreaturesAnimal.entrySet()) {
            Class<? extends EcosystemEntity> animalType = entry.getKey();
            List<? extends EcosystemEntity> ecosystemEntities = numberCreaturesAnimal.get(animalType);

            int maxCount = ecosystemEntities.get(0).getMaxNumberOfCreatures();
            int initialCount = (int) (maxCount * Settings.PERCENT_OF_ANIMALS_AT_THE_START);
            for (int i = 0; i < initialCount; i++) {
                addCreatureAnimal((Class<? extends Animal>) ecosystemEntities.get(0).getClass());
            }

        }
    }


    public <T> void addCreatureAnimal(Class<T> type) {
        try {
            T newCreature = (T) type.getDeclaredConstructor(Location.class).newInstance(this);

            CopyOnWriteArrayList<T> creaturesList = (CopyOnWriteArrayList<T>) numberCreaturesAnimal.computeIfAbsent((Class<? extends Animal>) type, k -> new CopyOnWriteArrayList<>());
            creaturesList.add(newCreature);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ConcurrentHashMap<Class<? extends Animal>, CopyOnWriteArrayList<Animal>> getNumberCreaturesAnimal() {
        return numberCreaturesAnimal;
    }

    public List<Plant> getNumberCreaturesPlant() {
        return numberCreaturesPlant;
    }

    public Island getIsland() {
        return island;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Animal getRandomAnimal() {
        CopyOnWriteArrayList<Class<? extends Animal>> animalTypes = new CopyOnWriteArrayList<>(numberCreaturesAnimal.keySet());
        if(animalTypes.isEmpty()){
            return null;
        }
        int randomNumber = ThreadLocalRandom.current().nextInt(0, animalTypes.size());
        Class<? extends Animal> aClass = animalTypes.get(randomNumber);
        CopyOnWriteArrayList<Animal> animals = numberCreaturesAnimal.get(aClass);
        if (animals == null || animals.isEmpty()) {
            return null;
        }
        int randomNumberAnimal = ThreadLocalRandom.current().nextInt(0, animals.size());
        return animals.get(randomNumberAnimal);
    }

    @Override
    public void run() {
        for (Map.Entry<Class<? extends Animal>, CopyOnWriteArrayList<Animal>> entry : numberCreaturesAnimal.entrySet()) {
            List<Animal> animals = entry.getValue();

            for (Animal animal : animals) {
                animal.move();
                if (animal instanceof PredatorAnimal) {
                    Animal prey = getRandomAnimal();
                    if (prey != null) {
                        ((PredatorAnimal) animal).eat(prey);
                    }
                } else if (animal instanceof HerbivoreAnimal) {
                    if (!numberCreaturesPlant.isEmpty()) {
                        Plant plant = numberCreaturesPlant.get(ThreadLocalRandom.current().nextInt(0,numberCreaturesPlant.size()));
                        animal.eat(plant);
                    }
                }
                animal.worker();
                animal.reproduce();


            }
        }
    }

    public ConcurrentHashMap<Class<? extends EcosystemEntity>, Integer> getCountEntity(){
        ConcurrentHashMap<Class<? extends EcosystemEntity>, Integer> entityCounts = new ConcurrentHashMap<>();
        numberCreaturesAnimal.forEach((animalClass, animals) ->
                entityCounts.merge(animalClass, animals.size(), Integer::sum)

        );
        entityCounts.put(Plant.class,numberCreaturesPlant.size());
        return entityCounts;

    }


    public boolean canAddAnimal(Animal animal) {
        CopyOnWriteArrayList<Animal> animals = numberCreaturesAnimal.get(animal.getClass());
        if (animals == null || animals.size() < animal.getMaxNumberOfCreatures()) {
            return true;
        }
        return false;
    }
    public void growPlant() {
        if (numberCreaturesPlant.size() < Plant.maxNumberOfCreatures) {
            for (int i = 0; i < Plant.maxNumberOfCreatures-numberCreaturesPlant.size(); i++) {
                numberCreaturesPlant.add(new Plant(this));
            }
        } else if (numberCreaturesPlant.isEmpty()) {
            numberCreaturesPlant.add(new Plant(this));
        }
            }

}



