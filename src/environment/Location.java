package environment;

import entitie.EcosystemEntity;
import entitie.animal.Wolf;
import entitie.plant.Plant;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final Map<Class<? extends EcosystemEntity>, Integer> numberCreatures = new HashMap<>();

    public Location() {
        numberCreatures.put(Wolf.class, 0);
        numberCreatures.put(Plant.class, 0);
    }

    public Map<Class<? extends EcosystemEntity>, Integer> getNumberCreatures() {
        return numberCreatures;
    }

}
