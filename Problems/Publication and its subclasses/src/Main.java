class Publication {

    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public final String getInfo() {
        // write your code here
        return this.getType() + this.getDetails() + ": " + this.getTitle();

    }

    public String getType() {
        return "Publication";
    }

    public String getDetails() {
        return "";
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    public String getSource() {
        return " (source - " + source + ")";
    }

    public String getType() {
        return "Newspaper";
    }

    public String getDetails() {
        return this.getSource();
    }

    // write your code here

}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    public String getAuthor() {
        return " (author - " + author + ")";
    }

    public String getType() {
        return "Article";
    }

    public String getDetails() {
        return this.getAuthor();
    }

    // write your code here

}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }

    public String getDaysToExpire() {
        return " (days to expire - " + daysToExpire + ")";
    }

    public String getType() {
        return "Announcement";
    }

    public String getDetails() {
        return this.getDaysToExpire();
    }

    // write your code here

}