package com.testwizardapp.testwizardapp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.net.URL;
import java.nio.ByteBuffer;

@Service
@Slf4j
@AllArgsConstructor
public class FileManager {

    private final static String bucket = "testwizard";
    @Autowired
    private final S3Client s3Client;

    public String upload(String keyName, byte[] attachment) {
        try {
            log.info("Uploading a PDF to S3 - {}", keyName);
            PutObjectResponse putObjectResult = s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(keyName)
                            .contentType(MediaType.APPLICATION_PDF.toString())
                            .contentLength((long) attachment.length)
                            .build(),
                    RequestBody.fromByteBuffer(ByteBuffer.wrap(attachment)));
            final URL reportUrl = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucket).key(keyName).build());
            log.info("putObjectResult = " + putObjectResult);
            log.info("reportUrl = " + reportUrl);
            return reportUrl.toString();
        } catch (SdkServiceException ase) {
            log.error("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason.", ase);
            log.info("Error Message:    " + ase.getMessage());
            log.info("Key:       " + keyName);
            throw ase;
        } catch (SdkClientException ace) {
            log.error("Caught an AmazonClientException, which " + "means the client encountered "
                    + "an internal error while trying to " + "communicate with S3, "
                    + "such as not being able to access the network.", ace);
            log.error("Error Message: {}, {}", keyName, ace.getMessage());
            throw ace;
        }
    }
}
