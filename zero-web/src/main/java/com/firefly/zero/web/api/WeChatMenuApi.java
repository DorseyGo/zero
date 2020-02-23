/**
 * File: WeChatMenuApi
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 10:20
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.api;

import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.struct.Menu;
import com.firefly.zero.web.model.MenuMatchRule;
import com.firefly.zero.web.request.CreateCustomizedMenuRequest;
import com.firefly.zero.web.request.DeletePersonalizedMenuRequest;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("weChatMenuApi")
public class WeChatMenuApi extends WeChatApi implements WeChatCreationApi<Void, CreateCustomizedMenuRequest>,
        WeChatDeleteApi<Void, DeletePersonalizedMenuRequest>, WeChatDeleteAllApi<Void> {

    static final Logger logger = LoggerFactory.getLogger(WeChatMenuApi.class);

    @Override
    public Void create(final CreateCustomizedMenuRequest request) throws ApiInvocationException {
        final WxMenu wxMenu = fromRequest(request);

        try {
            newWxMpService().menuCreate(wxMenu);
            return null;
        } catch (WxErrorException e) {
            logger.error("Failed to create menu with request: {}", request, e);
            throw newApiInvocationException(e);
        }
    }

    private WxMenu fromRequest(final CreateCustomizedMenuRequest request) {
        WxMenu menu = new WxMenu();
        menu.setMatchRule(matchRuleOf(request.getMatchRule()));
        menu.setButtons(buttonsOf(request.getMenus()));

        return menu;
    }

    private List<WxMenu.WxMenuButton> buttonsOf(final List<Menu> menus) {
        final List<WxMenu.WxMenuButton> buttons = Lists.newArrayListWithExpectedSize(menus.size());
        WxMenu.WxMenuButton wxMenuButton = null;
        for (Menu menu : menus) {
            wxMenuButton = new WxMenu.WxMenuButton();
            menuButtonOf(menu, wxMenuButton);

            buttons.add(wxMenuButton);
        }

        return buttons;
    }

    private void menuButtonOf(final Menu menu, final WxMenu.WxMenuButton wxMenuButton) {
        wxMenuButton.setKey(menu.getKey());
        wxMenuButton.setName(menu.getName());
        wxMenuButton.setType(menu.getType());
        wxMenuButton.setUrl(menu.getUrl());

        if (menu.getSubButtons() != null && !menu.getSubButtons().isEmpty()) {
            wxMenuButton.setSubButtons(buttonsOf(menu.getSubButtons()));
        }
    }

    private WxMenu.WxMenuRule matchRuleOf(MenuMatchRule matchRule) {
        final WxMenu.WxMenuRule rule = new WxMenu.WxMenuRule();
        rule.setGroupId(String.valueOf(matchRule.getGroupId()));
        rule.setSex(String.valueOf(matchRule.getGender()));
        rule.setCountry(matchRule.getCountry());
        rule.setProvince(matchRule.getProvince());
        rule.setCity(matchRule.getCity());
        rule.setClientPlatformType(String.valueOf(matchRule.getPlatformType()));

        return rule;
    }

    @Override
    public Void deletAll() throws ApiInvocationException {
        try {
            newWxMpService().menuDelete();
            return null;
        } catch (WxErrorException e) {
            logger.error("Failed to delete all customized menu", e);
            throw newApiInvocationException(e);
        }
    }

    @Override
    public Void delete(final DeletePersonalizedMenuRequest request) throws ApiInvocationException {
        try {
            newWxMpService().menuDelete(request.getMenuId());
            return null;
        } catch (WxErrorException e) {
            logger.error("Failed to request to wechat endpoint for deleting personalized menu: {}", request.getMenuId(),
                    e);
            throw newApiInvocationException(e);
        }
    }
}
