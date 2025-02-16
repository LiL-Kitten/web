package cringe.back.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
    public String hash(String password) {
        return BCrypt.hashpw(password, "SALT");
    }

    public boolean verify(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
