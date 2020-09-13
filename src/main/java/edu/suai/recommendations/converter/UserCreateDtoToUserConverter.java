package edu.suai.recommendations.converter;

import edu.suai.recommendations.common.RolesEnum;
import edu.suai.recommendations.dto.create.UserCreateDto;
import edu.suai.recommendations.model.Role;
import edu.suai.recommendations.model.User;
import edu.suai.recommendations.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class UserCreateDtoToUserConverter extends AbstractConverter<UserCreateDto, User> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    @Override
    protected User convert(UserCreateDto userCreateDto) {
        if (userCreateDto == null) {
            return null;
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByTitle(RolesEnum.USER.getTitle()));
        return User.builder()
                .email(userCreateDto.getEmail())
                .login(userCreateDto.getLogin())
                .password(bCryptPasswordEncoder.encode(userCreateDto.getPassword()))
                .userName(userCreateDto.getUserName())
                .roles(roles)
                .build();
    }
}
