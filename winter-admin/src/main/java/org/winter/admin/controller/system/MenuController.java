package org.winter.admin.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.winter.common.annotation.LogOperation;
import org.winter.common.rest.Result;
import org.winter.system.domain.Menu;
import org.winter.system.domain.vo.TreeVo;
import org.winter.system.service.MenuService;
import org.winter.system.service.dto.MenuDto;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping("/list")
    public Result list() {
        List<MenuDto> menuDtos = menuService.buildTree(menuService.findAll());
        return Result.success(menuDtos);
    }

    @LogOperation("新增菜单")
    @PreAuthorize("@permissionService.hasPermission('system:menu:add')")
    @PostMapping
    public Result insert(@RequestBody Menu menu) {
        if (menuService.insert(menu) != null) {
            return Result.success("新增成功!");
        }
        return Result.error("新增失败!");
    }

    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        return Result.success(menuService.findById(id));
    }

    @GetMapping("/treeselect")
    public Result treeselect() {
        List<TreeVo> menuOptions = TreeVo.buildTreeselect(menuService.buildTree(menuService.findAll()));
        return Result.success(menuOptions);
    }

    @LogOperation("更新菜单")
    @PreAuthorize("@permissionService.hasPermission('system:menu:edit')")
    @PutMapping
    public Result update(@RequestBody Menu menu) {
        if (menuService.update(menu) != null) {
            return Result.success("更新成功!");
        }
        return Result.error("更新失败!");
    }

    @LogOperation("删除菜单")
    @PreAuthorize("@permissionService.hasPermission('system:menu:delete')")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        if (menuService.hasChildren(id)) {
            return Result.error("存在子类目,无法删除!");
        }
        menuService.delete(id);
        return Result.success("删除成功!");
    }

}
