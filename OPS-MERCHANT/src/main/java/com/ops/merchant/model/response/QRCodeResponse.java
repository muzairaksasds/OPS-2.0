package com.ops.merchant.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class QRCodeResponse {
    private String responseCode;
    private String responseDescription;
    private String qrCode;

}
