package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.*;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account)
    {
        Account result = accountService.registerAccount(account);
        
        if(result == null)
            return ResponseEntity.status(400).body(account);

        if(result.getAccountId() == null) {
            return ResponseEntity.status(409).body(account);
        }    


        return ResponseEntity.status(200).body(account);
    }
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account)
    {
        Account result = accountService.loginAccount(account);

        if(result == null)
            return ResponseEntity.status(401).body(result);
        
        return ResponseEntity.ok(result);
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){

        if(!accountService.userExists(message.getPostedBy()))
            return ResponseEntity.status(400).body(message);

        Message result = messageService.createMessage(message);
        
        if(result == null)
            return ResponseEntity.status(400).body(result);


        return ResponseEntity.ok(result);
    }

}
