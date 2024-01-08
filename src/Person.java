import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Person {
    String name;

    Date birthDate;
    public static User signup(ArrayList<User> users){
        System.out.print("enter your username:");
        Scanner scanner=new Scanner(System.in);
        String username=scanner.nextLine();
        System.out.print("enter your Password:");
        String password=scanner.nextLine();
        User user=new User();
        user.UserName=username;
        user.Password=password;
        user.act=UserAct.Member;
        users.add(user);
        return user;
    }
    public static User login(ArrayList<User> users,String userName,String password){
        for (User user:users) {
            if (user.UserName.equals(userName) && user.Password.equals(password)){
                UserAct act=user.act;
                return user;

            }
        }
        return null;
    }

}
