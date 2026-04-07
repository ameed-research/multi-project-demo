package com.ameed.demo1.controllers;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareServiceClient;
import com.azure.storage.file.share.models.ShareFileItem;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class MyController {

    private final ShareServiceClient shareServiceClient;

    @GetMapping
    public Map<String, Object> listFilesInShare() {
        ShareClient shareClient = shareServiceClient.getShareClient("carpentry-demo-fileshare");
        return shareClient.getRootDirectoryClient()
                .listFilesAndDirectories()
                .stream().collect(Collectors.toMap(
                        ShareFileItem::getName,
                        fileRef -> fileRef.isDirectory() ? "Directory" : "File"
                ));
    }

    @PostConstruct
    public void init() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> STORAGE_ACCOUNT_NAME = {}", System.getenv("STORAGE_ACCOUNT_NAME"));
    }
}
