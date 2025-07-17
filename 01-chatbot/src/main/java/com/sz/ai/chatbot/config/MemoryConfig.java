package com.sz.ai.chatbot.config;

import com.alibaba.cloud.ai.memory.jdbc.MysqlChatMemoryRepository;
import com.alibaba.cloud.ai.memory.redis.RedisChatMemoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author StarryZeng
 * @version 1.0.0
 * @date 2025/7/18 00:37
 */
@Configuration
public class MemoryConfig {
    
    @Value("${spring.ai.memory.redis.host}")
    private String redisHost;
    @Value("${spring.ai.memory.redis.port}")
    private int redisPort;
    @Value("${spring.ai.memory.redis.password}")
    private String redisPassword;
    @Value("${spring.ai.memory.redis.timeout}")
    private int redisTimeout;
    
    @Value("${spring.ai.memory.repository.jdbc.mysql.url}")
    private String mysqlJdbcUrl;
    
    @Value("${spring.ai.memory.repository.jdbc.mysql.username}")
    private String mysqlUsername;
    
    @Value("${spring.ai.memory.repository.jdbc.mysql.password}")
    private String mysqlPassword;
    
    @Value("${spring.ai.memory.repository.jdbc.mysql.driver-class-name}")
    private String mysqlDriverClassName;
    
    @Bean
    public RedisChatMemoryRepository redisChatMemoryRepository() {
        return RedisChatMemoryRepository.builder()
                .host(redisHost)
                .port(redisPort)
                //.password(redisPassword)
                .timeout(redisTimeout)
                .build();
    }
    
    @Bean
    public MysqlChatMemoryRepository mysqlChatMemoryRepository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlDriverClassName);
        dataSource.setUrl(mysqlJdbcUrl);
        dataSource.setUsername(mysqlUsername);
        dataSource.setPassword(mysqlPassword);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return MysqlChatMemoryRepository.mysqlBuilder().jdbcTemplate(jdbcTemplate).build();
    }
    
}
