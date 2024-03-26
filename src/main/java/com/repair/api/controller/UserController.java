package com.repair.api.controller;

import com.repair.api.dto.request.user.UserDeleteRequestDto;
import com.repair.api.dto.request.user.UserLoginRequestDto;
import com.repair.api.dto.request.user.UserMailRequestDto;
import com.repair.api.dto.request.user.UserPasswordChangeRequestDto;
import com.repair.api.dto.request.user.UserSignupRequestDto;
import com.repair.api.dto.request.user.UserUpdateRequestDto;
import com.repair.api.dto.response.user.UserDeleteResponseDto;
import com.repair.api.dto.response.user.UserLoginResponseDto;
import com.repair.api.dto.response.user.UserMailResponseDto;
import com.repair.api.dto.response.user.UserPasswordChangeResponseDto;
import com.repair.api.dto.response.user.UserSignupResponseDto;
import com.repair.api.dto.response.user.UserUpdateResponseDto;
import com.repair.api.service.user.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public UserSignupResponseDto signUp(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        return userService.signUp(userSignupRequestDto);
    }

    @PostMapping("/login")
    public Optional<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PostMapping("passwordchange")
    public Optional<UserPasswordChangeResponseDto> passwordChange(@RequestBody UserPasswordChangeRequestDto userPasswordChangeRequestDto) {
        return userService.passwordUpdate(userPasswordChangeRequestDto);
    }

    @PostMapping("/update")
    public Optional<UserUpdateResponseDto> update(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userUpdateRequestDto);
    }

    @PostMapping("/mail")
    public Optional<UserMailResponseDto> mail(@RequestBody UserMailRequestDto userMailRequestDto) {
        return userService.sendMail(userMailRequestDto);
    }

    @DeleteMapping("/delete")
    public Optional<UserDeleteResponseDto> userDelete(@RequestBody UserDeleteRequestDto userDeleteRequestDto) {
        return userService.deleteUser(userDeleteRequestDto);
    }
}