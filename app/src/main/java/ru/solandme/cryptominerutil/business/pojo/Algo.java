
package ru.solandme.cryptominerutil.business.pojo;



public class Algo {

    private String name;
    private Long hashrate; //Hashrate измеряется в h/s

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

}
