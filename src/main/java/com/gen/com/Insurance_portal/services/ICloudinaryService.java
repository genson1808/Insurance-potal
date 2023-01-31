package com.gen.com.Insurance_portal.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface ICloudinaryService {
    String upload(MultipartFile file);
    CompletableFuture<String> uploadAsync(MultipartFile file);
    String uploadPDF(MultipartFile file);
    CompletableFuture<String> uploadPDFAsync(MultipartFile file);
    String downloadPDF(String publicId);
    String convert(MultipartFile file);
}
