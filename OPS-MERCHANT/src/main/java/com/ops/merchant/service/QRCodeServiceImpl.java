package com.ops.merchant.service;

import com.ops.merchant.Util.QRCodeGenerator;
import com.ops.merchant.model.request.QRCodeRequest;
import com.ops.merchant.model.response.QRCodeResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    QRCodeGenerator qrCodeGenerator;

    @Override
    public ResponseEntity<QRCodeResponse> generateQRCode(QRCodeRequest request) {

        try {

            String text = createTextForQR(request);

            BufferedImage image = qrCodeGenerator.generateQRCodeImage(text, qrCodeGenerator.getWIDTH(), qrCodeGenerator.getHEIGHT());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Encode imageBytes to Base64
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            QRCodeResponse response = new QRCodeResponse("200", "Success", base64Image);

            return ResponseEntity.ok().body(response);

        } catch (IOException e) {
            // Handle IOException appropriately
            return ResponseEntity.status(500).body(null);
        } catch (Exception e) {
            // Handle other exceptions appropriately
            return ResponseEntity.status(500).body(null);
        }
    }

    private String createTextForQR(QRCodeRequest request) {
        UUID qrId = UUID.randomUUID();
        String toReturn = "\nQR ID: " + qrId +
                "\nChannel: " + request.getChannel() +
                "\nStore ID: " + request.getStoreId() +
                "\nPayment Method: " + request.getPaymentMethod() +
                "\nOrder Ref Num: " + request.getOrderRefNum() +
                "\nTransaction Point Num: " + request.getTransactionPointNum() +
                "\nQR Format Indicator: " + request.getQrFormatIndicator() +
                "\nSignature: " + request.getSignature();

        return toReturn;
    }



}
