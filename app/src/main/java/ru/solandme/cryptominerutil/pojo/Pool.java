package ru.solandme.cryptominerutil.pojo;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Pool extends RealmObject {

    @PrimaryKey
    private String id;
    @Required
    private String name;
    private RealmList<Coin> coins;
    private RealmList<Algo> algos;
    private String timeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(RealmList<Coin> coins) {
        this.coins = coins;
    }

    public RealmList<Algo> getAlgos() {
        return algos;
    }

    public void setAlgos(RealmList<Algo> algos) {
        this.algos = algos;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
