package entity.plant;

import config.EntityStatsWrapper;
import config.XMLParser;
import entity.EcosystemEntity;
import entity.animal.Predator.Wolf;
import environment.Location;

public class Plant implements EcosystemEntity {
    private static final EntityStatsWrapper stats = XMLParser.loadEntityStats();
    private static final EntityStatsWrapper.PlantStats plantStats = stats.getPlants().getFirst();
    private Location actualLocation;
    public final static int maxNumberOfCreatures = plantStats.getMaxNumberOnCell();
    private double weight;
    private boolean isDie = false;

    @Override
    public void die() {
        isDie = true;
        actualLocation.getNumberCreaturesPlant().remove(this);
    }

    public Plant(Location actualLocation) {
        weight = plantStats.getWeight();
        this.actualLocation = actualLocation;
    }

    @Override
    public int getMaxNumberOfCreatures() {
        return maxNumberOfCreatures;
    }

    public Location getActualLocation() {
        return actualLocation;
    }


    public double getWeight() {
        return weight;
    }
}
