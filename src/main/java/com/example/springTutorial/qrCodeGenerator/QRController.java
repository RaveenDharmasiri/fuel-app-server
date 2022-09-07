package com.example.springTutorial.qrCodeGenerator;

import com.google.zxing.WriterException;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Base64;

public class QRController {
    public String getQRCode(String personNumber) {

        final String QR_CODE_IMAGE_PATH = "C:/DEV/Spring Boot/springTutorial/src/main/resources/static/img/QRCode_"+ personNumber +".png";

        String medium = "http://localhost:8080/api/v1/person-vehicle-details/get-QR-Code-details/" + personNumber;

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(medium, 250, 250);

            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(medium, 250, 250, QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        return "qrcode : " + qrcode;
    }
}
