package com.example.Ql_ThuNhap.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXITED(1001,"User exited", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 3 characters",HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1000, "Invalid message Key",HttpStatus.NOT_FOUND),
    USER_NOT_EXITED(1006, "User not existed",HttpStatus.NOT_FOUND),
    UNAUTHENTIACTED(1008, "Unauthenticated",HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(1007,"User not found",HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1018, "You do not have permission", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1019, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    USERNAME_NOT_FOUND(1020,"Cannot find user with UserName",HttpStatus.NOT_FOUND),
    INVALID_USERNAME_PASSWORD(1021, "Invalid UserName/Password",HttpStatus.BAD_REQUEST),
    USER_FORBIDDENED(1027,"user account is unaccessible",HttpStatus.BAD_REQUEST),
    SO_DU_THIEU(1028,"Số dư không đủ để tạo tiết kiệm", HttpStatus.BAD_REQUEST),
    SO_DU_KHONG_DU(1029, "Số dư không đủ để tạo chi tiêu", HttpStatus.BAD_REQUEST),
    CHI_TIEU_NOT_FOUND(1030, "Chi tiêu không tồn tại", HttpStatus.NOT_FOUND),
    CHI_TIET_CHI_TIEU_NOT_FOUND(1031, "Chi tiết chi tiêu không tồn tại", HttpStatus.NOT_FOUND),
    SO_TIEN_VUOT_QUA_HAN_MUC (1032, "Tổng số tiền chi tiết vượt quá số tiền chi tiêu", HttpStatus.BAD_REQUEST),
    TIET_KIEM_NOT_FOUND(1033, "Khoản tiết kiệm này không tồn tại", HttpStatus.NOT_FOUND),
    NGAY_GUI_PHẢI_TRƯỚC_NGAY_DAT_MUC_TIEU(1034, "Ngày gửi vào phải trước ngày đạt được mục tiêu" , HttpStatus.BAD_REQUEST),
    Email(1035, "Email đã tồn tại", HttpStatus.BAD_REQUEST),
    SO_DU_KO_DU_DE_XOA(1036, "Không thể xóa thu nhập vì số dư không đủ." , HttpStatus.BAD_REQUEST),
    Invalid_Email_Format(1037," Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    Email_Empty (1038, "Email không được để trống" , HttpStatus.BAD_REQUEST)

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
