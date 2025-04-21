    package com.example.mockApp.model;

    import lombok.*;

    import java.text.SimpleDateFormat;
    import java.util.Date;

    import javax.validation.constraints.NotBlank;
    @Data
    @NoArgsConstructor
    @Getter
    @Setter
    public class User {
        @NotBlank(message = "Необходимо ввести Login")
        private String login;
        @NotBlank(message = "Необходимо ввести Password")
        private String password;
        private String date;

        public User(String login, String password){
            this.login = login;
            this.password = password;
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
    }
