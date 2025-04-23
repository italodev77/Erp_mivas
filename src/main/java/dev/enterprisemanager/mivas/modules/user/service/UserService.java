package dev.enterprisemanager.mivas.modules.user.service;

import dev.enterprisemanager.mivas.modules.user.entity.User;
import dev.enterprisemanager.mivas.modules.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
