package services.auth;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AuthServiceTest {

    private transient AuthService service;

    private static final String TEST_USERNAME = "john";
    private static final String TEST_PASS = "12345678";

    @BeforeEach
    void setUp() {
        service = new AuthService(true);
        service.register(TEST_USERNAME, TEST_PASS);
    }

    @Test
    void defaultConnectTest() throws SQLException {
        service = new AuthService();
        Assertions.assertNotNull(service.getTable());
    }

    @Test
    void authFailureTest() {
        AuthResponse response = service.auth("john", "123456");
        Assertions.assertEquals(AuthResponse.WRONG_PASSWORD, response);
    }

    @Test
    void registrationShortFailureTest() {
        RegistrationResponse response = service.register(TEST_USERNAME, "123");
        Assertions.assertEquals(RegistrationResponse.SHORT_PASSWORD, response);
    }

    @Test
    void registrationOccupiedFailureTest() {
        RegistrationResponse response = service.register(TEST_USERNAME, TEST_PASS);
        Assertions.assertEquals(RegistrationResponse.OCCUPIED_NAME, response);
    }

    @Test
    void registrationSuccessTest() {
        RegistrationResponse response = service.register("sem10", "ROMAN_IS_BEST");
        Assertions.assertEquals(RegistrationResponse.SUCCESS, response);
    }

    @Test
    void authenticationSuccessTest() {
        AuthResponse authResponse = service.auth(TEST_USERNAME, TEST_PASS);
        Assertions.assertEquals(AuthResponse.SUCCESS, authResponse);
    }

    @Test
    void authenticationFailureTest() {
        AuthResponse authResponse = service.auth(TEST_USERNAME, "123");
        Assertions.assertEquals(AuthResponse.WRONG_PASSWORD, authResponse);
    }

    @Test
    void nonExistentUserTest() {
        AuthResponse authResponse = service.auth("i_dont_exist", TEST_PASS);
        Assertions.assertEquals(AuthResponse.WRONG_PASSWORD, authResponse);
    }

}
