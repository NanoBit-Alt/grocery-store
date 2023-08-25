package com.market.domain.user;

import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ApplicationScoped
public class UserService {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";


    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserUseCase userUseCase;

    @Inject
    public UserService(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Transactional
    public String create(UserView userView) {
        if (userUseCase.exists(userView.email())) {
            return "email is already being used";
        } else if (!isPasswordValid(userView.password())) {
            return "invalid password";
        } else {
            User user = new User();
            user.setCreatedAt(Instant.now());
            user.setUserName(userView.userName());
            user.setEmail(userView.email());
            user.setPassword(encryptPassword(userView.password()));
            user.setAuthorityLevel(1);
            userUseCase.create(user);
            return "user created";
        }
    }

    @Transactional
    public String delete(long id) {
        userUseCase.delete(id);
        return null;
    }

    public List<User> listByPage(int index) {
        return userUseCase.listByPage();
    }

    public User findById(long id) {
        return userUseCase.findById(id);
    }

    public User findByEmail(String email) {
        return userUseCase.findByEmail(email);
    }

    public String update(UserView userView) {
        User user = userUseCase.findByEmail(userView.email());
        user.setUserName(userView.userName());
        user.setEmail(userView.email());
        user.setPassword(userView.password());
        return userUseCase.update(user);
    }

    public static boolean isPasswordValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}