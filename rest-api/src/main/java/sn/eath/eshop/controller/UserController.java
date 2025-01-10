package sn.eath.eshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import sn.eath.eshop.dto.AuthenticationRequestDto;
import sn.eath.eshop.dto.AuthenticationResponseDto;
import sn.eath.eshop.dto.UserDto;
import sn.eath.eshop.entity.User;
import sn.eath.eshop.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Enregistre un nouvel utilisateur dans le système.
     *
     * @param user les informations de l'utilisateur à enregistrer
     * @return une {@link ResponseEntity} contenant le DTO de l'utilisateur enregistré
     *         et un statut HTTP CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody User user) {
        UserDto userDto = userService.registerUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    /**
     * Authentifie un utilisateur à l'aide de ses informations d'identification.
     *
     * @param authenticationRequest un objet contenant l'email et le mot de passe de l'utilisateur
     * @return une {@link ResponseEntity} contenant un DTO avec le token d'authentification
     *         et un statut HTTP OK
     * @throws Exception en cas d'échec de l'authentification
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> loginUser(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {
        AuthenticationResponseDto authenticationResponse = userService.loginUser(authenticationRequest, authenticationManager);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    /**
     * Récupère un utilisateur par son ID.
     *
     * @param userId l'identifiant unique de l'utilisateur
     * @return une {@link ResponseEntity} contenant le DTO de l'utilisateur et un statut HTTP OK
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * Récupère un utilisateur par son adresse e-mail.
     *
     * @param email l'adresse e-mail de l'utilisateur
     * @return une {@link ResponseEntity} contenant le DTO de l'utilisateur et un statut HTTP OK
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}