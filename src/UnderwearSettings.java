public class UnderwearSettings {
    private int underwearUsage;
    private int totalUnderwear;

    public UnderwearSettings(int underwearUsage, int totalUnderwear) {
        this.underwearUsage = underwearUsage;
        this.totalUnderwear = totalUnderwear;
    }

    public int getUnderwearUsage() {
        return underwearUsage;
    }

    public int getTotalUnderwear() {
        return totalUnderwear;
    }
}