import java.util.*;

/**
 * This class stores some necessary information about a watchlist.
 * Watchlist stores the reference of Movie objects.
 * The name of each watchlist is unique.
 * Watchlist knows how many valid movies it contains.
 * Watchlist stores all languages and studios of the movies without duplicates.
 *
 * @author Haochen Liu
 * @version 1.0
 */

public class Watchlist {

    private String name;
    private LinkedList<Movie> listMovie;
    private int validCount;

    private HashMap<String,Integer> hashMapStudio;
    private HashMap<String,Integer> hashMapLanguage;

    //final (not change the location in memory)
    private static LinkedList<String> existedWatchlist = new LinkedList<String>();

    /**
     * Constructor of Watchlist.
     * It takes a String as its name, and an array of Movie object(s);
     * Each Watchlist object has its unique name.
     * The references of Movie objects are stored in a linked list.
     * All publishing studios and languages will be stored without duplicates.
     * Studios and languages are stored in key-value pairs, and the value is
     * how many times they are mentioned. If the hashmap does not contain this key,
     * it will be added and set the value to 1. If it already has this key, then
     * the value of the key will add 1. This makes sure no duplicates exists.
     *
     * @param wName the name of the Watchlist object
     * @param arrayMovie the array of Movie object to be added to the watchlist
     */
    public Watchlist(String wName, Movie[] arrayMovie){

        // null input will not be accepted
        if(wName==null || arrayMovie == null){
            throw new IllegalArgumentException("Input cannot be null.");
        }
        // count the total number of Movie objects
        int movieCount = 0;
        for(int i=0;i<arrayMovie.length;i++){
            if(arrayMovie[i]!=null){
                movieCount++;
            }
        }
        if(movieCount == 0){
            throw new IllegalArgumentException("Invalid input: the array must contain at least one movie");
        }
        // names of Watchlist objects are unique.
        if(Watchlist.existedWatchlist.contains(wName)){
            throw new IllegalArgumentException("The name for the watchlist has already been used. Please set a new one.");
        }
        this.name = wName;
        existedWatchlist.add(wName);
        this.listMovie = new LinkedList<Movie>();
        this.hashMapStudio = new HashMap<String,Integer>();
        this.hashMapLanguage = new HashMap<String,Integer>();
        int vCount = 0;
        for(int i=0; i<movieCount; i++){
            if(arrayMovie[i]!=null){
                if(!this.listMovie.contains(arrayMovie[i])){
                    //store the Movie object to this Watchlist
                    this.listMovie.add(arrayMovie[i]);
                    // check whether the language has already been added
                    if(this.hashMapLanguage.containsKey(arrayMovie[i].getLanguage())){
                        Integer lCount = this.hashMapLanguage.get(arrayMovie[i].getLanguage()) + 1;
                        this.hashMapLanguage.replace(arrayMovie[i].getLanguage(),lCount);
                    }
                    // if not, put this key-value pair in with value=1
                    else{
                        this.hashMapLanguage.put(arrayMovie[i].getLanguage(),1);
                    }
                    // similar to above.
                    if(this.hashMapStudio.containsKey(arrayMovie[i].getPublishingStudio())){
                        Integer sCount = this.hashMapStudio.get(arrayMovie[i].getPublishingStudio()) + 1;
                        this.hashMapStudio.replace(arrayMovie[i].getPublishingStudio(),sCount);
                    }
                    else {
                        this.hashMapStudio.put(arrayMovie[i].getPublishingStudio(), 1);
                    }
                }
                // update the validity of the Movie
                arrayMovie[i].updateValidity();
                if(arrayMovie[i].getValidity()){
                    vCount++;
                }
            }
        }
        this.validCount = vCount;

        // add this Watchlist to Library
        Library.addWatchlist(this);
    }

    /**
     * Rename the Watchlist after created.
     * No existing name will be used.
     *
     * @param newName the new name for this Watchlist
     */
    public void rename(String newName){
        // no existing name will be used
        if(Watchlist.existedWatchlist.contains(newName)){
            throw new IllegalArgumentException("This name has been used.");
        }
        this.name = newName;
    }

    /**
     * Get a String array of all languages in this Watchlist.
     *
     * @return the String array that contains all languages without duplicates
     */
    public String[] getLanguages(){
        Set<String> sLan = this.hashMapLanguage.keySet();
        int lSize = sLan.size();
        int count = 0;
        String[] arrayLanguage = new String[lSize];
        if(lSize != 0) {
            for (String lan : sLan) {
                arrayLanguage[count] = lan;
                count++;
            }
        }
        return arrayLanguage;


    }
    /**
     * Get a String array of all publishing studios in this Watchlist.
     *
     * @return the String array that contains all publishing studios without duplicates
     */
    public String[] getStudios(){
        Set<String> sStu = this.hashMapStudio.keySet();
        int sSize = sStu.size();
        int count = 0;
        String[] arrayStudio = new String[sSize];
        if(sSize != 0) {
            for (String stu : sStu) {
                arrayStudio[count] = stu;
                count++;
            }
        }
        return arrayStudio;
    }

    /**
     * Remove the first movie in the Watchlist.
     * If this Watchlist contains no movie, it will return false.
     * If the movie is successfully removed, it will return true.
     * When removing the movie, the related information of Watchlist
     * will also be updated.
     * For example, if the Movie object to be removed is the only
     * movie with English as its language, then English will no longer
     * be stored in this Watchlist object.
     *
     * @return whether the first movie is successfully removed
     */
    public boolean removeFirst(){
        // if there is no movie, then return false
        if(this.listMovie.size() == 0){
            return false;
        }
        Movie toRemove = this.listMovie.getFirst();


        if(toRemove.getValidity()){
            this.validCount = this.validCount - 1;
        }

        String rStudio = toRemove.getPublishingStudio();
        String rLanguage = toRemove.getLanguage();

        // Update the information about studios and languages

        if(this.hashMapStudio.get(rStudio)==1){
            // remove the pair if it has value 1
            this.hashMapStudio.remove(rStudio);
        }
        else{
            // reduce the value by 1 if the value is larger than 1
            Integer newCount1 = this.hashMapStudio.get(rStudio)-1;
            this.hashMapStudio.replace(rStudio,newCount1);
        }

        // similar to above
        if(this.hashMapLanguage.get(rLanguage)==1){
            this.hashMapLanguage.remove(rLanguage);
        }
        else{
            Integer newCount2 = this.hashMapLanguage.get(rLanguage)-1;
            this.hashMapLanguage.replace(rLanguage,newCount2);
        }

        // remove the first movie of the linked list
        this.listMovie.removeFirst();

        return true;
    }

    // java docstrings
    /**
     * Return a deep copy of all Movies stored in this Watchlist.
     * The user's action to the copy of Movies will not affect
     * the original Movies.
     *
     * @return a deep-copied linked list of Movies stored in this Watchlist
     */
    public LinkedList<Movie> accessAll(){
        int size = this.listMovie.size();
        LinkedList<Movie> copyMovies = new LinkedList<Movie>();
        for(int i=0;i<size;i++){
            copyMovies.add(new Movie(this.listMovie.get(i)));
        }

        return copyMovies;
        //unmodifiable list could be better
    }

    /**
     * Return the number of valid Movies in this Watchlist.
     *
     * @return the number of valid Movies
     */
    public int getValidCount(){
        return this.validCount;
    }



}
