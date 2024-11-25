package lr4.music;
import java.util.List;

import lr4.filemanager.FileManager;
import java.util.ArrayList;

public class MusicService {
    private List<Album> albums;

    public MusicService() {
        albums = new ArrayList<>();
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public Album getAlbum(String name) {
        for (Album album : albums) {
            if (album.getName().equalsIgnoreCase(name)) {
                return album;
            }
        }
        return null; // Return null if not found
    }

    public void printAlbums() {
        System.out.println("Albums: ");
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    public void printAlbumsAndCompositions() {
        for (Album album : albums) {
            System.out.println(album);
            System.out.println("Compositions: ");
            album.printAlbum();
        }
    }

    public void sortByStyle(String order) {
        for (Album album : albums) {
            album.sortByStyle(order);
        }
    }

    public void getCompsWithinBounds(int bound1, int bound2) {
        for (Album album : albums) {
            List<Composition> comps = album.getCompsWithinBounds(bound1, bound2);
            System.out.println("Album: " + album.getName());
            for (Composition comp : comps) {
                System.out.println(comp);
            }
        }
    }

    public void addComposition(Album album, Composition composition) {
        if (album != null && composition != null) {
            album.getCompositions().add(composition); // Add the composition to the album's list
        }
    }
    

    public void saveToFile(String filePath) {
            FileManager.serializeAlbums(albums, filePath);
    }

    public void loadFromFile(String filePath) {
        List<Album> loadedAlbums = FileManager.deserializeAlbums(filePath);
        if (loadedAlbums != null) {
            albums.addAll(loadedAlbums); // Add all loaded albums to the existing list
        }
    }
}
