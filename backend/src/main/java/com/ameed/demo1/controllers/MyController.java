package com.ameed.demo1.controllers;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareServiceClient;
import com.azure.storage.file.share.models.ShareFileItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class MyController {

    private final ShareServiceClient shareServiceClient;

    @GetMapping
    public Map<String, Object> listFilesInShare(String shareName) {
        ShareClient shareClient = shareServiceClient.getShareClient(shareName);
        return shareClient.getRootDirectoryClient()
                .listFilesAndDirectories()
                .stream().collect(Collectors.toMap(
                        ShareFileItem::getName,
                        fileRef -> fileRef.isDirectory() ? "Directory" : "File"
                ));
    }
}
