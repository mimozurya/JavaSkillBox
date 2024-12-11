package org.example.skillbox_mod6_orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/send")
    public void send(@RequestBody OrderRequest order){
        orderService.send(order);
    }
}
