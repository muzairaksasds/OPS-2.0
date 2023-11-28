package com.ops.merchant.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QRCodeRequest {

    private String channel;

    private Integer storeId;

    private String paymentMethod;

    private String orderRefNum;

    private Double amount;

    private String transactionPointNum;

    private String productNumber;

    private String qrFormatIndicator;

    private String signature;



}

