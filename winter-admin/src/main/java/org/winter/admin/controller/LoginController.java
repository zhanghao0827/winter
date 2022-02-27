package org.winter.admin.controller;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.winter.common.rest.Result;
import org.winter.common.util.CaptchaUtils;
import org.winter.common.util.SecurityUtils;
import org.winter.core.security.config.bean.LoginBody;
import org.winter.core.security.model.SysUserDetails;
import org.winter.core.security.service.JwtAuthenticationService;
import org.winter.system.domain.vo.MetaVo;
import org.winter.system.domain.vo.RouteVo;
import org.winter.system.service.MenuService;
import org.winter.system.service.dto.MenuDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @Autowired
    private MenuService menuService;

    // 这个方法用于在登录后登录验证后返回token
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody) {
        String token;
        try {
            token = jwtAuthenticationService.login(loginBody);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        Map<String, Object> map = Result.getMap();
        map.put("token", token);
        return Result.success(map);
    }

    @GetMapping("/captcha")
    public void generateCaptcha() {
        CaptchaUtils.generateCaptcha();
    }

    @GetMapping("/getInfo")
    public Result getInfo() {
        SysUserDetails userDetails = (SysUserDetails) SecurityUtils.getAuthentication().getPrincipal();
        if (ObjectUtil.isNotNull(userDetails)) {
            return Result.success(userDetails);
        } else {
            return Result.error();
        }
    }

    @GetMapping("/getRoutes")
    public Result getRoutes() {
        SysUserDetails userDetails = (SysUserDetails) SecurityUtils.getAuthentication().getPrincipal();
        List<MenuDto> menuDtoList = menuService.findByUserId(userDetails.getUser().getId());
        List<MenuDto> tree = menuService.buildTree(menuDtoList);
        return Result.success(buildRouters(tree));
    }

    private List<RouteVo> buildRouters(List<MenuDto> menus) {
        List<RouteVo> routers = new LinkedList<>();
        for (MenuDto menu : menus) {
            RouteVo router = new RouteVo();
            router.setName(menu.getName());
            router.setPath(menu.getPath());
            router.setComponent(menu.getComponent());
            router.setRedirect(menu.getRedirect());
            router.setMeta(new MetaVo(menu.getTitle(), menu.getIcon()));
            List<MenuDto> menuChildren = menu.getChildren();
            if (menuChildren != null && menuChildren.size() > 0) {
                router.setChildren(buildRouters(menuChildren));
            }
            routers.add(router);
        }
        return routers;
    }
}