package rv.messaging.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rcs.auth.api.AuthUtils;
import rcs.auth.api.exceptions.UnauthorizedException;
import rv.messaging.models.Message;
import rv.messaging.models.MessageSendRequest;
import rv.messaging.services.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {

    private MessageService messageService;
    private AuthUtils authUtils;

    public MessagingController(MessageService messageService, AuthUtils authUtils) {
        this.messageService = messageService;
        this.authUtils = authUtils;
    }

    @PostMapping
    public ResponseEntity<Object> sendMessage(
            @RequestBody MessageSendRequest sendRequest,
            HttpServletRequest request) {

        return authUtils.tryGetLoggedInUser(request)
                .map(user -> {
                    messageService.sendMessage(
                            user.getUsername(),
                            sendRequest,
                            LocalDateTime.now());

                    return ResponseEntity.ok().build();
                })
                .orElseThrow(UnauthorizedException::new);
    }

    @GetMapping("/inbox")
    public List<Message> getInbox(HttpServletRequest request, @RequestParam(required = false) boolean unreadOnly) {
        return authUtils.tryGetLoggedInUser(request)
                .map(user -> messageService.getInbox(user.getUsername(), unreadOnly))
                .orElseThrow(UnauthorizedException::new);
    }

    @GetMapping("/outbox")
    public List<Message> getOutbox(HttpServletRequest request, @RequestParam(required = false) boolean unreadOnly) {
        return authUtils.tryGetLoggedInUser(request)
                .map(user -> messageService.getOutbox(user.getUsername(), unreadOnly))
                .orElseThrow(UnauthorizedException::new);
    }
}
