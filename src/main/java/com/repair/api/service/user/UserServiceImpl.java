package com.repair.api.service.user;

import com.repair.api.common.RandomPasswordGenerator;
import com.repair.api.common.ResultResponseDto;
import com.repair.api.domain.value.Error;
import com.repair.api.domain.value.Role;
import com.repair.api.dto.request.user.UserDeleteRequestDto;
import com.repair.api.dto.request.user.UserLoginRequestDto;
import com.repair.api.dto.request.user.UserMailRequestDto;
import com.repair.api.dto.request.user.UserPasswordChangeRequestDto;
import com.repair.api.dto.request.user.UserSignupRequestDto;
import com.repair.api.dto.request.user.UserUpdateRequestDto;
import com.repair.api.dto.response.user.UserLoginResponseDto;
import com.repair.api.domain.User;
import com.repair.api.exception.RepairException;
import com.repair.api.repository.user.UserRepository;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;

    @Override
    public ResultResponseDto signUp(UserSignupRequestDto userSignupRequestDto) {
        User user = User.builder()
                .email(userSignupRequestDto.getEmail())
                .password(passwordEncoder.encode(userSignupRequestDto.getPassword()))
                .name(userSignupRequestDto.getName())
                .phone(userSignupRequestDto.getPhone())
                .role(Role.USER.getKey())
                .build();

        if (userRepository.findByEmail(userSignupRequestDto.getEmail()).isEmpty()) {
            userRepository.save(user);
            return new ResultResponseDto(200, "회원가입에 성공하셨습니다.");
        } else {
            throw new RepairException(Error.ID_CONFLICT.getStatus(), Error.ID_CONFLICT.getMessage());
        }
    }

    @Override
    public Optional<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(() -> new RepairException(Error.ID_NOT_FOUND.getStatus(), Error.ID_NOT_FOUND.getMessage()));

        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            return Optional.of(new UserLoginResponseDto(user.getId(), user.getEmail(),
                    user.getName(),
                    user.getRole()));
        } else {
            throw new RepairException(Error.PW_NOT_FOUND.getStatus(), Error.PW_NOT_FOUND.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultResponseDto passwordUpdate(UserPasswordChangeRequestDto userPasswordChangeRequestDto) {
        User user = userRepository.findById(userPasswordChangeRequestDto.getUserId())
                .orElseThrow(() -> new RepairException(Error.ID_NOT_FOUND.getStatus(), Error.ID_NOT_FOUND.getMessage()));

        if (passwordEncoder.matches(userPasswordChangeRequestDto.getPassword(), user.getPassword())) {
            user.changePassword(passwordEncoder.encode(userPasswordChangeRequestDto.getNewPassword()));
            return new ResultResponseDto(1, "비밀번호 변경이 완료되었습니다.");
        } else {
            throw new RepairException(Error.PW_NOT_FOUND.getStatus(), Error.PW_NOT_FOUND.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userUpdateRequestDto.getUserId())
                .orElseThrow(() -> new RepairException(Error.ID_NOT_FOUND.getStatus(), Error.ID_NOT_FOUND.getMessage()));

        if (passwordEncoder.matches(userUpdateRequestDto.getPassword(), user.getPassword())) {
            user.updateUser(userUpdateRequestDto.getName(), userUpdateRequestDto.getPhone());
            return new ResultResponseDto(1, "회원 정보 수정이 완료되었습니다.");
        } else {
            throw new RepairException(Error.PW_NOT_FOUND.getStatus(), Error.PW_NOT_FOUND.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultResponseDto sendMail(UserMailRequestDto userMailRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userMailRequestDto.getEmail());

        if (optionalUser.isEmpty()) {
            throw new RepairException(Error.EMAIL_NOT_FOUND.getStatus(), Error.EMAIL_NOT_FOUND.getMessage());
        }

        String password = RandomPasswordGenerator.generateRandomPassword();

        simpleMailMessage.setTo(userMailRequestDto.getEmail());
        simpleMailMessage.setSubject("[리페어] 임시 비밀번호 발급 안내.");
        simpleMailMessage.setFrom("daishi7462@naver.com");
        simpleMailMessage.setText("회원님의 임시 비밀번호는 " + password + " 입니다.");

        User findUser = optionalUser.get();
        findUser.changePassword(passwordEncoder.encode(password));

        javaMailSender.send(simpleMailMessage);
        return new ResultResponseDto(1, "메일 발송이 완료되었습니다.");
    }

    @Override
    public ResultResponseDto deleteUser(UserDeleteRequestDto userDeleteRequestDto) {
        User user = userRepository.findById(userDeleteRequestDto.getUserId())
                .orElseThrow(() -> new RepairException(Error.ID_NOT_FOUND.getStatus(), Error.ID_NOT_FOUND.getMessage()));

        userRepository.deleteById(user.getId());

        return new ResultResponseDto(1, "회원 탈퇴가 정상 진행되었습니다.");
    }
}
