package com.example.Ql_ThuNhap.Controller;

import com.example.Ql_ThuNhap.Dto.Request.ApiResponse;
import com.example.Ql_ThuNhap.Dto.Request.UserCreationRequest;
import com.example.Ql_ThuNhap.Dto.Response.UserResponse;
import com.example.Ql_ThuNhap.Dto.Update.UserUpdateRequest;
import com.example.Ql_ThuNhap.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    @GetMapping("/list")
    ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") Long userId){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }
   /* @GetMapping("/my-info")
    public UserResponse getMyInfo() {
        return userService.getMyInfo();
    }*/
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId){
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }
}
