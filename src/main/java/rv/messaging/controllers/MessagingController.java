package rv.messaging.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rv.messaging.models.Message;
import rv.messaging.models.MessageSendRequest;
import rv.messaging.services.MessageService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {

    private MessageService messageService;

    public MessagingController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody MessageSendRequest request) {
        messageService.sendMessage(
                "user", // need to figure out use based on credentials"
                request,
                LocalDateTime.now());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/inbox")
    public List<Message> getInbox() {
        return messageService.getInbox("user");
    }
}
