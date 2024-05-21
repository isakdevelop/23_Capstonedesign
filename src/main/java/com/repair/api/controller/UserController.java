package com.repair.api.controller;

import com.repair.api.common.ResultResponseDto;
import com.repair.api.dto.request.user.UserDeleteRequestDto;
import com.repair.api.dto.request.user.UserLoginRequestDto;
import com.repair.api.dto.request.user.UserMailRequestDto;
import com.repair.api.dto.request.user.UserPasswordChangeRequestDto;
import com.repair.api.dto.request.user.UserSignupRequestDto;
import com.repair.api.dto.request.user.UserUpdateRequestDto;
import com.repair.api.dto.response.user.UserLoginResponseDto;
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
    public ResultResponseDto signUp(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        return userService.signUp(userSignupRequestDto);
    }

    @PostMapping("/login")
    public Optional<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PatchMapping("/modifyPW")
    public ResultResponseDto passwordChange(@RequestBody UserPasswordChangeRequestDto userPasswordChangeRequestDto) {
        return userService.passwordUpdate(userPasswordChangeRequestDto);
    }

    @PatchMapping("/update")
    public ResultResponseDto update(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userUpdateRequestDto);
    }

    @PostMapping("/mail")
    public ResultResponseDto mail(@RequestBody UserMailRequestDto userMailRequestDto) {
        return userService.sendMail(userMailRequestDto);
    }

    @DeleteMapping("/delete")
    public ResultResponseDto userDelete(@RequestBody UserDeleteRequestDto userDeleteRequestDto) {
        return userService.deleteUser(userDeleteRequestDto);
    }
}
