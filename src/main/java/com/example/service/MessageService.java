package com.example.service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
@Transactional
public class MessageService {
    
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    public Message createMessage(Message message){

        if(message.getMessageText().isBlank() || message.getMessageText().length() > 255)
            return null;
        

        return messageRepository.save(message);
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    public Message getMessageById(Integer id)
    {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent())
        {
            Message message = optionalMessage.get();
            return message;
        }

        return null;
    }
    public Integer deleteMessageById(Integer id)
    {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent())
        {
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
    public Integer updateMessageById(Integer id, String newMessageText)
    {
        if(newMessageText.isBlank() || newMessageText.length() > 255)
            return 0;

        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent())
        {
            Message message = optionalMessage.get();
            message.setMessageText(newMessageText);
            messageRepository.save(message);
            return 1;
        }
        return 0;
    }
    public List<Message> getMessagesByUserId(Integer id)
    {
        Optional<List<Message>> optionalMessage = messageRepository.findMessagesByPostedBy(id);
        if(optionalMessage.isPresent())
        {
            List<Message> messages = optionalMessage.get();
            return messages;
        }

        return null;
    }
}
