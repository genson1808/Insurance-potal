package com.gen.com.Insurance_portal.services.impls;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.gen.com.Insurance_portal.services.ICloudinaryService;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CloudinaryService implements ICloudinaryService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public String upload(MultipartFile file) {

        logger.trace("Called CloudinaryService.upload with args: the multipart file");
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String publicId = uploadResult.get("public_id").toString();
            logger.info("successfully uploaded the file: " + publicId);
            return uploadResult.get("url").toString();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    @Async
    public CompletableFuture<String> uploadAsync(MultipartFile file) {
        logger.trace("Called CloudinaryService.upload with args: the multipart file");
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String publicId = uploadResult.get("public_id").toString();
            logger.info("successfully uploaded the file: " + publicId);
            return CompletableFuture.completedFuture(uploadResult.get("url").toString());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public String uploadPDF(MultipartFile file) {
        logger.trace("Called CloudinaryService.upload with args: the multipart file");
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("format", "pdf"));
            String publicId = uploadResult.get("public_id").toString();
            logger.info("successfully uploaded the file: " + publicId);

            String format = "jpg";
            Transformation transformation = new Transformation().page(1).crop("scale");

            String cloudUrl = cloudinary.url().secure(true).format(format)
                    .transformation(transformation)
                    .publicId(publicId)
                    .generate();

            return cloudUrl;

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    @Async
    public CompletableFuture<String> uploadPDFAsync(MultipartFile file) {
        logger.trace("Called CloudinaryService.upload with args: the multipart file");
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("format", "pdf"));
            String publicId = uploadResult.get("public_id").toString();
            logger.info("successfully uploaded the file: " + publicId);

            String format = "jpg";
            Transformation transformation = new Transformation().page(1).crop("scale");

            String cloudUrl = cloudinary.url().secure(true).format(format)
                    .transformation(transformation)
                    .publicId(publicId)
                    .generate();

            return CompletableFuture.completedFuture(cloudUrl);

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public String downloadPDF(String publicId) {
        return null;
    }

    @Override
    public String convert(MultipartFile file) {
        return null;
    }

    public ResponseEntity<ByteArrayResource> downloadImg(String publicId, int width, int height, boolean isAvatar) {

        logger.info("Requested to download the image file: " + publicId);

        // Generates the URL
        String format = "jpg";
        Transformation transformation = new Transformation().width(width).height(height).crop("fill");
        if (isAvatar) {
            // transformation = transformation.gravity("face").radius("max");
            transformation = transformation.radius("max");
            format = "png";
        }
        String cloudUrl = cloudinary.url().secure(true).format(format)
                .transformation(transformation)
                .publicId(publicId)
                .generate();

        logger.debug("Generated URL of the image to be downloaded: " + cloudUrl);

        try {
            // Get a ByteArrayResource from the URL
            URL url = new URL(cloudUrl);
            InputStream inputStream = url.openStream();
            byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);
            ByteArrayResource resource = new ByteArrayResource(out);

            // Creates the headers
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-disposition", "attachment; filename=image.jpg");
            responseHeaders.add("Content-Type", "image/jpeg");

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .contentLength(out.length)
                    // .contentType(MediaType.parseMediaType(mimeType))
                    .body(resource);

        } catch (Exception ex) {
            logger.error("FAILED to download the file: " + publicId);
            return null;
        }
    }

}


