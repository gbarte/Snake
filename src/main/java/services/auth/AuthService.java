package services.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import services.db.UsersTableHandler;

/**
 * The class that handles the logics for the authentication.
 */
public class AuthService {

    private static final int MIN_PASS_LENGTH = 6;

    private transient UsersTableHandler tableHandler;

    public UsersTableHandler getTable() {
        return this.tableHandler;
    }

    private void setTable(UsersTableHandler tableHandler) {
        this.tableHandler = tableHandler;
    }

    public AuthService(boolean isTest) {
        tableHandler = new UsersTableHandler(isTest);
    }

    public AuthService() {
        tableHandler = new UsersTableHandler(false);
    }

    /**
     * Authenticates user with provided username and password.
     * @param username - username of the player.
     * @param password - password of the player.
     * @return AuthResponse that defines result of the authentication.
     */
    public AuthResponse auth(String username, String password) {

        Map<String, String> user = tableHandler.getUser(username);

        if (user == null) {
            return AuthResponse.WRONG_PASSWORD;
        }

        String hash = getPasswordHash(password);
        if (user.containsKey("hash") && user.get("hash").equals(hash)) {
            return AuthResponse.SUCCESS;
        } else {
            return AuthResponse.WRONG_PASSWORD;
        }

    }


    /**
     * Registers a new user in the database while checking for basic formatting.
     * @param username - username of the player.
     * @param password - password of the player.
     * @return RegistrationResponse that defines result of the registration.
     */
    public RegistrationResponse register(String username, String password) {

        if (password.length() < MIN_PASS_LENGTH) {
            return RegistrationResponse.SHORT_PASSWORD;
        }

        if (tableHandler.getUser(username) != null) {
            return RegistrationResponse.OCCUPIED_NAME;
        }

        String hash = getPasswordHash(password);
        tableHandler.createUser(username, hash);
        return RegistrationResponse.SUCCESS;
    }


    private static String getPasswordHash(String password) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(password.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
