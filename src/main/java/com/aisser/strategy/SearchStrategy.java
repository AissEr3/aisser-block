package com.aisser.strategy;

import com.aisser.model.dto.ArticleSearchDTO;

import java.util.List;

public interface SearchStrategy {

    List<ArticleSearchDTO> searchArticle(String keywords);

}
