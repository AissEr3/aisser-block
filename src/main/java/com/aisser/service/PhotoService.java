package com.aisser.service;

import com.aisser.model.dto.PageResultDTO;
import com.aisser.model.dto.PhotoAdminDTO;
import com.aisser.model.dto.PhotoDTO;
import com.aisser.model.entity.Photo;
import com.aisser.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PhotoService extends IService<Photo> {

    PageResultDTO<PhotoAdminDTO> listPhotos(ConditionVO conditionVO);

    void updatePhoto(PhotoInfoVO photoInfoVO);

    void savePhotos(PhotoVO photoVO);

    void updatePhotosAlbum(PhotoVO photoVO);

    void updatePhotoDelete(DeleteVO deleteVO);

    void deletePhotos(List<Integer> photoIds);

    PhotoDTO listPhotosByAlbumId(Integer albumId);

}
