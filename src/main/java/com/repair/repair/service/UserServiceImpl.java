package com.repair.repair.service;

import com.repair.repair.dto.request.UserLoginRequestDto;
import com.repair.repair.dto.request.UserPasswordChangeRequestDto;
import com.repair.repair.dto.request.UserSignupRequestDto;
import com.repair.repair.dto.request.UserUpdateRequestDto;
import com.repair.repair.dto.response.UserLoginResponseDto;
import com.repair.repair.dto.response.UserPasswordChangeResponseDto;
import com.repair.repair.dto.response.UserSignupResponseDto;
import com.repair.repair.dto.response.UserUpdateResponseDto;
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

    @Transactional
    @Override
    public Optional<UserPasswordChangeResponseDto> passwordUpdate(UserPasswordChangeRequestDto userPasswordChangeRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userPasswordChangeRequestDto.getId());

        if(optionalUser.isEmpty()) {
            return Optional.of(new UserPasswordChangeResponseDto(0, "존재하지 않는 회원입니다."));
        }
        User findUSer = optionalUser.get();

        if (passwordEncoder.matches(userPasswordChangeRequestDto.getPassword(), findUSer.getPassword())) {
            findUSer.changePassword(passwordEncoder.encode(userPasswordChangeRequestDto.getNewPassword()));
            return Optional.of(new UserPasswordChangeResponseDto(1, "비밀번호 변경이 완료되었습니다."));
        } else {
            return Optional.of(new UserPasswordChangeResponseDto(0, "비밀번호가 일치하지 않습니다."));
        }
    }

    @Transactional
    @Override
    public Optional<UserUpdateResponseDto> updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userUpdateRequestDto.getId());

        if (optionalUser.isEmpty()) {
            return Optional.of(new UserUpdateResponseDto(0, "아이디에 해당하는 유저가 존재하지 않습니다."));
        } else {
            User findUser = optionalUser.get();

            if (passwordEncoder.matches(userUpdateRequestDto.getPassword(), findUser.getPassword())) {
                findUser.updateUser(userUpdateRequestDto.getName());

                return Optional.of(new UserUpdateResponseDto(1, "회원 정보 수정이 완료되었습니다."));
            } else {
                return Optional.of(new UserUpdateResponseDto(0, "비밀번호가 일치하지 않습니다."));
            }
        }
    }
}
