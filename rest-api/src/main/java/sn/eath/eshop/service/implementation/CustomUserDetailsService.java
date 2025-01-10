package sn.eath.eshop.service.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.eath.eshop.dto.AuthenticationRequestDto;
import sn.eath.eshop.dto.AuthenticationResponseDto;
import sn.eath.eshop.dto.UserDto;
import sn.eath.eshop.entity.User;
import sn.eath.eshop.exception.user.*;
import sn.eath.eshop.mapper.UserMapper;
import sn.eath.eshop.repository.UserRepository;
import sn.eath.eshop.security.JwtUtil;
import sn.eath.eshop.service.UserService;

import java.util.ArrayList;

/**
 * Service d'implémentation des fonctionnalités liées à la gestion et à l'authentification des utilisateurs.
 * <p>
 * Cette classe implémente {@link UserService} et {@link UserDetailsService}.
 * Elle fournit des méthodes pour enregistrer, récupérer, et authentifier les utilisateurs,
 * ainsi que pour générer des jetons JWT sécurisés.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Constructeur injectant les dépendances nécessaires.
     *
     * @param userRepository   le repository pour la gestion des utilisateurs
     * @param userMapper       le mapper pour convertir les entités utilisateur en DTO
     * @param passwordEncoder  l'encodeur de mot de passe
     * @param jwtUtil          l'utilitaire pour la gestion des jetons JWT
     */
    public CustomUserDetailsService(UserRepository userRepository, UserMapper userMapper,
                                    PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Charge un utilisateur par son nom d'utilisateur (adresse e-mail).
     *
     * @param username l'adresse e-mail de l'utilisateur
     * @return un objet {@link UserDetails} contenant les informations de l'utilisateur
     * @throws UsernameNotFoundException si aucun utilisateur correspondant n'est trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UserNotFoundException("Utilisateur non trouvé avec l'email : " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getMotDePasse(), new ArrayList<>());
    }

    /**
     * Récupère les informations d'un utilisateur à partir de son adresse e-mail.
     *
     * @param email l'adresse e-mail de l'utilisateur
     * @return un {@link UserDto} contenant les informations de l'utilisateur
     * @throws UserGetException si aucun utilisateur correspondant n'est trouvé
     */
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserGetException("Utilisateur non trouvé pour l'email : " + email);
        }
        return userMapper.userToUserDto(user);
    }

    /**
     * Récupère les informations d'un utilisateur à partir de son identifiant unique.
     *
     * @param userId l'identifiant unique de l'utilisateur
     * @return un {@link UserDto} contenant les informations de l'utilisateur
     * @throws UserNotFoundException si aucun utilisateur correspondant n'est trouvé
     */
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Utilisateur non trouvé pour l'ID : " + userId)
        );
        return userMapper.userToUserDto(user);
    }

    /**
     * Enregistre un nouvel utilisateur après validation des données fournies.
     *
     * @param user l'entité utilisateur à enregistrer
     * @return un {@link UserDto} représentant l'utilisateur enregistré
     * @throws UserConflictException si un utilisateur avec le même email existe déjà
     * @throws UserInvalidModelException si les données de l'utilisateur sont invalides
     * @throws UserSaveException si une erreur survient lors de l'enregistrement
     */
    @Override
    public UserDto registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserConflictException("Un utilisateur avec cet email existe déjà.");
        }

        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new UserInvalidModelException("L'email fourni est invalide.");
        }

        if (user.getMotDePasse() == null || user.getMotDePasse().length() < 6) {
            throw new UserInvalidModelException("Le mot de passe doit comporter au moins 6 caractères.");
        }

        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserSaveException("Erreur lors de l'enregistrement de l'utilisateur.");
        }

        return userMapper.userToUserDto(user);
    }

    /**
     * Authentifie un utilisateur en vérifiant ses informations d'identification
     * et génère un jeton JWT sécurisé.
     *
     * @param authenticationRequest les informations d'identification de l'utilisateur
     * @param authenticationManager le gestionnaire d'authentification
     * @return un {@link AuthenticationResponseDto} contenant le {@link UserDto} et le jeton JWT
     * @throws UserInvalidModelException si les données de connexion sont invalides
     * @throws UserNotFoundException si aucun utilisateur correspondant n'est trouvé
     * @throws Exception si une erreur survient lors de l'authentification
     */
    public AuthenticationResponseDto loginUser(AuthenticationRequestDto authenticationRequest, AuthenticationManager authenticationManager) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.motDePasse())
        );

        UserDetails userDetails = loadUserByUsername(authenticationRequest.email());
        String jwt = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByEmail(authenticationRequest.email());
        if (user == null) {
            throw new UserNotFoundException("Utilisateur non trouvé avec l'email : " + authenticationRequest.email());
        }

        UserDto userDto = userMapper.userToUserDto(user);
        return new AuthenticationResponseDto(userDto, jwt);
    }
}