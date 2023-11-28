package com.ops.merchant.controller;
import com.ops.merchant.model.request.QRCodeRequest;
import com.ops.merchant.model.response.QRCodeResponse;
import com.ops.merchant.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/generate")
    public ResponseEntity<QRCodeResponse> generateQRCode(@RequestBody QRCodeRequest request) {
        System.out.println("DEBUG----------------1");
        return qrCodeService.generateQRCode(request);
    }
}
