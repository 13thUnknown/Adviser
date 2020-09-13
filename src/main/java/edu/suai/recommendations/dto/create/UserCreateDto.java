package edu.suai.recommendations.dto.create;

import lombok.*;

import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDto {

    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$")
    private String login;

    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    private String password;

    private String userName;

}
