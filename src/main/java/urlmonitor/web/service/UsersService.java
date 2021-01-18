package urlmonitor.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import urlmonitor.security.UserPrincipal;
import urlmonitor.web.model.UsersModel;
import urlmonitor.web.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public final String ROLE_USER = "USER";

    public final String ROLE_ADMIN = "ADMIN";

    public UsersModel getActiveUser(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return usersRepository.findByUsername(userPrincipal.getUsername());
    }

    public boolean saveUserWithValidate(UsersModel user) {
        validateUser(user);
        if (user.getErrors() != null && user.getErrors().length() > 0) {
            return false;
        }
        if (!user.getRoles().contentEquals(ROLE_ADMIN)) {
            user.setRoles(ROLE_USER);
        }
        user.setActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }

    private void validateUser(UsersModel user) {
        user.setErrors("");

        if (!user.getPassword().contentEquals(user.getRepeatPassword())) {
            user.setErrors(user.getErrors() + "\r\n Your password and confirmation password do not match.");
        }

        UsersModel testUser = usersRepository.findByUsername(user.getUsername());
        if (testUser != null && (user.getId() == null || !user.getId().equals(testUser.getId()))) {
            user.setErrors(user.getErrors() + "\r\n User with the same username is already registered.");
        }
    }
}
