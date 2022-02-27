package org.winter.admin.controller.system;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.ObjectUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.winter.common.annotation.LogOperation;
import org.winter.common.domain.PageDomain;
import org.winter.common.rest.Result;
import org.winter.system.domain.User;
import org.winter.system.domain.vo.PasswordVo;
import org.winter.system.domain.vo.UserTemplateVo;
import org.winter.system.service.RoleService;
import org.winter.system.service.UserService;
import org.winter.system.service.criteria.UserCriteria;
import org.winter.system.service.dto.RoleSimpleDto;
import org.winter.system.service.dto.UserDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("@permissionService.hasPermission('system:user:query')")
    @GetMapping("/list")
    public Result list(UserCriteria user) {
        Page<UserDto> page = userService.findList(user);
        PageDomain<UserDto> pageDomain = new PageDomain<>();
        return Result.success(pageDomain.toPageDomain(page));
    }

    @LogOperation("更新用户")
    @PreAuthorize("@permissionService.hasPermission('system:user:edit')")
    @PutMapping
    public Result update(@RequestBody User user) {
        User sysUser = userService.update(user);
        if (sysUser != null) {
            return Result.success("更新成功！");
        } else {
            return Result.error("更新失败！");
        }
    }

    @LogOperation("新增用户")
    @PreAuthorize("@permissionService.hasPermission('system:user:add')")
    @PostMapping
    public Result insert(@RequestBody User user) {
        if (userService.insert(user) != null) {
            return Result.success("新增成功!");
        }
        return Result.error("新增失败!");
    }

    /**
     * / -- 不穿任何参数，返回所有SysRoleSimpleDto集合
     * /{id} -- 接收用户主键id,如果传id，就根据id查询用户，返回用户对象和用户对应的角色id集合
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:query')")
    @GetMapping(value = {"/", "/{id}"})
    public Result findById(@PathVariable(value = "id", required = false) Long id) {
        Map<String, Object> map = Result.getMap();
        List<RoleSimpleDto> roles = roleService.findAllRoleSimpleDto();
        map.put("roleOptions", roles);
        if (ObjectUtil.isNotNull(id)) {
            UserDto user = userService.findUserById(id);
            map.put("user", user);
        }
        return Result.success(map);
    }

    @LogOperation("删除用户")
    @PreAuthorize("@permissionService.hasPermission('system:user:delete')")
    @DeleteMapping("/{ids}")
    public Result deleteBatch(@PathVariable List<Long> ids) {
        if (userService.deleteBatch(ids) > 0) {
            return Result.success("删除成功!");
        }
        return Result.success("删除失败!");
    }

    @LogOperation("更新用户状态")
    @PreAuthorize("@permissionService.hasPermission('system:user:edit')")
    @PutMapping("/enabled")
    public Result updateEnabled(@RequestBody User user) {
        if (userService.updateEnabled(user) > 0) {
            return Result.success("状态更新成功!");
        }
        return Result.success("状态更新失败!");
    }

    @PostMapping("/avatar")
    public Result updateAvatar(@RequestParam MultipartFile avatar) {
        User user = userService.updateAvatar(avatar);
        if (ObjectUtil.isNotNull(user)) {
            return Result.success("头像上传成功！");
        }
        return Result.success("头像上传失败！");
    }

    @LogOperation("更新用户密码")
    @PutMapping("/password")
    public Result updatePassword(@RequestBody PasswordVo password) {
        try {
            userService.updatePassword(password);
            return Result.success("密码修改成功，请重新登录！");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @LogOperation("导出用户")
    @PreAuthorize("@permissionService.hasPermission('system:user:export')")
    @GetMapping("/export")
    public void exportExcel(UserCriteria user, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=user_list.xls");//默认Excel名称
        OutputStream out = response.getOutputStream();
        List<UserDto> content = userService.findList(user).getContent();
        // 如果要进行操作，就要再转化一次 https://blog.csdn.net/bsmmaoshenbo/article/details/86590279 , https://blog.csdn.net/IM507/article/details/99677430
        List<UserDto> list = new ArrayList<>(content);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), UserDto.class, list);
        workbook.write(out);
        out.flush();
        out.close();
    }

    @GetMapping("/template")
    public void downLoadTemplate(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=user_list.xls");
        OutputStream out = response.getOutputStream();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), UserTemplateVo.class, new ArrayList<>());
        workbook.write(out);
        out.flush();
        out.close();
    }

    @LogOperation("导入用户")
    @PreAuthorize("@permissionService.hasPermission('system:user:import')")
    @PostMapping("/import")
    public Result importExcel(MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0);
        importParams.setHeadRows(1);
        List<UserDto> list = ExcelImportUtil.importExcel(file.getInputStream(), UserDto.class, importParams);
        int i = userService.insertBatch(list);
        if (i > 0) {
            return Result.success("导入成功!");
        }
        return Result.error("导入失败!");
    }

}