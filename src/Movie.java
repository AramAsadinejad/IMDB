import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Movie {
    String name;
    ArrayList<Director> directors;
    Date releaseDate;
    ArrayList<ActorAct> actors;
    ArrayList<Reviews> reviews;
    ArrayList<Suggestion> suggestions;
    ArrayList<Writer> writers;
    Genre genre;
    String language;

    public void rate(ArrayList<Rate> rates,User user,String num){
        Rate rate=new Rate();
        rate.movie=this;
        rate.member=user;
        if(num.equals("1")){
                    rate.star=ReteNum.one;
                }
                else if(num.equals("2")){
                    rate.star=ReteNum.two;
                }
                else if(num.equals("3")){
                    rate.star=ReteNum.three;
                }
                else if(num.equals("4")){
                    rate.star=ReteNum.four;
                }
                else if(num.equals("5")){
                    rate.star=ReteNum.five;
                }

        rates.add(rate);
        System.out.println("done!");

    }
    public static Movie search(ArrayList<Movie> movies,String name){
        for (Movie movie:
                movies) {
            if (name.equals(movie.name)){
                return movie;
            }
        }
        return null;
    }

    public Director searchd(String name){
        for (Director director:
                this.directors) {
            if (name.equals(director.name)){
                return director;
            }
        }
        return null;
    }
    public Actor searcha(String name){
        for (ActorAct actor:
                this.actors) {
            if (name.equals(actor.actor.name)){
                return actor.actor;
            }
        }
        return null;
    }
    public Reviews searchr(User user){
        for (Reviews review:
             this.reviews) {
            if(user==review.writer) {
                return review;

            }
        }
        return null;
    }
    public void editMovie(User user,String mode) {
        if (user.act == UserAct.Admin) {
            Scanner scanner=new Scanner(System.in);
            switch (mode){
                case "name":
                    System.out.println("say the new name");
                    String name=scanner.nextLine();
                    this.name=name;
                    break;
                case "director":
                    System.out.println("say the old director name u wanna change");
                    String director =scanner.nextLine();
                    System.out.println("say the director info u wanna change(name,birth date)");
                    String dmode=scanner.nextLine();
                    switch (dmode){
                        case "name":
                            System.out.println("say the new director name");
                            String dname=scanner.nextLine();

                            this.searchd(director).name=dname;
                            break;

                        case "birth date":
                            System.out.println("say the new birth date in this format(yyyy/MM/dd)");
                            String dbirth=scanner.nextLine();
                            try {
                                Date newBirth = new SimpleDateFormat("yyyy/MM/dd").parse(dbirth);
                                this.searchd(director).birthDate=newBirth;
                            }
                            catch(Exception error){
                                System.out.println("wrong format");
                            }

                    }
                case "actors":
                    System.out.println("say the actor name");
                    String actor =scanner.nextLine();
                    System.out.println("say the director info u wanna change(name,birth date,");
                    String amode=scanner.nextLine();
                    switch (amode){
                        case "name":
                            System.out.println("say the new actor name");
                            String aname=scanner.nextLine();
                            this.searcha(actor).name=aname;
                            break;

                        case "birth date":
                            System.out.println("say the new birth date in this format(yyyy/MM/dd)");
                            String abirth=scanner.nextLine();
                            try {
                                Date newBirth = new SimpleDateFormat("yyyy/MM/dd").parse(abirth);
                                this.searcha(actor).birthDate=newBirth;
                            }
                            catch(Exception error){
                                System.out.println("wrong format");
                            }
                            break;

                    }
                case "writers":
                    System.out.println("say the writer name");
                    String writer =scanner.nextLine();
                    System.out.println("say the director info u wanna change(name,birth date,");
                    String rmode=scanner.nextLine();
                    switch (rmode){
                        case "name":
                            System.out.println("say the new actor name");
                            String rname=scanner.nextLine();
                            this.searcha(writer).name=rname;
                            break;

                        case "birth date":
                            System.out.println("say the new birth date in this format(yyyy/MM/dd)");
                            String rbirth=scanner.nextLine();
                            try {
                                Date newBirth = new SimpleDateFormat("yyyy/MM/dd").parse(rbirth);
                                this.searcha(writer).birthDate=newBirth;
                            }
                            catch(Exception error){
                                System.out.println("wrong format");
                            }
                            break;

                    }
                case "genre":
                    System.out.println("whats new genre?");
                    String ngenre=scanner.nextLine();
                    this.genre.genre=ngenre;
                    break;
                case "language":
                    System.out.println("whats new language?");
                    String nlanguage=scanner.nextLine();
                    this.language=nlanguage;
                    break;
                case "release date":
                    System.out.println("say the new date in this format(yyyy/MM/dd)");
                    String date=scanner.nextLine();
                    try {
                        Date ndate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
                        this.releaseDate=ndate;
                    }
                    catch(Exception error){
                        System.out.println("wrong format");
                    }
                    break;
                case "reviews":
                    System.out.println("say the username u want to edit his/her reviews about this movie");
                    String input=scanner.nextLine();
                    User userR = User.search(Main.users,input);
                    System.out.println("say the new text");
                    String ntext=scanner.nextLine();
                    this.searchr(userR).text=ntext;
                    break;

                default:
                    System.out.println("not valid");
            }

        } else {
            System.out.println("You do not have permission to edit movies.");
        }
    }
    public void deleteMovie(ArrayList<Movie> movies,User user){
        if (user.act==UserAct.Admin){
            movies.remove(this);
        }
    }
    public static void addMovie(ArrayList<Movie> movies){
        System.out.println("say the movie name");
        Scanner scanner=new Scanner(System.in);
        String Mname=scanner.nextLine();
        Movie movie=new Movie();
        movie.name=Mname;
        movie.reviews=new ArrayList<>();
        movie.writers=new ArrayList<>();
        movie.suggestions=new ArrayList<>();
        movie.directors=new ArrayList<>();
        movie.actors=new ArrayList<>();
        movies.add(movie);

    }
}
