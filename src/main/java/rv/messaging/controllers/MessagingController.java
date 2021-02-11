package rv.messaging.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rv.messaging.models.MessageSendRequest;
import rv.messaging.services.MessageService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {

    private MessageService messageService;

    public MessagingController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody MessageSendRequest request) {
        messageService.sendMessage(
                "user", // need to figure out use based on credentials"
                request,
                LocalDateTime.now());

        return ResponseEntity.ok().build();
    }
}
