import java.util.*;
/**
 * Library has to static fields to store all the created
 * Movie objects and Watchlist objects.
 * They are stored in HashSets to avoid possible duplicates.
 *
 * @author Haochen Liu
 * @version 1.0
 */
public class Library {

    private static HashSet<Movie> libMovies = new HashSet<Movie>();
    private static HashSet<Watchlist> libWatchlists = new HashSet<Watchlist>();

    /**
     * Add a Movie object to Library.
     *
     * @param toAdd the Movie object to be added
     */
    public static void addMovie(Movie toAdd){
        Library.libMovies.add(toAdd);
    }

    /**
     * Remove a Movie object from the library.
     *
     * @param toRemove the Movie to be removed
     */
    public static void removeMovie(Movie toRemove){
        Library.libMovies.remove(toRemove);
    }
    /**
     * Add a Watchlist object to Library.
     *
     * @param toAdd the Watchlist object to be added
     */
    public static void addWatchlist(Watchlist toAdd){
        Library.libWatchlists.add(toAdd);
    }

    /**
     * Remove a Watchlist object from the library.
     *
     * @param toRemove the Watchlist to be removed
     */
    public static void removeWatchlist(Watchlist toRemove){
        Library.libWatchlists.remove(toRemove);
    }
}
