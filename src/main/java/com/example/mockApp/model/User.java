    package com.example.mockApp.model;

    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotEmpty;
    import lombok.*;

    import java.text.SimpleDateFormat;
    import java.util.Date;

    @Data
    @AllArgsConstructor
    @Getter
    @Setter
    public class User {
        @NotEmpty(message = "Необходимо ввести Login")
        private String login;
        @NotEmpty(message = "Необходимо ввести Password")
        private String password;
        @Email(message = "Email должен быть корректным")
        private String email;
        private Date date;
    }
