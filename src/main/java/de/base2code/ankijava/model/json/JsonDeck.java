package de.base2code.ankijava.model.json;

public class JsonDeck {
    private String name;
    private int extendRev = 10;
    private int usn = 0;
    private boolean collapsed = true;
    private boolean browserCollapsed = true;
    private int[] newToday = new int[]{0, 0};
    private int[] revToday = new int[]{0, 0};
    private int[] lrnToday = new int[]{0, 0};
    private int[] timeToday = new int[]{0, 0};
    private int dyn = 0;
    private int extendNew = 10;
    private int conf = 1;
    private int id; // Deck ID
    private long mod = System.currentTimeMillis() / 1000;
    private String desc;

    public JsonDeck(String name, int id, String desc) {
        this.name = name;
        this.id = id;
        this.desc = desc;
    }
}
