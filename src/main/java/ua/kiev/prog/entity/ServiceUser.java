package ua.kiev.prog.entity;

public class ServiceUser {
    private long serviceId;
    private String name;
    private int rate;
    private long lastValue;

    public ServiceUser() {
    }

    public ServiceUser(long serviceId, String name, int rate, long lastValue) {
        this.serviceId = serviceId;
        this.name = name;
        this.rate = rate;
        this.lastValue = lastValue;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getLastValue() {
        return lastValue;
    }

    public void setLastValue(long lastValue) {
        this.lastValue = lastValue;
    }
}
