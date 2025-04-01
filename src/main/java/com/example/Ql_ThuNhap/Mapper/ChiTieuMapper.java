package com.example.Ql_ThuNhap.Mapper;

import com.example.Ql_ThuNhap.Dto.Request.ChiTieuRequest;
import com.example.Ql_ThuNhap.Dto.Response.ChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Update.ChiTieuUpdateRequest;
import com.example.Ql_ThuNhap.Entity.ChiTieu;
import com.example.Ql_ThuNhap.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChiTieuMapper {
    @Mapping(target = "user", source = "user")
    ChiTieu toChiTieu(ChiTieuRequest chiTieuRequest, User user);
    @Mapping(target = "userId", source = "user.userId")
    ChiTieuResponse toChiTieuResponse(ChiTieu chiTieu);
    void updateChiTieu(@MappingTarget ChiTieu chiTieu, ChiTieuUpdateRequest chiTieuRequest);
}
