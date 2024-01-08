import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User{
    String UserName;
    String Password;
    UserAct act;
    ArrayList<Movie> watchedMovies;
    ArrayList<Movie> recommendations;
    ArrayList<User> followings;
    ArrayList<Celebritie> followedCellebririe;
    ArrayList<Playlist> playlists;
    public static User search(ArrayList<User> users,String userName){
        for (User user:
             users) {
            if (userName.equals(user.UserName)){
                return user;
            }
        }
        return null;
    }
    public void watch(Movie movie){
        this.watchedMovies.add(movie);
        for (Movie movie1:
             Main.movies) {
            if (movie1.genre==movie.genre){
                this.recommendations.add(movie1);
            }

        }

    }
    public static User update(User user){
        System.out.println("what do u want to update?\n1)username\n2)password");
        Scanner input=new Scanner(System.in);
        String updateKey=input.nextLine();
        switch (updateKey){
            case "1":
                System.out.println("enter ur new username");
                String neww=input.nextLine();
                user.UserName=neww;
                break;
            case "2":
                System.out.println("enter ur old password");
                String old=input.nextLine();
                if(old.equals(user.Password)){
                    System.out.println("enter ur nnew pass");
                    String newwpass=input.nextLine();
                    user.UserName=newwpass;
                    break;
                }else {
                    System.out.println("wrong pass");
                }

        }
        return user;

    }
    public static void deleteUser(ArrayList<User> users, User admin,User user){
        if (user.act!=UserAct.Admin){
            users.remove(user);
        }
    }
    public void EditorPannel(ArrayList<Movie> movies){
        if (this.act==UserAct.Editor){
            Scanner scanner=new Scanner(System.in);
            System.out.println("do u want to suggest?y/n");
            String input=scanner.nextLine();
            switch (input){
                case "y":
                    this.MemberPannel(Main.users,movies,this.playlists);
                    break;
                case "n":
                    System.out.println("what movie do u want to suggest?if u wanna exit say exit");
                    String movieName=scanner.nextLine();
                    if (movieName.equals("exit")){
                        Main.run=false;
                        return;
                    }
                    Movie movie=Movie.search(movies,movieName);
                    System.out.println("what's ur suggestion?");
                    String suggest=scanner.nextLine();
                    Suggestion suggestion=new Suggestion();
                    suggestion.text=suggest;
                    suggestion.sender=this;
                    movie.suggestions.add(suggestion);
            }

        }
    }
    public void MemberPannel(ArrayList<User> users,ArrayList<Movie> movies,ArrayList<Playlist> playlists){
        Scanner scanner=new Scanner(System.in);
        System.out.println("what do u want to do?1)rate a movie 2) rate a review 3)add a playlist 4)add movie to ur playlist\n" +
                "5) follow a friend 6)see ur followings infos 7)see ur favorite celebrities updates 8)follow a celebritie\n" +
                "9)see ur recommendations 10)add review to a movie 11)exit");
        String input=scanner.nextLine();
        if(input.equals("1")){
            System.out.println("tell the movie name u wanna rate");
            String movieName=scanner.nextLine();
            Movie movie=Movie.search(movies,movieName);
            System.out.println("what rate?say numeric");
            String rate=scanner.nextLine();
            try {
                movie.rate(Main.rates,this,rate);
            }catch (Exception e){
                System.out.println("not valid movie name or rate");
            }

        } else if (input.equals("2")) {
            System.out.println("tell the movie name u wanna rate it's review");
            String movieName=scanner.nextLine();
            Movie movie=Movie.search(movies,movieName);
            System.out.println("what username u wanna rate its review");
            String username=scanner.nextLine();
            User sender=User.search(users,username);
            System.out.println("helpfull or not helpfull?");
            String help=scanner.nextLine();
            movie.searchr(sender).rateReviews(this,help);
        } else if (input.equals("3")) {
            System.out.println("say ur new playlist name");
            String name=scanner.nextLine();
            Playlist newPlaylist=new Playlist();
            newPlaylist.name=name;
            playlists.add(newPlaylist);
            System.out.println("done!");

        } else if (input.equals("4")) {
            System.out.println("say ur playlist name");
            String name=scanner.nextLine();
            Playlist playlist=Playlist.search(playlists,name);
            System.out.println("say ur movie name u wanna add");
            String movieN=scanner.nextLine();
            Movie movie=Movie.search(movies,movieN);
            playlist.movies.add(movie);

        } else if (input.equals("5")) {
            System.out.println("say a username u wanna follow");
            String username=scanner.nextLine();
            User user=User.search(Main.users,username);
            this.followings.add(user);
            System.out.println("done!");

        } else if (input.equals("6")) {
            System.out.println("what username of ur following u wanna see infos?");
            String username=scanner.nextLine();
            User user=User.search(this.followings,username);

        } else if (input.equals("7")) {
            System.out.println("what celebrity name u wanna see its updates");
            String name=scanner.nextLine();
            Celebritie celebritie=Celebritie.search(Main.celebrities,name);
            celebritie.show(Main.movies);

        } else if (input.equals("8")) {
            System.out.println("what celebrity name u wanna follow");
            String name=scanner.nextLine();
            Celebritie celebritie=Celebritie.search(Main.celebrities,name);
            this.followedCellebririe.add(celebritie);
            System.out.println("done!");

        } else if (input.equals("9")) {
            for (Movie movie:
                 this.recommendations) {
                System.out.println(movie.name);

            }
        } else if (input.equals("11")) {
            Main.run = false;
        } else if (input.equals("10")) {
            System.out.println("say ur movie name u wanna add review");
            String movieN=scanner.nextLine();
            Movie movie=Movie.search(movies,movieN);
            Reviews review=new Reviews();
            review.writer = this;
            System.out.println("what do u want to write");
            String text=scanner.nextLine();
            review.text=text;
            review.date= new Date();
            movie.reviews.add(review);

        } else {
            System.out.println("invalid input");
        }
    }
    public void AdminPannel(ArrayList<User> users,ArrayList<Movie> movies){
        if(this.act==UserAct.Admin){
            Scanner scanner=new Scanner(System.in);
            System.out.println("do u want to enter admin pannel?y/n");
            String p =scanner.nextLine();
            if(p.equals("y")){
                System.out.println("what do u want to do dear admin?\n1)delete user\n2)edit user\n3)add user\n4)delete movie\n" +
                        "5)edit movie\n6)add movie\n7)see suggestions to approve or reject\n8)exit");
                String input=scanner.nextLine();
                switch (input){
                    case "1":
                        System.out.println("say the username account u want to delete");
                        String Username=scanner.nextLine();
                        deleteUser(users,this,search(users, Username));
                        break;
                    case "3":
                        Person.signup(users);
                        break;
                    case "2":
                        System.out.println("say the username account u want to edit");
                        String eusername=scanner.nextLine();
                        update(search(users,eusername));
                        break;
                    case "4":
                        System.out.println("say the movie name u want to delete");
                        String movieN=scanner.nextLine();
                        Movie movie=Movie.search(Main.movies,movieN);
                        movie.deleteMovie(Main.movies,this);
                        break;
                    case "5":
                        System.out.println("say the movie name u want to edit");
                        String emovie=scanner.nextLine();
                        Movie Emovie=Movie.search(Main.movies,emovie);
                        System.out.println("what do u want to edit?directors or actors or writers or name or genre or language");
                        String mode=scanner.nextLine();
                        Emovie.editMovie(this,mode);
                        break;
                    case "6":
                        Movie.addMovie(Main.movies);
                        break;
                    case "8":
                        Main.run=false;
                        break;
                    default:
                        System.out.println("not valid");
                        break;


                }
            }else if(p.equals("n")){
                this.MemberPannel(users,movies,this.playlists);
            }

        }

    }
}

