package cringe.back.util;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
    private static final String SALT = BCrypt.gensalt();

    public String hash(String password) {
        return BCrypt.hashpw(password, SALT);
    }
}
