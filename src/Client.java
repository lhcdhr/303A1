

public class Client {
    public static void main(String[] args){

        // m1 has a valid file path on my laptop, so m1 is the only valid movie of the 5.
        // However, this will not work on your computer.
        // You can replace the path(s) with what you have.
        Movie m1 = new Movie("D:\\shotcut workspace\\HOI4 CAH part1.mP4","movie1","language1","studio1");
        Movie m2 = new Movie("D:\\Movies\\movie2.wmv","movie2","language2","studio2");
        Movie m3 = new Movie("D:\\Movies\\movie3.FlV","movie3","language1","studio2");
        Movie m4 = new Movie("D:\\Movies\\movie4.avi","movie4","language2","studio1");
        Movie m5 = new Movie("D:\\Movies\\movie5.avi","movie5","language2","studio1");


        m1.updateCustomInfo("actor","Jackie Chan");
        m1.updateCustomInfo("actor2","Someone Else");
        m1.updateCustomInfo("theme","action");
        m1.removeCustomInfo("actor2");

        Movie[] arrayMovie = new Movie[4];

        arrayMovie[0]=m1;
        arrayMovie[1]=m2;
        arrayMovie[2]=m3;
        arrayMovie[3]=m4;

        Watchlist w1 = new Watchlist("watchlist1", arrayMovie);



    }


}
