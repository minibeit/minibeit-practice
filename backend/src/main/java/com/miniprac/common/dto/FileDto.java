package com.miniprac.common.dto;

import com.miniprac.file.domain.File;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileDto {
    private String url;

    public static FileDto build(File file){
        return FileDto.builder().url(file.getUrl()).build();
    }
}
