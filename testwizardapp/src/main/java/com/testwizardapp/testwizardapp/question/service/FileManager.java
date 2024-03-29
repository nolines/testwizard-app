package com.testwizardapp.testwizardapp.question.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URL;
import java.nio.ByteBuffer;

@Service
@Slf4j
public class FileManager {

    private final static String bucket = "testwizard";

    @Autowired
    private S3Client s3Client;

    public String upload(String keyName, String base64File) {
        try {
            byte[] bI = org.apache.commons.codec.binary.Base64.decodeBase64((base64File.substring(base64File.indexOf(",") + 1)).getBytes());
            var putObjectResult = s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(keyName)
                            .contentType(MediaType.IMAGE_JPEG.toString())
                            .contentLength((long) bI.length)
                            .build(),
                    RequestBody.fromByteBuffer(ByteBuffer.wrap(bI)));
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

    public void remove(String keyName) {
        log.info("Removing file with key - {}", keyName);
        var deleteObjectResp = s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucket).key(keyName).build());

        deleteObjectResp.deleteMarker();
        log.info("File removal successful : {} - for file key : {}", keyName);
    }
}
