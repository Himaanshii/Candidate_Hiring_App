package org.example.candidate_application.service;

import org.example.candidate_application.config.RabbitMQConfig;
import org.example.candidate_application.dto.JobOfferNotificationDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobOfferService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notifyCandidate(JobOfferNotificationDTO dto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                dto
        );
    }

}
