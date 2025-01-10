package sn.eath.eshop.service;

import org.springframework.security.authentication.AuthenticationManager;
import sn.eath.eshop.dto.AuthenticationRequestDto;
import sn.eath.eshop.dto.AuthenticationResponseDto;
import sn.eath.eshop.dto.UserDto;
import sn.eath.eshop.entity.User;

public interface UserService {
    UserDto getUserById(Long userId);

    UserDto registerUser(User user);

    AuthenticationResponseDto loginUser(AuthenticationRequestDto authenticationRequest, AuthenticationManager authenticationManager) throws Exception;

    UserDto getUserByEmail(String email);
}
