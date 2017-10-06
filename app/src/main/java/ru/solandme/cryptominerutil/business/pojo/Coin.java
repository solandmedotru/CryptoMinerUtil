
package ru.solandme.cryptominerutil.business.pojo;


public class Coin {

    private String id;

    private String tag;
    private Long dayBlocks;
    private Double dayBtc;
    private String algo;
    private String estimate;
    private Long hashRate;
    private Long height;
    private Long lastBlock;
    private String name;
    private Long port;
    private Long shares;
    private Long timeSinceLast;
    private Long workers;
    private Long difficultyNow;
    private Long difficultyByDay;
    private String timeStamp;

    public Long getDifficultyNow() {
        return difficultyNow;
    }

    public void setDifficultyNow(Long difficultyNow) {
        this.difficultyNow = difficultyNow;
    }

    public Long getDifficultyByDay() {
        return difficultyByDay;
    }

    public void setDifficultyByDay(Long difficultyByDay) {
        this.difficultyByDay = difficultyByDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDayBlocks() {
        return dayBlocks;
    }

    public void setDayBlocks(Long dayBlocks) {
        this.dayBlocks = dayBlocks;
    }

    public Double getDayBtc() {
        return dayBtc;
    }

    public void setDayBtc(Double dayBtc) {
        this.dayBtc = dayBtc;
    }

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public Long getHashRate() {
        return hashRate;
    }

    public void setHashRate(Long hashRate) {
        this.hashRate = hashRate;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(Long lastBlock) {
        this.lastBlock = lastBlock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public Long getShares() {
        return shares;
    }

    public void setShares(Long shares) {
        this.shares = shares;
    }

    public Long getTimeSinceLast() {
        return timeSinceLast;
    }

    public void setTimeSinceLast(Long timeSinceLast) {
        this.timeSinceLast = timeSinceLast;
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
