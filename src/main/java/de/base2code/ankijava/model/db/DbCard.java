package de.base2code.ankijava.model.db;

public class DbCard {

    private int id;

    // notes.id
    private int nid;

    // deck id
    private int did;

    // ordinal : identifies which of the card templates or cloze deletions it corresponds to
    //      for card templates, valid values are from 0 to num templates - 1
    //      for cloze deletions, valid values are from 0 to max cloze index - 1 (they're 0 indexed despite the first being called `c1`)
    private int ord;

    // mod : modification time as epoch seconds
    private int mod;

    // usn : update sequence number : used to figure out diffs when syncing.
    //      value of -1 indicates changes that need to be pushed to server.
    //      usn < server usn indicates changes that need to be pulled from server.
    private int usn;

    // type : 0=new, 1=learning, 2=due, 3=filtered
    private int type;

    // queue : -3=sched buried, -2=user buried, -1=suspended,
    //      0=new, 1=learning, 2=review (as for type)
    //      3=in learning, next rev in at least a day after the previous review
    //      4=preview
    private int queue;

    // due : Due is used differently for different card types:
    //      new: note id or random int
    //      due: integer day, relative to the collection's creation time
    //      learning: integer timestamp
    private int due;

    // ivl : interval (used in SRS algorithm). Negative = seconds, positive = days
    private int ivl;

    // factor : The ease factor of the card in permille (parts per thousand).
    // If the ease factor is 2500, the card’s interval will be multiplied by 2.5 the next time you press Good.
    private int factor;

    // reps : number of reviews
    private int reps;

    // lapses : the number of times the card went from a "was answered correctly" to "was answered incorrectly" state
    private int lapses;

    // of the form a*1000+b, with:
    //      a the number of reps left today
    //      b the number of reps left till graduation
    //      for example: '2004' means 2 reps left today and 4 reps till graduation
    private int left;

    // odue : original due: In filtered decks, it's the original due date that the card had before moving to filtered.
    // If the card lapsed in scheduler1, then it's the value before the lapse. (This is used when switching to scheduler 2. At this time, cards in learning becomes due again, with their previous due date)
    // -- In any other case it's 0.
    private int odue;

    // odid : original did: only used when the card is currently in filtered deck
    private int odid;

    // flags : an integer. This integer mod 8 represents a "flag", which can be see in browser and while reviewing a note. Red 1, Orange 2, Green 3, Blue 4, no flag: 0. This integer divided by 8 represents currently nothing
    private int flags;

    // data : currently unused
    private String data;

    public DbCard(int id, int nid, int did, int ord, int mod, int usn, int type, int queue, int due, int ivl, int factor, int reps, int lapses, int left, int odue, int odid, int flags, String data) {
        this.id = id;
        this.nid = nid;
        this.did = did;
        this.ord = ord;
        this.mod = mod;
        this.usn = usn;
        this.type = type;
        this.queue = queue;
        this.due = due;
        this.ivl = ivl;
        this.factor = factor;
        this.reps = reps;
        this.lapses = lapses;
        this.left = left;
        this.odue = odue;
        this.odid = odid;
        this.flags = flags;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public int getIvl() {
        return ivl;
    }

    public void setIvl(int ivl) {
        this.ivl = ivl;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getLapses() {
        return lapses;
    }

    public void setLapses(int lapses) {
        this.lapses = lapses;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getOdue() {
        return odue;
    }

    public void setOdue(int odue) {
        this.odue = odue;
    }

    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
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
