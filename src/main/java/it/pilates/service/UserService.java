package it.pilates.service;

import it.pilates.dto.CreateUserRequest;
import it.pilates.dto.UserResponse;
import it.pilates.entity.User;
import it.pilates.exception.BusinessException;
import it.pilates.exception.ResourceNotFoundException;
import it.pilates.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id " + id + " not found"));
    }

    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email.toLowerCase())
                .map(UserResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with email " + email + " not found"));
    }

    @Transactional
    public UserResponse create(CreateUserRequest request) {
        String email = request.email().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("A user with email " + email + " already exists");
        }

        User user = new User(
                request.name().trim(),
                email,
                request.password(),
                request.role()
        );

        User saved = userRepository.save(user);
        return UserResponse.from(saved);
    }
}