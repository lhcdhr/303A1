import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class stores some necessary information about a movie,
 * including its storing path, title, language, publishing studio,
 * validity, format, and some custom information.
 * @author Haochen Liu
 * @version 1.0
 */


public class Movie{

    private enum Format{ MP4, MOV, WMV, AVI, FLV, MKV }

    private boolean validity;
    //final
    private Format format;

    final private String path;
    private String title;
    private String language;
    private String publishingStudio;
    private HashMap<String, String> customInfo;
    private static LinkedList<String> existedPath = new LinkedList<String>();

    /**
     * Constructor of Movie class.
     * Each Movie object has a path that points to a unique file.
     * The path cannot be changed after the creation of this movie object.
     * No Movie objects will point to a same file.
     * The format of the file can only be one of MP4, MOV, WMV, AVI, FLV,and MKV,
     * cases of letters in the path does not matter.
     * The movie is valid if the path points to a actual file.
     * The
     *
     * @param mPath the path pointing to where the movie file is stored
     * @param mTitle the title of the movie
     * @param mLanguage the language of the movie
     * @param mStudio the publishing studio of the movie
     */
    public Movie(String mPath, String mTitle, String mLanguage, String mStudio){

        /*
        * Get the format of the movie file based on the input path, and convert
        * all lower case characters to upper case.
        * */
        String mFormat = mPath.substring(mPath.length()-3).toUpperCase();

        // Check if the file is one of the 6 restricted formats.
        boolean formatCheck = false;
        for(int i=0;i<6;i++){

            // Once the format matches, set the format of the object.
            if(Format.values()[i].name().equals(mFormat)){
                formatCheck = true;
                this.format = Format.values()[i];
                break;
            }
        }
        // Movie object with invalid format should not be created.
        if(!formatCheck){
            throw new IllegalArgumentException("Unable to create the movie due to invalid file type.");
        }

        // No movie objects shall share the same path.
        File temp = new File(mPath);
        for(int i=0;i<Movie.existedPath.size();i++){
            File usedFile = new File(Movie.existedPath.get(i));
            if(temp.compareTo(usedFile)==0){
                throw new IllegalArgumentException("Unable to create the movie. This file has been assigned to another movie.");
            }
        }

        // Set the path.
        this.path = mPath;

        // Save the used path to existedPath.
        String ePath = mPath;
        Movie.existedPath.add(ePath);

        // Check and set the validity of the object.
        File movie = new File(mPath);
        this.validity = movie.exists();

        // Set the required information
        this.title = mTitle.toLowerCase();
        this.language = mLanguage.toLowerCase();
        this.publishingStudio = mStudio.toLowerCase();

        // Create a hashmap to store the custom information in key-value pairs.
        this.customInfo = new HashMap<String,String>();

        // Store each movie object in Library.
        Library.addMovie(this);

    }

    /**
     * This constructor will deep-copy the input Movie object.
     * Users will have access to all the movies stored in a watchlist,
     * so it is necessary to prevent them from changing the information of the
     * Movie object by mistake.
     *
     * @param toCopy the Movie object to be deep-copied
     */
    public Movie(Movie toCopy){
        this.validity = toCopy.validity;
        this.path = toCopy.path;
        String cFormat = this.path.substring(this.path.length()-3).toUpperCase();
        for(int i=0;i<6;i++){
            // Once the format matches, set the format of the object.
            if(Format.values()[i].name().equals(cFormat)){
                this.format = Format.values()[i];
                break;
            }
        }
        this.title = toCopy.title;
        this.language = toCopy.language;
        this.publishingStudio = toCopy.publishingStudio;
        this.customInfo = (HashMap<String, String>) toCopy.customInfo.clone();

    }

    /**
     * The validity of a Movie object is based on whether the file exists.
     * If the underlying file disappears, it becomes invalid.
     * If the file is restored, it becomes valid.
     *
     */
    public void updateValidity(){
        File toCheck = new File(this.path);
        this.validity = toCheck.exists();
    }

    /**
     * Return the current validity of this Movie object.
     *
     * @return the current validity
     */
    public boolean getValidity(){
        return this.validity;
    }

    /**
     * Return the title of the Movie Object.
     *
     * @return the current title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Return the language of the Movie Object.
     *
     * @return the current language
     */
    public String getLanguage(){
        return this.language;
    }

    /**
     * Return the publishing studio of the Movie Object.
     *
     * @return the current publishing studio
     */
    public String getPublishingStudio(){
        return this.publishingStudio;
    }



    /**
     * Add or change custom information.
     * If the key does not exist, then it will create a new pair;
     * If the key exist, the older value will be overwritten.
     */
    public void updateCustomInfo(String key, String value) {
        this.customInfo.put(key,value);
    }

    /**
     * Remove a custom information based on the key.
     */
    public void removeCustomInfo(String key){
        if(this.customInfo.containsKey(key)){
            this.customInfo.remove(key);
        }
        else{
            System.out.println("The information does not exist!");
        }
    }

    /**
     * Return a copy of the hashmap that stores all the custom information about a Movie object.
     * HashMap<>.clone() is a shallow copy, but the key and value stored in it are String,
     * so the changing the returned hashmap will not affect the one stored in this Movie object.
     *
     * @return the copy of the hashmap that contains all custom information
     */
    public HashMap<String,String> getCustomInfo(){

        return (HashMap<String, String>) this.customInfo.clone();
    }

}
