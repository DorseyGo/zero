/**
 * File: MiniAdminConfig
 * Author: DorSey Q F TANG
 * Created: 2020/3/12
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "zero.mini.admin")
public class MiniAdminConfig {
    private String imageServerUrl;

    /**
     * Default constructor.
     */
    public MiniAdminConfig() {}

    public String getImageServerUrl() {
        return imageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.imageServerUrl = imageServerUrl;
    }
}
