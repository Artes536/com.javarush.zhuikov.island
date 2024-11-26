package config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class EatingRules {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Rule")
    private List<Rule> rules;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public static class Rule {
        @JacksonXmlProperty(isAttribute = true)
        private String animal;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Prey")
        private List<Prey> preys;


        public String getAnimal() {
            return animal;
        }

        public void setAnimal(String animal) {
            this.animal = animal;
        }

        public List<Prey> getPreys() {
            return preys;
        }

        public void setPreys(List<Prey> preys) {
            this.preys = preys;
        }
    }

    public static class Prey {
        @JacksonXmlProperty(isAttribute = true)
        private String type;

        @JacksonXmlProperty(isAttribute = true)
        private double chance;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getChance() {
            return chance;
        }

        public void setChance(double chance) {
            this.chance = chance;
        }
    }
}
