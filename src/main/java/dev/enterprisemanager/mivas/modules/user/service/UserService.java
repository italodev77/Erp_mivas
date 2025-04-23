package dev.enterprisemanager.mivas.modules.user.service;

import dev.enterprisemanager.mivas.modules.user.dto.UserDTO;
import dev.enterprisemanager.mivas.modules.user.entity.User;
import dev.enterprisemanager.mivas.modules.user.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
        }

        try {
            userRepository.deleteById(id);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Erro ao deletar o usuário com ID " + id + ": " + ex.getMessage(), ex);
        }
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));

        user.setName(userDTO.name());
        user.setEmail(userDTO.email());

        if (userDTO.password() != null && !userDTO.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDTO.password()));
        }

        return userRepository.save(user);
    }



}
