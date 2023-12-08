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

}