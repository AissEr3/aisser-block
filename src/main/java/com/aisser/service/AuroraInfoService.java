package com.aisser.service;

import com.aisser.model.dto.AboutDTO;
import com.aisser.model.dto.AuroraAdminInfoDTO;
import com.aisser.model.dto.AuroraHomeInfoDTO;
import com.aisser.model.dto.WebsiteConfigDTO;
import com.aisser.model.vo.AboutVO;
import com.aisser.model.vo.WebsiteConfigVO;

public interface AuroraInfoService {

    void report();

    AuroraHomeInfoDTO getAuroraHomeInfo();

    AuroraAdminInfoDTO getAuroraAdminInfo();

    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    WebsiteConfigDTO getWebsiteConfig();

    void updateAbout(AboutVO aboutVO);

    AboutDTO getAbout();

}
