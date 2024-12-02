package config;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import entity.EcosystemEntity;

import java.io.File;
import java.io.IOException;

public class XMLParser {
    public static EatingRules parseEatingRules() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(new File("C:\\Users\\Семён\\IdeaProjects\\com.javarush.zhuikov.island\\src\\resource\\ChanceToEat.xml"), EatingRules.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при чтении XML файла.");
        }
    }
    public static EntityStatsWrapper loadEntityStats() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(new File("C:\\Users\\Семён\\IdeaProjects\\com.javarush.zhuikov.island\\src\\resource\\EntityСharacteristics.xml"), EntityStatsWrapper.class);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
