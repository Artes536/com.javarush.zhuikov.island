package app;

import config.EatingRules;
import config.Settings;
import config.XMLParser;
import environment.Island;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(Settings.heightIsland, Settings.widthIsland);
        EatingRules eatingRules = XMLParser.parseEatingRules("C:\\Users\\Семён\\IdeaProjects\\com.javarush.zhuikov.island\\src\\resources\\ChanceToEat.xml");

    }
}
