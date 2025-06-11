package ee.taltech.iti0202.detective.jsonloader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.iti0202.detective.crimecase.CrimeCase;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JsonLoader {
    /**
     * Loads crime cases from the specified JSON file.
     *
     * @param filePath The path to the JSON file containing crime case data.
     * @return A list of CrimeCase objects. If an error occurs during loading, an empty list is returned.
     */
    public static List<CrimeCase> loadCases(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<CrimeCase>>() { });
        } catch (IOException e) {
            System.out.println("Error loading JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
