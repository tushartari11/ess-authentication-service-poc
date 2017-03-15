package de.vitapublic.essAuthenticationService.auth.service;

import de.vitapublic.essAuthenticationService.auth.model.User;

public interface UserService {
    void registerUser(User user);

    User findByUsername(String username);
}
