package entitie.animal;

import config.EatingRules;
import entitie.EcosystemEntity;
import environment.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements EcosystemEntity {
    protected double weight;
    protected int maxSpeed;
    protected final double maxSatiety = Integer.MAX_VALUE;
    protected double actualSatiety;
    protected Location actualLocation;
    protected int maxNumberOfCreatures;
    protected EatingRules eatingRules = new EatingRules();


    public void worker() {
        --actualSatiety;
    }

    public abstract void eat(Object object);

    public void move(Location location) {
        actualLocation = location;
    }

    public void chooseDirection(Location location) {

    }

    public void reproduce() {
        Integer currentCount = actualLocation.getNumberCreatures().get(this.getClass());
        if (currentCount != null && currentCount >= maxNumberOfCreatures) {
            return;
        }
        actualLocation.getNumberCreatures().put(this.getClass(), currentCount + 1);
    }

    public void die() {
        Integer currentCount = actualLocation.getNumberCreatures().get(this.getClass());
        actualLocation.getNumberCreatures().put(this.getClass(), currentCount - 1);
        actualLocation = null;
    }

    public Class<? extends EcosystemEntity> getRandomCreature() {

        List<Class<? extends EcosystemEntity>> keys = new ArrayList<>(actualLocation.getNumberCreatures().keySet());
        int randomNumber = ThreadLocalRandom.current().nextInt(keys.size());
        Class<? extends EcosystemEntity> randomKey = keys.get(randomNumber);
        return randomKey;
    }

}
