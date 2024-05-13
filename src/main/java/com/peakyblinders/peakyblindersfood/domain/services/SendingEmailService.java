package com.peakyblinders.peakyblindersfood.domain.services;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;


public interface SendingEmailService {

    void toSend(Message message);

    @Getter
    @Builder
    class Message{
        @Singular
        private Set<String> recipients;
        @NonNull
        private String subject;
        @NonNull
        private String body;


    }
}


