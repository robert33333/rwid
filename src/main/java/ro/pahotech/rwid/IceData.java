package ro.pahotech.rwid;

public class IceData {
    public static final int NULL = Integer.MIN_VALUE;
    private int relativeHumidity;
    private int surfTemp;
    private boolean alertSent = false;

    public IceData(int relativeHumidity, int surfTemp) {
        this.relativeHumidity = relativeHumidity;
        this.surfTemp = surfTemp;
    }

    public int getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(int relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public int getSurfTemp() {
        return surfTemp;
    }

    public void setSurfTemp(int surfTemp) {
        this.surfTemp = surfTemp;
    }

    public boolean isAlertSent() {
        return alertSent;
    }

    public void setAlertSent(boolean alertSent) {
        this.alertSent = alertSent;
    }
}
