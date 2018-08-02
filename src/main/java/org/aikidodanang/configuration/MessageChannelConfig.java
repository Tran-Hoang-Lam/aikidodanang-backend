package org.aikidodanang.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class MessageChannelConfig {
    private static final String TASK_EXECUTOR = "task-executor";

    @Bean(name = TASK_EXECUTOR)
    public TaskExecutor getTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("Pub-Sub-");
        taskExecutor.setCorePoolSize(3);
        return taskExecutor;
    }

    @Bean
    MessageChannel createAlbumChannel(@Qualifier(TASK_EXECUTOR) TaskExecutor taskExecutor) {
        return MessageChannels.publishSubscribe(taskExecutor).get();
    }
}
