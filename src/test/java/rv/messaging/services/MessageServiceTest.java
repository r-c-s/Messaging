package rv.messaging.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rv.messaging.models.Message;
import rv.messaging.models.MessageSendRequest;
import rv.messaging.repositories.MessagesRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageServiceTest {

    private MessageService target;
    private MessagesRepository repository;

    @Before
    public void setup() {
        repository = mock(MessagesRepository.class);
        target = new MessageService(repository);
    }

    @Test
    public void testSendMessage() {
        // Arrange
        String sender = "sender";
        MessageSendRequest request = new MessageSendRequest("to", "subject", "body");
        LocalDateTime date = LocalDateTime.now();

        // Act
        target.sendMessage(sender, request, date);

        // Assert
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(repository).save(captor.capture());
        Message saved = captor.getValue();

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getFrom()).isEqualTo(sender);
        assertThat(saved.getTo()).isEqualTo(request.getTo());
        assertThat(saved.getSubject()).isEqualTo(request.getSubject());
        assertThat(saved.getBody()).isEqualTo(request.getBody());
        assertThat(saved.getDate()).isEqualTo(date);
    }
}
