package de.base2code.ankijava.model.json;

public class JsonModel {
    private String css = "";
    private long did; // Deck ID
    private JsonField[] flds;
    private int id; // Model ID (matches mid on notes)
    private String latexPost = "";
    private String latexPre = "";
    private long mod; // Modification timestamp
    private String name;
    private int sortf = 0; // Sort field index
    private String tags = "";
    private JsonTmpls[] tmpls;
    private int type = 0; // 0 for standard, 1 for cloze
    private int usn = 0; // Update sequence number: for finding diffs when syncing. See the description in the cards table for more info
    private String vers = "[]";

    public JsonModel(long did, JsonField[] flds, int id, String name, JsonTmpls[] tmpls) {
        this.did = did;
        this.flds = flds;
        this.id = id;
        this.mod = System.currentTimeMillis() / 1000;
        this.name = name;
        this.tmpls = tmpls;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public JsonField[] getFlds() {
        return flds;
    }

    public void setFlds(JsonField[] flds) {
        this.flds = flds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatexPost() {
        return latexPost;
    }

    public void setLatexPost(String latexPost) {
        this.latexPost = latexPost;
    }

    public String getLatexPre() {
        return latexPre;
    }

    public void setLatexPre(String latexPre) {
        this.latexPre = latexPre;
    }

    public long getMod() {
        return mod;
    }

    public void setMod(long mod) {
        this.mod = mod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSortf() {
        return sortf;
    }

    public void setSortf(int sortf) {
        this.sortf = sortf;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public JsonTmpls[] getTmpls() {
        return tmpls;
    }

    public void setTmpls(JsonTmpls[] tmpls) {
        this.tmpls = tmpls;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUsn() {
        return usn;
    }

    public void setUsn(int usn) {
        this.usn = usn;
    }

    public String getVers() {
        return vers;
    }

    public void setVers(String vers) {
        this.vers = vers;
    }

    public static class JsonField {
        // Object for each Field in the model
        private String font = "Arial";
        private String media = "[]";
        private String name;
        private int ord;
        private boolean rtl = false;
        private int size = 20;
        private boolean sticky = false;

        public JsonField(String name, int ord) {
            this.name = name;
            this.ord = ord;
        }
    }

    public static class JsonTmpls {
        private String afmt = "{{FrontSide}}\\n\\n<hr id=answer>\\n\\n{{Back}}";
        private String bafmt = "";
        private String bqfmt = "";
        private String did = null;
        private String name;
        private int ord;
        private String qfmt = "{{Front}}";

        public JsonTmpls(String name, int ord) {
            this.name = name;
            this.ord = ord;
        }
    }
}
