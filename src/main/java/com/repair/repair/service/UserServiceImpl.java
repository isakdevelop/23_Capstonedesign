package com.repair.repair.service;

import com.repair.repair.dto.request.UserLoginRequestDto;
import com.repair.repair.dto.request.UserSignupRequestDto;
import com.repair.repair.dto.response.UserLoginResponseDto;
import com.repair.repair.dto.response.UserSignupResponseDto;
import com.repair.repair.model.User;
import com.repair.repair.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Optional<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto) {
        Optional<User> findUser = userRepository.findByEmail(userLoginRequestDto.getEmail());

        if (findUser.isEmpty()) {
            return Optional.empty();
        }

        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), findUser.get().getPassword()))   {
            return Optional.of(new UserLoginResponseDto(findUser.get().getId(), findUser.get().getEmail(), findUser.get().getName(),
                    findUser.get().getType()));
        } else {
            return Optional.empty();
        }
    }
}