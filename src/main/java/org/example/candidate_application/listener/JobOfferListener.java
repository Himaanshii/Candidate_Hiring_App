package org.example.candidate_application.listener;

import org.example.candidate_application.config.RabbitMQConfig;
import org.example.candidate_application.dto.JobOfferNotificationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.internet.MimeMessage;

@Component
public class JobOfferListener {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void listen(JobOfferNotificationDTO dto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(dto.getCandidateEmail());
            helper.setFrom(fromEmail);
            helper.setSubject("Job Offer Notification");

            String body = "<h3>Congratulations " + dto.getCandidateName() + "!</h3>" +
                    "<p>You have been selected for the position of <b>" + dto.getPosition() + "</b>.</p>";

            helper.setText(body, true);
            mailSender.send(message);
            System.out.println("Email sent to: " + dto.getCandidateEmail());
        } catch (Exception e) {
            System.err.println("Email sending failed: " + e.getMessage());
        }
    }
}

