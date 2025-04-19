package dev.enterprisemanager.mivas.config.security;

import dev.enterprisemanager.mivas.modules.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final IUserRepository iuserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iuserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}