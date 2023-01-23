package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dto.OrderDTO;

import java.util.concurrent.CompletableFuture;

@RestController
public class KafkaOrderController {

    @Autowired
    private KafkaTemplate<Integer, OrderDTO> template;

    @PostMapping
    public void sendNotification(OrderDTO orderDTO) {
        CompletableFuture<SendResult<Integer, OrderDTO>> future
                = template.send("messengers", orderDTO.getId(), orderDTO);
        future.whenComplete((result, error) ->
                System.out.println(result + "| Error: " + error)
        );
        template.flush();
    }
}
