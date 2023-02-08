package com.taco.config.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;

import java.io.File;

@Configuration
public class FileWriterIntegrationConfig {
    @Bean
    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> upperCaseTransformer(){
        return text -> text.toUpperCase();
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler fileWriter(){
        FileWritingMessageHandler fileWritingMessageHandler =
                new FileWritingMessageHandler(new File("tmp/si/files"));
        fileWritingMessageHandler.setExpectReply(false);
        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
        fileWritingMessageHandler.setAppendNewLine(true);
        return fileWritingMessageHandler;
    }
}
