package com.peakyblinders.peakyblindersfood.core.storge;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("peakyblindersfood-api.storage")
public class StorageProperties {


    private Local local = new Local();

    @Getter
    @Setter
    public class Local {
        private Path directoryPhotos;
    }
}


