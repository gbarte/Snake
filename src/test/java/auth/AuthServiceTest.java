package auth;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthServiceTest {

    private AuthService service;

    @BeforeEach
    void setUp() {

        service = new AuthService("test_table");

        RegistrationResponse response = service.register("john", "12345678");
    }

    @Test
    void authFailureTest() {
        AuthResponse response = service.auth("john", "123456");
        Assertions.assertEquals(AuthResponse.WRONG_PASSWORD, response);
    }

    @Test
    void registrationShortFailureTest() {
        RegistrationResponse response = service.register("john", "123");
        Assertions.assertEquals(RegistrationResponse.SHORT_PASSWORD, response);
    }

    @Test
    void registrationSuccessTest() {
        RegistrationResponse response = service.register("sem10", "ROMAN_IS_BEST");
        Assertions.assertEquals(RegistrationResponse.SUCCESS, response);
    }

    @Test
    void authenticationSuccessTest() {
        AuthResponse authResponse = service.auth("john", "12345678");
        Assertions.assertEquals(AuthResponse.SUCCESS, authResponse);
    }

    @Test
    void authenticationFailureTest() {
        AuthResponse authResponse = service.auth("john", "123");
        Assertions.assertEquals(AuthResponse.WRONG_PASSWORD, authResponse);
    }

    @Test
    void nonExistentUserTest() {
        AuthResponse authResponse = service.auth("i_dont_exist", "12345678");
        Assertions.assertEquals(AuthResponse.WRONG_PASSWORD, authResponse);
    }

}
