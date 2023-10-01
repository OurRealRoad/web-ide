package com.jinro.webide.login.web;

import com.jinro.webide.login.domain.member.Member;
import com.jinro.webide.login.domain.member.MemberRepository;
import com.jinro.webide.login.web.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthController {

    @Autowired
    MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login page";
    }

    @GetMapping("/api/v1/signup")
    public String signup() {
        return "sign up page";
    }

    @PostMapping("/api/v1/signup")
    public SignupResponseDto signup(@RequestBody Member member){
        SignupResponseDto signupResponseDto = new SignupResponseDto();
        Member registeredMember = new Member();
        try{
            String hashPwd = passwordEncoder.encode(member.getPassword());
            member.setPassword(hashPwd);
            member.setRole("USER");
            member.setCreatedDate(String.valueOf(LocalDateTime.now()));
            registeredMember = memberRepository.save(member);
            System.out.println("2");
            if(registeredMember.getId() > 0 ){
                System.out.println("3");
                signupResponseDto.setStatus(HttpStatus.OK);
                signupResponseDto.setResult(true);
                signupResponseDto.setMessage("Successfully Signed Up");
                return signupResponseDto;
            }
            System.out.println("4");
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("5");
            signupResponseDto = new SignupResponseDto();
            signupResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            signupResponseDto.setResult(false);
            signupResponseDto.setMessage("An exception occured due to " + e.getMessage());
            return signupResponseDto;
        }
    }

    @RequestMapping("/api/v1/user")
    public Member getUserDetailsAfterLogin(Authentication authentication){
        List<Member> members = memberRepository.findByEmail(authentication.getName());
        if(members.size() > 0){
            return members.get(0);
        } else {
            return null;
        }
    }
}
