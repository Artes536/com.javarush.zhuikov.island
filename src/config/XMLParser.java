package config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class XMLParser {
    public static EatingRules parseEatingRules(String filePath) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(new File(filePath), EatingRules.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при чтении XML файла.");
        }
    }
}
