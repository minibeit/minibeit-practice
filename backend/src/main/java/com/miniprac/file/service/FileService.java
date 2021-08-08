package com.miniprac.file.service;

import com.miniprac.common.dto.SavedFile;
import com.miniprac.file.component.S3Uploader;
import com.miniprac.file.domain.File;
import com.miniprac.file.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final S3Uploader s3Uploader;

    public File upload(MultipartFile file) {
        if (file == null) {
            return null;
        }
        return fileRepository.save(File.create(s3Uploader.upload(file)));
    }

    public List<File> uploadFiles(List<MultipartFile> files) {
        List<SavedFile> savedFiles = new ArrayList<>();
        if (files != null) {
            savedFiles = s3Uploader.uploadFileList(files);
        }
        return fileRepository.saveAll(savedFiles.stream().map(File::create).collect(Collectors.toList()));
    }

    public void deleteById(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
