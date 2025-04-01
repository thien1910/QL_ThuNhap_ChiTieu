package com.example.Ql_ThuNhap.Mapper;

import com.example.Ql_ThuNhap.Dto.Request.TietKiemRequest;
import com.example.Ql_ThuNhap.Dto.Response.TietKiemResponse;
import com.example.Ql_ThuNhap.Dto.Update.TietKiemUpdateRequest;
import com.example.Ql_ThuNhap.Entity.TietKiem;
import com.example.Ql_ThuNhap.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TietKiemMapper {
    @Mapping(target = "user", source = "user")
    TietKiem toTietKiem(TietKiemRequest tietKiemRequest, User user);
    @Mapping(target = "userId", source = "user.userId")
    TietKiemResponse toTietKiemResponse(TietKiem tietKiem);
    void updateTietKiem(@MappingTarget TietKiem tietKiem, TietKiemUpdateRequest tietKiemRequest);
}
