import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Main {
    static ArrayList<User> users=new ArrayList<>();
    static ArrayList<Movie> movies=new ArrayList<>();
    static ArrayList<Rate> rates=new ArrayList<>();
    static ArrayList<Celebritie> celebrities=new ArrayList<>();
    static boolean run=true;
    public static void setDB(ArrayList<User> users,ArrayList<Movie> movies,ArrayList<Rate> rates,ArrayList<Celebritie> celebrities){
        User user=new User();
        user.UserName="aram";
        user.Password="1234";
        user.act=UserAct.Editor;
        users.add(user);
        User user1=new User();
        user1.UserName="erfan";
        user1.Password="1234";
        user1.act=UserAct.Member;
        users.add(user1);
        Movie movie=new Movie();
        movie.name="harry poter";
        movie.genre=new Genre();
        movie.genre.genre="action";
        movie.language="english";
        Actor actor = new Actor();
        actor.name="daniel radclieff";
        try {
            actor.birthDate=new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2014");
        }
        catch (Exception e){

        }
        celebrities.add(actor);
        ActorAct mact=new ActorAct();
        mact.actor=actor;
        mact.act="main role";
        movie.actors=new ArrayList<>();
        movie.actors.add(mact);
        Director director1=new Director();
        director1.name="mike newvel";
        try {
            director1.birthDate=new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1997");
        }
        catch (Exception e){

        }
        movie.directors=new ArrayList<>();
        movie.directors.add(director1);
        Director director2=new Director();
        director2.name="chris columbus";
        movie.directors.add(director2);
        movie.reviews=new ArrayList<>();
        Reviews review=new Reviews();
        review.writer=users.get(1);
        review.date=new Date();
        review.text="great job";
        review.helpfull=new ArrayList<>();
        review.notHelpfull=new ArrayList<>();
        movie.reviews.add(review);
        movie.suggestions=new ArrayList<>();
        movie.writers=new ArrayList<>();
        movies.add(movie);
        Movie movie2=new Movie();
        movie2.name="lord of the rings";
        Actor actor21 = new Actor();
        actor21.name="elijah wood";
        try {
            actor21.birthDate=new SimpleDateFormat("dd/MM/yyyy").parse("12/10/1990");
        }
        catch (Exception e){

        }
        ActorAct mact21=new ActorAct();
        mact21.actor=actor21;
        mact21.act="main role";
        movie2.actors=new ArrayList<>();
        movie2.actors.add(mact21);
        celebrities.add(director1);
        celebrities.add(director2);
        celebrities.add(actor21);
        movies.add(movie2);
    }

    public static void main(String[] args) {
        //defining a superuser(admin) first
        //starting system:
        users.add(0, new User());
        users.get(0).UserName="superuser";
        users.get(0).Password="1234";
        users.get(0).act=UserAct.Admin;
        setDB(users,movies,rates,celebrities);
        User user=users.get(0);
        System.out.println("1)signup 2)login");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.nextLine();
        switch (input){
            case "1":
                user=Person.signup(users);
                break;
            case "2":
                System.out.println("username:");
                String username=scanner.nextLine();
                System.out.println("password:");
                String password=scanner.nextLine();
                user=Person.login(users,username,password);
                if (user==null){
                    System.out.println("wrong username or password");
                }
                break;
            default:
                System.out.println("not valid");
                break;
        }
        while (run){
            if (user!=null){
                if(user.act==UserAct.Admin){
                    user.AdminPannel(users,movies);
                }
                else if(user.act==UserAct.Editor){
                    user.EditorPannel(movies);
                } else if (user.act==UserAct.Member) {
                    user.MemberPannel(users,movies,user.playlists);

                }
            }
        }



    }
}