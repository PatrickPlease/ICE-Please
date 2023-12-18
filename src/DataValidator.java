import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    public static boolean isUsernameValid(String username) {
        if(username == null || username.length() < 4 || username.length() > 20 ||  username.equals("")) {
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isPasswordValid(String password) {
        if(password == null || password.length() < 4 || password.length() > 20 ||  password.equals("")) {
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}