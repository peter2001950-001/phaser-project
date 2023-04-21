package com.phaser.project.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BulkUpdateOrderRequest {


    private List<OrderItem> orderItems;


}

