package com.repair.repair.service;

import com.repair.repair.dto.request.UserSignupRequestDto;
import com.repair.repair.dto.response.UserSignupResponseDto;
import com.repair.repair.model.User;
import com.repair.repair.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserSignupResponseDto signUp(UserSignupRequestDto userSignupRequestDto) {
        User user = User.builder()
                .email(userSignupRequestDto.getEmail())
                .password(passwordEncoder.encode(userSignupRequestDto.getPassword()))
                .name(userSignupRequestDto.getName())
                .build();

        if (userRepository.findByEmail(userSignupRequestDto.getEmail()).isEmpty())  {
            userRepository.save(user);
            return new UserSignupResponseDto(user.getId(), user.getEmail(), user.getName());
        } else {
            return new UserSignupResponseDto("이미 가입된 이메일 입니다.", user.getEmail(), user.getName());
        }
    }
}
