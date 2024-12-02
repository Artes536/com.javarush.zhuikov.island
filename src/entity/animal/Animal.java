package entity.animal;

import config.EntityStatsWrapper;
import config.Settings;
import config.XMLParser;
import entity.EcosystemEntity;
import environment.Island;
import environment.Location;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements EcosystemEntity {
    protected static final EntityStatsWrapper stats = XMLParser.loadEntityStats();
    protected double weight;
    protected int maxSpeed;
    protected double maxSatiety;
    protected double actualSatiety;
    protected Location actualLocation;
    protected int maxNumberOfCreatures;
    private int reproductionCooldown = 5;
    private boolean isDie = false;

    public Animal(double weight, int maxSpeed, double maxSatiety, Location actualLocation, int maxNumberOfCreatures) {
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.maxSatiety = maxSatiety;
        this.actualLocation = actualLocation;
        this.maxNumberOfCreatures = maxNumberOfCreatures;
        this.actualSatiety = maxSatiety;
    }
    public void move() {
        Location newLocation = chooseLocation();
        if (newLocation != null && isDie == false) {
            actualLocation.getNumberCreaturesAnimal().get(this.getClass()).remove(this);
            actualLocation = newLocation;
            actualLocation.getNumberCreaturesAnimal().get(this.getClass()).add(this);
        }
    }

    public void worker() {
        actualSatiety =  actualSatiety - (maxSatiety * Settings.HUNGER_REDUCTION_PERCENTAGE);
        if (actualSatiety <= 0) {
            die();
        }
    }

    @Override
    public void die() {
        isDie = true;
        actualLocation.getNumberCreaturesAnimal().get(this.getClass()).remove(this);
    }

    public abstract void eat(Object object);


    public Location chooseLocation() {
        int x = actualLocation.getX();
        int y = actualLocation.getY();
        Island island = actualLocation.getIsland();
        int randomMove = ThreadLocalRandom.current().nextInt(0,maxSpeed);
        Location[] neighbors = {
                island.getLocation(x - randomMove, y),
                island.getLocation(x + randomMove, y),
                island.getLocation(x, y - randomMove),
                island.getLocation(x, y + randomMove)
        };
        for (Location neighbor : neighbors) {
            if (neighbor != null && neighbor.canAddAnimal(this)) {
                return neighbor;
            }
        }
        return null;
    }

    public void reproduce() {
        if (reproductionCooldown > 0) {
            reproductionCooldown--;
            return;
        }
        reproductionCooldown = 5;
        List<Animal> animals = actualLocation.getNumberCreaturesAnimal().get(this.getClass());
        int chanceReproduce = ThreadLocalRandom.current().nextInt(0,100);
        if (animals != null && animals.size() >= 2 && actualLocation.canAddAnimal(this) && chanceReproduce < Settings.CHANCE_REPRODUCE) {
            actualLocation.addCreatureAnimal(this.getClass());
        }
        else {
            return;
        }
    }

    public int getMaxNumberOfCreatures() {
        return maxNumberOfCreatures;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isDie() {
        return isDie;
    }

    public double getActualSatiety() {
        return actualSatiety;
    }
}
