package com.carapp.apigateway.socket;

import com.carapp.apigateway.order.Order;
import lombok.Data;

@Data
public class WebSocketMessage {
    Order order;
    String token;
}
