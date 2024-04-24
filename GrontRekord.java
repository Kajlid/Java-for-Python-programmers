class GrontRekord implements Comparable<GrontRekord> {
    // vegetableType och country behöver vara public för att nås av den andra klassen.
    public String vegetableType;
    public String country;
    private int size;
    private String unit;

    public GrontRekord(String vegetableType, String country, int size, String unit) {
        // konstruktor till klassen
        // setters
        this.vegetableType = vegetableType;
        this.country = country;
        this.size = size;
        this.unit = unit;
    }

    @Override
    public int compareTo(GrontRekord other) {
        // Sortera grönsaker alfabetiskt
        // kolla först om typerna är samma
        if (this.vegetableType.compareTo(other.vegetableType) != 0) {
            return this.vegetableType.compareTo(other.vegetableType);
        }
        // om samma grönsakstyp, kolla på storlek (störst till minst, minskande order)
        if (Integer.compare(other.size, this.size) != 0) {
            return Integer.compare(other.size, this.size);
        }

        // om samma storlek, jämför land
        if (!this.country.equals(other.country)) {
            return this.country.compareTo(other.country);
        }

        if (this.size < other.size) {
            // om this.size är mindre, ta bort den
            return 1;
        } else if (this.size > other.size) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        // skriv ut output på rätt form
        return vegetableType + " " + country + " " + size + " " + unit;
    }
}



