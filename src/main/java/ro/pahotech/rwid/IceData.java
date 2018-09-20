package ro.pahotech.rwid;

public class IceData {
    private boolean relativeHumidity;
    private boolean lowSurfTemp;
    private boolean alertSent = false;

    public IceData(boolean relativeHumidity, boolean lowSurfTemp) {
        this.relativeHumidity = relativeHumidity;
        this.lowSurfTemp = lowSurfTemp;
    }

    public boolean isRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(boolean relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public boolean isLowSurfTemp() {
        return lowSurfTemp;
    }

    public void setLowSurfTemp(boolean lowSurfTemp) {
        this.lowSurfTemp = lowSurfTemp;
    }

    public boolean isAlertSent() {
        return alertSent;
    }

    public void setAlertSent(boolean alertSent) {
        this.alertSent = alertSent;
    }
}
