package com.zoft.solutions.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class BlobStorageConfig {

	@Value("${azure.storage.connection-string}")
    private String azureConnectionString;
	
    @Bean
    public BlobServiceClient blobServiceClient() {
        // Configure and create the BlobServiceClient
        return new BlobServiceClientBuilder()
                .connectionString(azureConnectionString)
                .buildClient();
    }
}

