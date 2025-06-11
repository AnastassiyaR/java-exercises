package ee.taltech.iti0202.productscatalog.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.iti0202.productscatalog.product.Product;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Json {

    /**
     * Loads JSON
     * @param filePath
     * @return a downloaded Json
     */
    public static List<Product> loadCases(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<Product>>() { });
        } catch (IOException e) {
            System.out.println("Error loading JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
