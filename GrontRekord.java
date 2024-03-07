import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.util.*;

class GrontRekord implements Comparable<GrontRekord> {
    private String vegetableType;
    private String country;
    private int size;
    private String unit;

    public GrontRekord(String vegetableType, String country, int size, String unit) {
        this.vegetableType = vegetableType;
        this.country = country;
        this.size = size;
        this.unit = unit;
    }

    @Override
    public int compareTo(GrontRekord other) {
        // Sort by vegetable type (alphabetically)
        int typeComparison = this.vegetableType.compareTo(other.vegetableType);
        if (typeComparison != 0) {
            return typeComparison;
        }
        // If vegetable types are the same, sort by size (largest to smallest)
        int sizeComparison = Integer.compare(other.size, this.size);
        if (sizeComparison != 0) {
            return sizeComparison;
        }
        // If sizes are the same, sort by country (alphabetically)
        return this.country.compareTo(other.country);
    }

    @Override
    public String toString() {
        return vegetableType + " " + country + " " + size + " " + unit;
    }
}



