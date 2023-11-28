package com.ops.merchant.service;

import com.ops.merchant.model.request.QRCodeRequest;
import com.ops.merchant.model.response.QRCodeResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface QRCodeService {
    ResponseEntity<QRCodeResponse> generateQRCode(QRCodeRequest request);
}
