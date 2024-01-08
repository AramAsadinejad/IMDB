import java.util.ArrayList;

public class Celebritie extends Person{
    public static Celebritie search(ArrayList<Celebritie> celebrities,String name){
        for (Celebritie celebritie:
             celebrities) {
            if(celebritie.name.equals(name)){
                return celebritie;
            }

        }
        return null;
    }

    public void show(ArrayList<Movie> movies){
        for (Movie movie:
             movies) {
            if(movie.actors.contains(this) || movie.directors.contains(this) || movie.writers.contains(this)){
                System.out.println(movie.name);
            }

        }
    }
}
