package de.base2code.ankijava.db.model;

public class DbNote {
    private int id;

    // guid: globally unique id, almost certainly used for syncing
    private String guid;

    // mid: model id
    private int mid;

    // mod: modification timestamp, epoch seconds
    private int mod;

    // usn: update sequence number: for finding diffs when syncing. See the description in the cards table for more info
    private int usn;

    // tags: space-separated string of tags.
    // includes space at the beginning and end, for LIKE "% tag %" queries
    private String tags;

    // flds: the values of the fields in this note. separated by 0x1f (31) character.
    private String flds;

    // sfld: sort field: used for quick sorting and duplicate check
    // The sort field is an integer so that when users are sorting on a field that contains only numbers, they are sorted in numeric instead of lexical order. Text is stored in this integer field.
    private String sfld;

    // csum: field checksum used for duplicate check.
    // integer representation of first 8 digits of sha1 hash of the first field
    private int csum;

    // flags: unused
    private int flags;

    // data: unused
    private String data;

    public DbNote(int id, String guid, int mid, int mod, int usn, String tags, String flds, String sfld, int csum, int flags, String data) {
        this.id = id;
        this.guid = guid;
        this.mid = mid;
        this.mod = mod;
        this.usn = usn;
        this.tags = tags;
        this.flds = flds;
        this.sfld = sfld;
        this.csum = csum;
        this.flags = flags;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public int getUsn() {
        return usn;
    }

    public void setUsn(int usn) {
        this.usn = usn;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFlds() {
        return flds;
    }

    public void setFlds(String flds) {
        this.flds = flds;
    }

    public String getSfld() {
        return sfld;
    }

    public void setSfld(String sfld) {
        this.sfld = sfld;
    }

    public int getCsum() {
        return csum;
    }

    public void setCsum(int csum) {
        this.csum = csum;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
