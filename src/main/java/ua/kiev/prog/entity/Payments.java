package ua.kiev.prog.entity;

/**
 * Created by mbro8_000 on 11.12.2015.
 */
public class Payments {
    private String serviceName;
    private long prevValue;
    private long currValue;
    private long rate;
    private long diff;
    private long mustPay;

    public Payments(String serviceName, long prevValue, long currValue, long rate) {
        this.serviceName = serviceName;
        this.prevValue = prevValue;
        this.currValue = currValue;
        this.rate = rate;
    }

    public Payments() {

    }

    public void calc(){
        diff = currValue - prevValue;
        mustPay = rate * (diff);
    }

    public long getDiff() {
        return diff;
    }

    public void setDiff(long diff) {
        this.diff = diff;
    }

    public long getMustPay() {
        return mustPay;
    }

    public void setMustPay(long mustPay) {
        this.mustPay = mustPay;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(long prevValue) {
        this.prevValue = prevValue;
    }

    public long getCurrValue() {
        return currValue;
    }

    public void setCurrValue(long currValue) {
        this.currValue = currValue;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

}
