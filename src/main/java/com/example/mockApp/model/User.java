    package com.example.mockApp.model;

    import jakarta.validation.constraints.NotEmpty;
    import lombok.*;

    import java.text.SimpleDateFormat;
    import java.util.Date;

    @Data
    @NoArgsConstructor
    @Getter
    @Setter
    public class User {
        @NotEmpty(message = "Необходимо ввести Login")
        private String login;
        @NotEmpty(message = "Необходимо ввести Password")
        private String password;
        private String date;
    }
