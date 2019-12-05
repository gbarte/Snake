package auth;

import db.UsersTable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class AuthService {

    private static final int MIN_PASS_LENGTH = 6;

    private UsersTable table;

    public UsersTable getTable() {
        return this.table;
    }

    private void setTable(UsersTable table) {
        this.table = table;
    }

    public AuthService() {
        table = new UsersTable();
        table.connect();
    }

    public AuthService(String tableName) {
        table = new UsersTable();
        table.connect(tableName);
    }

    /**
     * Authenticates user with provided username and password.
     * @param username - username of the player.
     * @param password - password of the player.
     * @return AuthResponse that defines result of the authentication.
     */
    public AuthResponse auth(String username, String password) {

        assert username != null;
        assert password != null;

        Map<String, String> user = table.getUser(username);

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

        assert username != null;
        assert password != null;

        if (password.length() < MIN_PASS_LENGTH) {
            return RegistrationResponse.SHORT_PASSWORD;
        }

        if (table.getUser(username) != null) {
            return RegistrationResponse.OCCUPIED_NAME;
        }

        String hash = getPasswordHash(password);
        table.createUser(username, hash);
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
