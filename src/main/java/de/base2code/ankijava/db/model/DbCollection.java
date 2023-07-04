package de.base2code.ankijava.db.model;

public class DbCollection {
    private int id;

    // crt: timestamp of the creation date in second. It's correct up to the day. For V1 scheduler, the hour corresponds to starting a new day. By default, new day is 4.
    private int crt;

    // mod: timestamp of the last modification date in second.
    private int mod;

    // scm: schema mod time: time when "schema" was modified.
    // If server scm is different from the client scm a full-sync is required.
    private int scm;

    // ver: version
    private int ver;

    // dty: dirty: unused, set to 0
    private int dty;

    // usn: update sequence number: used for syncing. See usn in cards table for more details.
    private int usn;

    // ls: "last sync time"
    private int ls;

    // conf: json object containing configuration options that are synced
    // Described below in "configuration JSONObjects" https://github.com/ankidroid/Anki-Android/wiki/Database-Structure
    private String conf;

    // json object of json object(s) representing the models (aka Note types)
    // keys of this object are strings containing integers: "creation time in epoch milliseconds" of the models
    // values of this object are other json objects of the form described below in "Models JSONObjects"
    private String models;

    // json object of json object(s) representing the deck(s)
    // keys of this object are strings containing integers: "deck creation time in epoch milliseconds" for most decks, "1" for the default deck
    // values of this object are other json objects of the form described below in "Decks JSONObjects"
    private String decks;

    // json object of json object(s) representing the options group(s) for decks
    // keys of this object are strings containing integers: "options group creation time in epoch milliseconds" for most groups, "1" for the default option group
    // values of this object are other json objects of the form described below in "DConf JSONObjects"
    private String dconf;

    // tags: a cache of tags used in the collection (This list is displayed in the browser. Potentially at other place)
    private String tags;

    public DbCollection(int id, int crt, int mod, int scm, int ver, int dty, int usn, int ls, String conf, String models, String decks, String dconf, String tags) {
        this.id = id;
        this.crt = crt;
        this.mod = mod;
        this.scm = scm;
        this.ver = ver;
        this.dty = dty;
        this.usn = usn;
        this.ls = ls;
        this.conf = conf;
        this.models = models;
        this.decks = decks;
        this.dconf = dconf;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCrt() {
        return crt;
    }

    public void setCrt(int crt) {
        this.crt = crt;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public int getScm() {
        return scm;
    }

    public void setScm(int scm) {
        this.scm = scm;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public int getDty() {
        return dty;
    }

    public void setDty(int dty) {
        this.dty = dty;
    }

    public int getUsn() {
        return usn;
    }

    public void setUsn(int usn) {
        this.usn = usn;
    }

    public int getLs() {
        return ls;
    }

    public void setLs(int ls) {
        this.ls = ls;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getDecks() {
        return decks;
    }

    public void setDecks(String decks) {
        this.decks = decks;
    }

    public String getDconf() {
        return dconf;
    }

    public void setDconf(String dconf) {
        this.dconf = dconf;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
