package com.example.Ql_ThuNhap.Mapper;

import com.example.Ql_ThuNhap.Dto.Request.ChiTietChiTieuRequest;
import com.example.Ql_ThuNhap.Dto.Response.ChiTietChiTieuResponse;
import com.example.Ql_ThuNhap.Dto.Update.ChiTietChiTieuUpdateRequest;
import com.example.Ql_ThuNhap.Entity.ChiTietChiTieu;
import com.example.Ql_ThuNhap.Entity.ChiTieu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChiTietChiTieuMapper {
    @Mapping(target = "chiTieu", source = "chiTieu")
    ChiTietChiTieu toChiTietChiTieu(ChiTietChiTieuRequest chiTietChiTieuRequest, ChiTieu chiTieu);
    @Mapping(target = "chiTieuId", source = "chiTieu.chiTieuId")
    ChiTietChiTieuResponse toChiTietChiTieuResponse(ChiTietChiTieu chiTietChiTieu);
    void updateChiTietChiTieu(@MappingTarget ChiTietChiTieu chiTietChiTieu, ChiTietChiTieuUpdateRequest chiTietChiTieuRequest);
}
