package rcs.messaging.services;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rcs.messaging.models.Message;
import rcs.messaging.models.MessageSendRequest;
import rcs.messaging.repositories.MessagesRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

        assertThat(saved.get_id()).isNotNull();
        assertThat(saved.getFrom()).isEqualTo(sender);
        assertThat(saved.getTo()).isEqualTo(request.getTo());
        assertThat(saved.getSubject()).isEqualTo(request.getSubject());
        assertThat(saved.getBody()).isEqualTo(request.getBody());
        assertThat(saved.getDate()).isEqualTo(date);
    }

    @Test
    public void testGetInbox() {
        // Arrange
        String user = "user";

        Message message1 = new Message(new ObjectId(), null, null, null, null, null, false);
        Message message2 = new Message(new ObjectId(), null, null, null, null, null, false);
        List<Message> expectedResult = List.of(message1, message2);
        when(repository.getInbox(user, true)).thenReturn(expectedResult);

        // Act
        List<Message> result = target.getInbox(user, true);

        // Assert
        verify(repository).getInbox(user, true);
        verify(repository).markAsRead(Set.of(message1.get_id(), message2.get_id()));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetOutbox() {
        // Arrange
        String user = "user";

        List<Message> expectedResult = List.of(mock(Message.class));
        when(repository.getOutbox(user, true)).thenReturn(expectedResult);

        // Act
        List<Message> result = target.getOutbox(user, true);

        // Assert
        verify(repository).getOutbox(user, true);
        assertThat(result).isEqualTo(expectedResult);
    }
}
