package org.winter.admin.controller.monitor;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.winter.common.annotation.LogOperation;
import org.winter.common.domain.PageDomain;
import org.winter.common.rest.Result;
import org.winter.monitor.domain.Log;
import org.winter.monitor.service.LogService;
import org.winter.monitor.service.criteria.LogCriteria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/monitor/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @PreAuthorize("@permissionService.hasPermission('monitor:log:query')")
    @GetMapping("/list")
    public Result list(LogCriteria log) {
        Page<Log> page = logService.findList(log);
        PageDomain<Log> pageDomain = new PageDomain<>();
        return Result.success(pageDomain.toPageDomain(page));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:log:delete')")
    @DeleteMapping("/{ids}")
    public Result deleteBatch(@PathVariable List<Long> ids) {
        if (logService.deleteBatch(ids) > 0) {
            return Result.success("删除成功!");
        }
        return Result.success("删除失败!");
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:log:export')")
    @GetMapping("/export")
    public void exportExcel(LogCriteria log, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=user_list.xls");
        OutputStream out = response.getOutputStream();
        List<Log> content = logService.findList(log).getContent();
        List<Log> list = new ArrayList<>(content);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Log.class, list);
        workbook.write(out);
        out.flush();
        out.close();
    }

}
