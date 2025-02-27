package com.aisser.service;

import com.aisser.model.dto.PhotoAlbumAdminDTO;
import com.aisser.model.dto.PhotoAlbumDTO;
import com.aisser.model.entity.PhotoAlbum;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.dto.PageResultDTO;
import com.aisser.model.vo.PhotoAlbumVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PhotoAlbumService extends IService<PhotoAlbum> {

    void saveOrUpdatePhotoAlbum(PhotoAlbumVO photoAlbumVO);

    PageResultDTO<PhotoAlbumAdminDTO> listPhotoAlbumsAdmin(ConditionVO condition);

    List<PhotoAlbumDTO> listPhotoAlbumInfosAdmin();

    PhotoAlbumAdminDTO getPhotoAlbumByIdAdmin(Integer albumId);

    void deletePhotoAlbumById(Integer albumId);

    List<PhotoAlbumDTO> listPhotoAlbums();

}
