package ro.pahotech.rwid;

class Property {
    private final String name;
    private final String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    Property(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
