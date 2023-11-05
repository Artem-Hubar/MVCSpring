package com.example.demo;
import com.example.demo.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
public class MVCGreetingController {

    private MessageRepo messageRepo;

    @Autowired
    public MVCGreetingController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);

        return "mvs-greeting";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name, Map<String, Object> model){
        System.out.println("testы");
        String s = name + " Yes";
        model.put("name", s);
        return "hello";
    }

    @GetMapping("/messages")
    public String messages(Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "messages";
    }

    @PostMapping("/add")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Message message = new Message(text, tag);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "messages";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String text, Map<String, Object> model){
        Iterable<Message> messages;
        if (text.isEmpty()){
            messages = messageRepo.findAll();
        }else {
            messages = messageRepo.findByTag(text);
        }
        model.put("messages", messages);
        return "messages";
    }
}