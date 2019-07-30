package com.soliduslabs.hash2link.service;

import com.soliduslabs.hash2link.db.MessageRepository;
import com.soliduslabs.hash2link.domain.Message;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String stringToHash(String text) {

        Optional<Message> message = messageRepository.findByContent(text);
        if (message.isPresent()) return message.get().getId();

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] digestBytes = messageDigest.digest();
        String hexString = DatatypeConverter.printHexBinary(digestBytes);
        messageRepository.save(new Message(hexString, text));
        return hexString;
    }

    public Optional<Message> hashToMessage(String hash) {
        return messageRepository.findById(hash);
    }
}
