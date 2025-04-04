package com.example.Ql_ThuNhap.Mapper;

import com.example.Ql_ThuNhap.Dto.Request.UserCreationRequest;
import com.example.Ql_ThuNhap.Dto.Response.UserResponse;
import com.example.Ql_ThuNhap.Dto.Update.UserUpdateRequest;
import com.example.Ql_ThuNhap.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    @Mapping(target = "soDu", source = "soDu") // ánh xạ số dư
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}