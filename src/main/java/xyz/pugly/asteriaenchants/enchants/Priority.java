package xyz.pugly.asteriaenchants.enchants;

public enum Priority {

    // Matches ordering of EventPriority
    LOWEST,
    LOW,
    NORMAL,
    HIGH,
    HIGHEST;

    public static Priority getPriority(String priority) {
        switch (priority) {
            case "LOWEST":
                return LOWEST;
            case "LOW":
                return LOW;
            case "NORMAL":
                return NORMAL;
            case "HIGH":
                return HIGH;
            case "HIGHEST":
                return HIGHEST;
            default:
                return NORMAL;
        }
    }

}
