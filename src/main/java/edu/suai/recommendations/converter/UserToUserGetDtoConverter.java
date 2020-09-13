package edu.suai.recommendations.converter;

import edu.suai.recommendations.dto.get.UserGetDto;
import edu.suai.recommendations.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToUserGetDtoConverter extends AbstractConverter<User, UserGetDto> {

    @Override
    protected UserGetDto convert(User user) {
        if (user == null){
            return null;
        }
        return UserGetDto.builder()
                .email(user.getEmail())
                .login(user.getLogin())
                .build();
    }
}
