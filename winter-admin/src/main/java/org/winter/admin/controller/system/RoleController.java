package org.winter.admin.controller.system;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.util.ObjectUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.winter.common.annotation.LogOperation;
import org.winter.common.domain.PageDomain;
import org.winter.common.rest.Result;
import org.winter.system.domain.Role;
import org.winter.system.domain.vo.TreeVo;
import org.winter.system.service.MenuService;
import org.winter.system.service.RoleService;
import org.winter.system.service.criteria.RoleCriteria;
import org.winter.system.service.dto.MenuDto;
import org.winter.system.service.dto.RoleDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @PreAuthorize("@permissionService.hasPermission('system:role:query')")
    @GetMapping("/list")
    public Result list(RoleCriteria role) {
        Page<RoleDto> page = roleService.findList(role);
        PageDomain<RoleDto> pageDomain = new PageDomain<>();
        return Result.success(pageDomain.toPageDomain(page));
    }

    @PreAuthorize("@permissionService.hasPermission('system:role:query')")
    @GetMapping(value = {"/", "/{id}"})
    public Result findById(@PathVariable(value = "id", required = false) Long id) {
        Map<String, Object> map = Result.getMap();
        List<TreeVo> menuOptions = TreeVo.buildElTree(menuService.buildTree(menuService.findAll()));
        List<Long> allId = menuService.findAllId();
        map.put("menuOptions", menuOptions);
        map.put("menuIds", allId);
        if (ObjectUtil.isNotNull(id)) {
            RoleDto roleDto = roleService.findById(id);
            List<Long> checkedKeys = roleDto.getMenus().stream().map(MenuDto::getId).collect(Collectors.toList());
            // 清空角色对应菜单，方便前端给form赋值，防止干扰
            roleDto.setMenus(new ArrayList<>());
            map.put("role", roleDto);
            map.put("checkedKeys", checkedKeys);
        }
        return Result.success(map);
    }

    @LogOperation("新增角色")
    @PreAuthorize("@permissionService.hasPermission('system:role:add')")
    @PostMapping
    public Result insert(@RequestBody Role role) {
        Role i = roleService.insert(role);
        if (ObjectUtil.isNotNull(i)) {
            return Result.success("添加成功！");
        }
        return Result.error("添加失败");
    }

    @LogOperation("更新角色")
    @PreAuthorize("@permissionService.hasPermission('system:role:edit')")
    @PutMapping
    public Result update(@RequestBody Role role) {
        Role i = roleService.update(role);
        if (ObjectUtil.isNotNull(i)) {
            return Result.success("更新成功！");
        }
        return Result.error("更新失败");
    }

    @LogOperation("删除角色")
    @PreAuthorize("@permissionService.hasPermission('system:role:delete')")
    @DeleteMapping("/{ids}")
    public Result deleteBatch(@PathVariable List<Long> ids) {
        if (roleService.deleteBatch(ids) > 0) {
            return Result.success("删除成功!");
        }
        return Result.success("删除失败!");
    }

    @LogOperation("导出角色")
    @PreAuthorize("@permissionService.hasPermission('system:role:export')")
    @GetMapping("/export")
    public void exportExcel(RoleCriteria role, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=role_list.xls");
        OutputStream out = response.getOutputStream();
        List<RoleDto> content = roleService.findList(role).getContent();
        List<RoleDto> list = new ArrayList<>(content);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), RoleDto.class, list);
        workbook.write(out);
        out.flush();
        out.close();
    }


}
