package com.market.domain.user;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserUseCase {

    private UserRepository userRepository;

    @Inject
    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        userRepository.persist(user);
        return null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> listByPage() {
        return userRepository.listAll();
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }

    public User delete(long id) {
        userRepository.deleteById(id);
        return null;
    }

    public String update(User user) {
        if(!user.getEmail().equals("admin@admin.com") && userRepository.isPersistent(user)) {
            User originalUser = userRepository.findById(user.getId());
            originalUser.setUserName(user.getUserName());
            originalUser.setPassword(user.getPassword());
            return "user updated";
        }
        return "couldn't update user";
    }

    public boolean exists(String email) {
        return userRepository.findByEmail(email) != null;
    }


}