package edu.asoldatov.bet.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckResultJob {

    //todo job for check result

    @Scheduled(fixedRate = 1000)
    public void check() {

    }

}
