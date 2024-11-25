package lr4.music;

import java.io.Serializable;

public class Composition implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private int duration;
    private String style;

    public Composition(String title, String author, int duration, String style) {
        this.title = title;
        this.author = author;
        this.duration = duration;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() { 
        return author;
    }

    public int getDuration() {    // in seconds
        return duration;
    }

    public String getStyle() {
        return style;
    }

    public String toString() {
        return title + " - " + duration +"s - "+ author + " - " + style+".\n";
    }

    public boolean withinBounds(int bound1, int bound2) {
        return duration >= bound1 && duration <= bound2;
    }

}
