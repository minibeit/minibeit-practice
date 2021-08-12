package com.miniprac.config.jpa;


import com.miniprac.security.userdetails.CustomUserDetails;
import com.miniprac.user.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter((authentication) -> !(authentication instanceof AnonymousAuthenticationToken))
                .map(Authentication::getPrincipal)
                .map(CustomUserDetails.class::cast)
                .map(CustomUserDetails::getUser);
    }
}
