package cn.jjdcn.etas.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledService {
    @Scheduled(cron = "1/2 * * * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
}
//