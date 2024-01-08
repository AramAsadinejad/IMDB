import java.util.ArrayList;

public class Playlist {
    String name;
    ArrayList<Movie> movies;
    public static Playlist search(ArrayList<Playlist> playlists,String name){
        for (Playlist playlist:
             playlists) {
            if(playlist.name==name){
                return playlist;
            }

        }
        return null;
    }
}
