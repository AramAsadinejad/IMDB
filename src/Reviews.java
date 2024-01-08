import java.util.ArrayList;
import java.util.Date;

public class Reviews {
    User writer;
    Date date;
    String text;
    ArrayList<User> helpfull;
    ArrayList<User> notHelpfull;
    public void deleteReviews(User user,ArrayList<Reviews> reviews){
        if (user.act==UserAct.Admin){
            reviews.remove(this);
        }
    }
    public void rateReviews(User user,String mode){
        if(mode.equals("helpfull")){
            helpfull.add(user);
        }
        else if(mode.equals("not helpfull")){
            notHelpfull.add(user);
        }

    }
}
