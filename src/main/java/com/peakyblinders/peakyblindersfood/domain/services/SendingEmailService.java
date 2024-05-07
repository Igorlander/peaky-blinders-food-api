package com.peakyblinders.peakyblindersfood.domain.services;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public interface SendingEmailService {

    void toSend(Message message);

    @Getter
    @Setter
    class Message{
    private Set<String> recipient;
            private String subject;
            private String body;
    }
}


