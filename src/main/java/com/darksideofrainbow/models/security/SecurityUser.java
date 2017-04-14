package com.darksideofrainbow.models.security;

import com.darksideofrainbow.models.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityUser extends ApplicationUser implements UserDetails {

    public SecurityUser(ApplicationUser user) {
        if (user != null) {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setIsAdmin(user.getIsAdmin());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        authorities.add(authority);
        if (this.getIsAdmin()) {
            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ADMIN");
            authorities.add(adminAuthority);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
