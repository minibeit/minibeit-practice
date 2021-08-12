package com.miniprac.file.domain;

import lombok.Getter;

@Getter
public enum FileServer {
    DISK,
    S3,
    EXTERNAL;
}
