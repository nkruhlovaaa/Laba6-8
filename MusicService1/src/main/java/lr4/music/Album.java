package lr4.music;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Composition> album;
    private int totalDuration;
    private String name;
    private String author;

    public Album(String name, String author) {
        this.name = name;
        this.author = author;
        totalDuration = 0;
        album = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public String getAuthor() { 
        return author;
    }

    public List<Composition> getAlbum() {
        return album;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void addComposition(Composition composition) {
        album.add(composition);
        totalDuration += composition.getDuration();
    }

    public List<Composition> getCompositions() {
        return album;
    }

    public List<Composition> getCompsWithinBounds(int bound1, int bound2) {
        List<Composition> result = new ArrayList<>();

        for (Composition composition : album) {
            if (composition.withinBounds(bound1, bound2)) {
                result.add(composition);
            }
        }
        return result;
    }

    public void sortByStyle(String order) {
        if (order.equals("d")) {
            album.sort((a, b) -> b.getStyle().compareTo(a.getStyle())); // sort by style in alphabetical order
        }else{
            album.sort((a, b) -> a.getStyle().compareTo(b.getStyle())); // sort by style in reverse alphabetical order
        }
    }

    public void printAlbum() {
        for(Composition composition : album) {
            System.out.println(composition);
        }
    }

    public String toString() {
        return name + " - " + author;
    }
}

