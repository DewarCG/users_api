package com.nisum.users.services;

import com.nisum.config.Constants;
import com.nisum.config.JwtService;
import com.nisum.users.models.dto.PhoneRequest;
import com.nisum.users.models.dto.PhoneResponse;
import com.nisum.users.models.dto.UserRequest;
import com.nisum.users.models.dto.UserResponse;
import com.nisum.users.models.entities.Phone;
import com.nisum.users.models.entities.User;
import com.nisum.users.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Value("${users.password_regex}")
    private String passwordPattern;

    public UserResponse createUser(UserRequest userRequest) {
        validateUserRequest(userRequest);
        User user = getUserEntity(userRequest);

        List<Phone> phones = userRequest.getPhones().
                stream().map(this::convertToPhone).collect(Collectors.toList());
        user.setPhones(phones);

        String token = generateToken(user.getName(), user.getId().toString());
        user.setToken(token);

        userRepository.save(user);

        return getUserResponse(user);
    }

    private UserResponse getUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getId().toString());
        userResponse.setModified(user.getModified());
        userResponse.setToken(user.getToken());
        userResponse.setCreated(user.getCreated());
        userResponse.setActive(user.getActive());

        List<PhoneResponse> phoneRespList = user.getPhones().
                stream().map(this::convertToPhoneResponse).collect(Collectors.toList());
        userResponse.setPhones(phoneRespList);
        return userResponse;
    }

    private void validateUserRequest(UserRequest userRequest) {
        if (!Pattern.matches(passwordPattern, userRequest.getPassword())) {
            throw new IllegalArgumentException("Formato de contraseña incorrecto");
        }
        if (!Pattern.matches(Constants.EMAIL_REGEX, userRequest.getEmail())) {
            throw new IllegalArgumentException("Formato de correo incorrecto");
        }
        if (existsUserByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Correo ya registrado");
        }
        if (userRequest.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de usuario obligatorio");
        }
    }

    private String generateToken(String subject, String id) {
        Claims claims = Jwts.claims()
                .setSubject(subject)
                .setAudience("web")
                .setId(id);
        return jwtService.generateToken(claims);
    }

    private User getUserEntity(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail().toLowerCase());
        LocalDate now = LocalDate.now();
        user.setCreated(now);
        user.setModified(now);
        user.setActive(true);
        user.setName(userRequest.getName().toLowerCase());
        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return user;
    }

    public boolean existsUserByEmail(String email) {
        String emailFinal = email.toLowerCase().trim();
        return userRepository.existsUserByEmail(emailFinal);
    }

    public Phone convertToPhone(PhoneRequest phoneRequest) {
        Phone phone = new Phone();
        phone.setId(UUID.randomUUID());
        phone.setNumber(phoneRequest.getNumber().trim());
        phone.setCityCode(phoneRequest.getCitycode().trim());
        phone.setCountryCode(phoneRequest.getCitycode().trim());
        return phone;
    }

    public PhoneResponse convertToPhoneResponse(Phone phoneEntity) {
        PhoneResponse phone = new PhoneResponse();
        phone.setId(phoneEntity.getId().toString());
        phone.setNumber(phoneEntity.getNumber());
        phone.setCityCode(phoneEntity.getCityCode());
        phone.setCountryCode(phoneEntity.getCountryCode());
        return phone;
    }
}
