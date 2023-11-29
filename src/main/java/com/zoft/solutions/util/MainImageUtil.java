package com.zoft.solutions.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;

@Component
public class MainImageUtil {

    @Value("${azure.blob.container-name}")
    private String containerName;

    @Autowired
    private BlobServiceClient blobServiceClient;
    
 // Handle image upload
    @Transactional
    public ResponseEntity<Map<String, Object>> uploadImage(MultipartFile file) {
        try {
            // Upload the file to Azure Blob Storage
            // Generate a unique name for the blob
            String blobName = UUID.randomUUID().toString();
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobName);
            blobClient.upload(file.getInputStream(), file.getSize(), true);

            // Save the image metadata in the database
           // blogRepository.setImageDetails(blogId, blobName, blobClient.getBlobUrl());

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("blobName", blobName);
            responseMap.put("blobUrl", blobClient.getBlobUrl());

            // Return ResponseEntity with the Map as the response body
            return ResponseEntity.ok(responseMap);
            //return ResponseEntity.ok(blogId, blobName, blobClient.getBlobUrl()"Image uploaded successfully.");
        } catch (IOException e) {
            return null;
        }
    }


    // Delete an image by ID
    public void deleteImage(String blobName) {
//        Optional<Blogdetails> optionalImage = blogRepository.findById(blogId);
//        if (optionalImage.isPresent()) {
//            Blogdetails image = optionalImage.get();
//            String blobName = image.getCoverImageName();
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobName);
            blobClient.delete();
            //blogRepository.clearImageDetails(blogId);

    }
}
