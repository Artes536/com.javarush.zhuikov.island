package config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Entities")
public class EntityStatsWrapper {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "AnimalStats")
    private List<AnimalStats> animals;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "PlantStats")
    private List<PlantStats> plants;

    // Геттеры и сеттеры
    public List<AnimalStats> getAnimal() {
        return animals;
    }

    public List<PlantStats> getPlants() {
        return plants;
    }

    public static class AnimalStats {

        @JacksonXmlProperty(localName = "type")
        private String type;

        @JacksonXmlProperty(localName = "weight")
        private double weight;

        @JacksonXmlProperty(localName = "maxNumberOnCell")
        private int maxNumberOnCell;

        @JacksonXmlProperty(localName = "maxSpeed")
        private int maxSpeed;

        @JacksonXmlProperty(localName = "satiety")
        private double satiety;

        public AnimalStats() {}

        public AnimalStats(String type, double weight, int maxNumberOnCell, int maxSpeed, double satiety) {
            this.type = type;
            this.weight = weight;
            this.maxNumberOnCell = maxNumberOnCell;
            this.maxSpeed = maxSpeed;
            this.satiety = satiety;
        }

        public String getType() {
            return type;
        }

        public double getWeight() {
            return weight;
        }

        public int getMaxNumberOnCell() {
            return maxNumberOnCell;
        }

        public int getMaxSpeed() {
            return maxSpeed;
        }

        public double getSatiety() {
            return satiety;
        }
    }

    public static class PlantStats {

        @JacksonXmlProperty(localName = "type")
        private String type;

        @JacksonXmlProperty(localName = "weight")
        private double weight;

        @JacksonXmlProperty(localName = "maxNumberOnCell")
        private int maxNumberOnCell;

        @JacksonXmlProperty(localName = "maxSpeed")
        private int maxSpeed;

        @JacksonXmlProperty(localName = "satiety")
        private double satiety;

        public PlantStats() {}

        public PlantStats(String type, double weight, int maxNumberOnCell) {
            this.type = type;
            this.weight = weight;
            this.maxNumberOnCell = maxNumberOnCell;
        }

        public String getType() {
            return type;
        }


        public double getWeight() {
            return weight;
        }


        public int getMaxNumberOnCell() {
            return maxNumberOnCell;
        }


        public int getMaxSpeed() {
            return maxSpeed;
        }


        public double getSatiety() {
            return satiety;
        }

    }
}
