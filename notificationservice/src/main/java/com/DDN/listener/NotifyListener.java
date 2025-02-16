package com.DDN.listener;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import com.DDN.entity.NotifiEntity;

import com.DDN.repository.NotifiRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotifyListener {
    @Autowired
    private NotifiRepository notifiRepository;

    @KafkaListener(topics = "booking-notification", groupId = "notify-group")
    public void processNotification(String message) {
        try {
            Map<String, String> notification = new ObjectMapper().readValue(message, new TypeReference<>() {
            });
            saveNotification(notification);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void saveNotification(Map<String, String> notification) {
        NotifiEntity notifiEntity = new NotifiEntity();
        notifiEntity.setCustomerName(notification.get("customerName"));
        notifiEntity.setEmail(notification.get("email"));
        notifiEntity.setFlightNumber(notification.get("flightNumber"));

        notifiRepository.save(notifiEntity);
    }

}
