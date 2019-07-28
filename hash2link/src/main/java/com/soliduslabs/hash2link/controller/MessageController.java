package com.soliduslabs.hash2link.controller;

import com.soliduslabs.hash2link.domain.Message;
import com.soliduslabs.hash2link.domain.dto.ReqResMessage;
import com.soliduslabs.hash2link.domain.dto.ResponseDigest;
import com.soliduslabs.hash2link.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<ResponseDigest> saveMessage(@RequestBody(required = true) ReqResMessage requestMessage) {
        String hash = messageService.stringToHash(requestMessage.getMessage());
        if (hash == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseDigest(hash), HttpStatus.OK);
    }

    @GetMapping("{hash}")
    public ResponseEntity<ReqResMessage> getMessage(@PathVariable String hash) {
        Optional<Message> message = messageService.hashToMessage(hash);
        return message.map(value -> new ResponseEntity<>(new ReqResMessage(value.getContent()), HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
