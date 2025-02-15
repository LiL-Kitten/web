package cringe.back.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
