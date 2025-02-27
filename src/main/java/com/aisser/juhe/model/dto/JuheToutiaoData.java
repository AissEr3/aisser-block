package com.aisser.juhe.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JuheToutiaoData {
    private String Uniquekey;
    private String Title;
    private String Date;
    private String Category;
    private String AuthorName;
    private String Url;
    private String ThumbnailPicS;
    private String ThumbnailPicS02;
    private String ThumbnailPicS03;
    private String IsContent;
}
