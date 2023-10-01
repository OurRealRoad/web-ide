package com.jinro.webide.login.config;

import com.jinro.webide.login.domain.member.Authority;
import com.jinro.webide.login.domain.member.Member;
import com.jinro.webide.login.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JinroIDEUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Member> member = memberRepository.findByEmail(username);
        if(member.size() > 0){
            if(passwordEncoder.matches(pwd,member.get(0).getPassword())){
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(member.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        } else {
            throw new BadCredentialsException("No member registered with this details.");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
        List<GrantedAuthority> grandtedAuthorities = new ArrayList<>();
        for(Authority authority : authorities){
            grandtedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grandtedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}