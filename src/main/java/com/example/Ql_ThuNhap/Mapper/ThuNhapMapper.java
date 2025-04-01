package com.example.Ql_ThuNhap.Mapper;

import com.example.Ql_ThuNhap.Dto.Request.ThuNhapRequest;
import com.example.Ql_ThuNhap.Dto.Response.ThuNhapResponse;
import com.example.Ql_ThuNhap.Dto.Update.ThuNhapUpdateRequest;
import com.example.Ql_ThuNhap.Entity.ThuNhap;
import com.example.Ql_ThuNhap.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ThuNhapMapper {

    @Mapping(target = "user", source = "user")
    ThuNhap toThuNhap(ThuNhapRequest thuNhapRequest, User user);
    @Mapping(target = "userId", source = "user.userId")
    ThuNhapResponse toThuNhapResponse(ThuNhap thuNhap);
    void updateThuNhap(@MappingTarget ThuNhap thuNhap, ThuNhapUpdateRequest thuNhapRequest);
}
