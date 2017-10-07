
package ru.solandme.cryptominerutil.business.pojo;



public class Algo {

    private String name;
    private Long hashrate; //Hashrate измеряется в h/s
    private boolean isActive;

    public Algo(String name, Long hashrate, boolean isActive) {
        this.name = name;
        this.hashrate = hashrate;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHashrate() {
        return hashrate;
    }

    public void setHashrate(Long hashrate) {
        this.hashrate = hashrate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
