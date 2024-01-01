package com.aisser.service;

import com.aisser.model.dto.CategoryAdminDTO;
import com.aisser.model.dto.CategoryDTO;
import com.aisser.model.dto.CategoryOptionDTO;
import com.aisser.model.entity.Category;
import com.aisser.model.vo.CategoryVO;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {

    List<CategoryDTO> listCategories();

    PageResultDTO<CategoryAdminDTO> listCategoriesAdmin(ConditionVO conditionVO);

    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO conditionVO);

    void deleteCategories(List<Integer> categoryIds);

    void saveOrUpdateCategory(CategoryVO categoryVO);

}
