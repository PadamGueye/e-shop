package sn.eath.eshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sn.eath.eshop.dto.UserDto;
import sn.eath.eshop.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
