package com.aurora.enums;

import com.aurora.constant.FileUploadPathConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum {
    AVATAR(FileUploadPathConstant.PREFIX+"avatar/", "头像路径"),

    ARTICLE(FileUploadPathConstant.PREFIX+"articles/", "文章图片路径"),

    VOICE(FileUploadPathConstant.PREFIX+"voice/", "音频路径"),

    PHOTO(FileUploadPathConstant.PREFIX+"photos/", "相册路径"),

    CONFIG(FileUploadPathConstant.PREFIX+"config/", "配置图片路径"),

    TALK(FileUploadPathConstant.PREFIX+"talks/", "配置图片路径"),

    MD(FileUploadPathConstant.PREFIX+"markdown/", "md文件路径");

    private final String path;

    private final String desc;


}
