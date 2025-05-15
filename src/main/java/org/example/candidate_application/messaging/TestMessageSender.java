package org.example.candidate_application.messaging;



import org.example.candidate_application.config.RabbitMQConfig;
import org.example.candidate_application.dto.JobOfferNotificationDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestMessageSender implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) {
        JobOfferNotificationDTO dto = new JobOfferNotificationDTO();
        dto.setCandidateEmail("himaaanshiii30@gmail.com");
        dto.setCandidateName("Himanshi Jain");
        dto.setPosition("Backend Developer");

        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, dto);
            System.out.println("Test message sent to RabbitMQ for: " + dto.getCandidateEmail());
        } catch (Exception e) {
            System.err.println("Failed to send test message: " + e.getMessage());
        }
    }
}
