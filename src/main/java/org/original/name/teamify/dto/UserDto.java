package org.original.name.teamify.dto;

import lombok.Getter;
import lombok.Setter;
import org.original.name.teamify.model.User;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserDto {
    public static UserDto ofUser(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String location;
    private boolean lookingForTeam;
}
