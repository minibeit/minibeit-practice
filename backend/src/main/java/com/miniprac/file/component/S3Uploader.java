package com.miniprac.file.component;

import com.miniprac.common.dto.SavedFile;
import com.miniprac.file.domain.FileServer;
import com.miniprac.file.domain.FileType;
import com.miniprac.file.service.exception.S3FileUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3Uploader {
    private final S3Client s3client;
    private final S3Props storageProps;

    public List<SavedFile> uploadFileList(List<MultipartFile> files) {
        return files.stream().map(this::upload).collect(Collectors.toList());
    }

    public SavedFile upload(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String extension = Optional.ofNullable(originalName)
                .filter(s -> s.contains("."))
                .map(s -> s.substring(originalName.lastIndexOf(".") + 1))
                .orElse(null);

        String s3FileName = Optional.ofNullable(RandomStringBuilder.generateAlphaNumeric(60)).orElseThrow() + "." + extension;
        String publicUrl = storageProps.getS3Public() + "/" + s3FileName;

        boolean isImage = this.isImage(extension);
        Integer width = null;
        Integer height = null;
        try {
            PutObjectResponse response = s3client.putObject(
                    PutObjectRequest.builder()
                            .key(s3FileName)
                            .bucket(storageProps.getS3Bucket())
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));
            if (isImage) {
                BufferedImage image = ImageIO.read(file.getInputStream());
                width = image.getWidth();
                height = image.getHeight();
            }
        } catch (IOException e) {
            throw new S3FileUploadException();
        }
        SavedFile.SavedFileBuilder savedFileBuilder = SavedFile.builder()
                .name(s3FileName)
                .extension(extension)
                .fileServer(FileServer.S3)
                .originalName(originalName)
                .size(file.getSize())
                .isImage(isImage)
                .publicUrl(publicUrl)
                .width(width)
                .height(height);
        if (isImage) {
            return savedFileBuilder.fileType(FileType.IMAGE).build();
        }
        return savedFileBuilder.fileType(FileType.FILE).build();
    }

    private boolean isImage(String extension) {
        return Optional.ofNullable(extension)
                .map(s -> s.toLowerCase().matches("png|jpeg|jpg|bmp|gif|svg"))
                .orElse(false);
    }
}
