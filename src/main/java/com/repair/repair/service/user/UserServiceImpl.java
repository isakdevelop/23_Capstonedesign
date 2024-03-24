package com.repair.repair.service.user;

import com.repair.repair.common.RandomPasswordGenerator;
import com.repair.repair.dto.request.user.UserDeleteRequestDto;
import com.repair.repair.dto.request.user.UserLoginRequestDto;
import com.repair.repair.dto.request.user.UserMailRequestDto;
import com.repair.repair.dto.request.user.UserPasswordChangeRequestDto;
import com.repair.repair.dto.request.user.UserSignupRequestDto;
import com.repair.repair.dto.request.user.UserUpdateRequestDto;
import com.repair.repair.dto.response.user.UserDeleteResponseDto;
import com.repair.repair.dto.response.user.UserLoginResponseDto;
import com.repair.repair.dto.response.user.UserMailResponseDto;
import com.repair.repair.dto.response.user.UserPasswordChangeResponseDto;
import com.repair.repair.dto.response.user.UserSignupResponseDto;
import com.repair.repair.dto.response.user.UserUpdateResponseDto;
import com.repair.repair.domain.User;
import com.repair.repair.repository.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;

    @Transactional
    @Override
    public UserSignupResponseDto signUp(UserSignupRequestDto userSignupRequestDto) {
        User user = User.builder()
                .email(userSignupRequestDto.getEmail())
                .password(passwordEncoder.encode(userSignupRequestDto.getPassword()))
                .name(userSignupRequestDto.getName())
                .build();

        if (userRepository.findByEmail(userSignupRequestDto.getEmail()).isEmpty()) {
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

        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), findUser.get().getPassword())) {
            return Optional.of(new UserLoginResponseDto(findUser.get().getId(), findUser.get().getEmail(),
                    findUser.get().getName(),
                    findUser.get().getType()));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<UserPasswordChangeResponseDto> passwordUpdate(
            UserPasswordChangeRequestDto userPasswordChangeRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userPasswordChangeRequestDto.getId());

        if (optionalUser.isEmpty()) {
            return Optional.of(new UserPasswordChangeResponseDto(0, "존재하지 않는 회원입니다."));
        }
        User findUser = optionalUser.get();

        if (passwordEncoder.matches(userPasswordChangeRequestDto.getPassword(), findUser.getPassword())) {
            findUser.changePassword(passwordEncoder.encode(userPasswordChangeRequestDto.getNewPassword()));
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

    @Transactional
    @Override
    public Optional<UserMailResponseDto> sendMail(UserMailRequestDto userMailRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userMailRequestDto.getEmail());

        if (optionalUser.isEmpty()) {
            return Optional.of(new UserMailResponseDto(0, "존재하지 않는 이메일입니다."));
        }

        String password = RandomPasswordGenerator.generateRandomPassword();

        simpleMailMessage.setTo(userMailRequestDto.getEmail());
        simpleMailMessage.setSubject("[리페어] 임시 비밀번호 발급 안내.");
        simpleMailMessage.setFrom("daishi7462@naver.com");
        simpleMailMessage.setText("회원님의 임시 비밀번호는 " + password + " 입니다.");

        User findUser = optionalUser.get();
        findUser.changePassword(passwordEncoder.encode(password));

        javaMailSender.send(simpleMailMessage);
        return Optional.of(new UserMailResponseDto(1, "메일 발송이 완료되었습니다."));
    }

    @Transactional
    @Override
    public Optional<UserDeleteResponseDto> deleteUser(UserDeleteRequestDto userDeleteRequestDto) {
        Optional<User> optionalUser = userRepository.findById(userDeleteRequestDto.getId());

        if (optionalUser.isEmpty()) {
            return Optional.of(new UserDeleteResponseDto(0, "회원 탈퇴에 실패하였습니다."));
        }

        User findUser = optionalUser.get();

        if (passwordEncoder.matches(userDeleteRequestDto.getPassword(), findUser.getPassword())) {
            userRepository.deleteById(findUser.getId());
            return Optional.of(new UserDeleteResponseDto(1, "회원 탈퇴가 정상 진행 되었습니다."));
        } else {
            return Optional.of(new UserDeleteResponseDto(0, "회원 탈퇴에 실패하였습니다."));
        }
    }
}
