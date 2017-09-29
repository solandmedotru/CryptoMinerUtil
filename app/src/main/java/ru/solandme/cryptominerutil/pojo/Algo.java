
package ru.solandme.cryptominerutil.pojo;

import io.realm.RealmObject;

public class Algo extends RealmObject {

    private String actualLast24h;
    private Long coins;
    private String estimateCurrent;
    private String estimateLast24h;
    private Long fees;
    private Long hashrate;
    private Double hashrateLast24h;
    private String name;
    private Long port;
    private Long workers;
    private String timeStamp;

    public String getActualLast24h() {
        return actualLast24h;
    }

    public void setActualLast24h(String actualLast24h) {
        this.actualLast24h = actualLast24h;
    }

    public Long getCoins() {
        return coins;
    }

    public void setCoins(Long coins) {
        this.coins = coins;
    }

    public String getEstimateCurrent() {
        return estimateCurrent;
    }

    public void setEstimateCurrent(String estimateCurrent) {
        this.estimateCurrent = estimateCurrent;
    }

    public String getEstimateLast24h() {
        return estimateLast24h;
    }

    public void setEstimateLast24h(String estimateLast24h) {
        this.estimateLast24h = estimateLast24h;
    }

    public Long getFees() {
        return fees;
    }

    public void setFees(Long fees) {
        this.fees = fees;
    }

    public Long getHashrate() {
        return hashrate;
    }

    public void setHashrate(Long hashrate) {
        this.hashrate = hashrate;
    }

    public Double getHashrateLast24h() {
        return hashrateLast24h;
    }

    public void setHashrateLast24h(Double hashrateLast24h) {
        this.hashrateLast24h = hashrateLast24h;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public Long getWorkers() {
        return workers;
    }

    public void setWorkers(Long workers) {
        this.workers = workers;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
