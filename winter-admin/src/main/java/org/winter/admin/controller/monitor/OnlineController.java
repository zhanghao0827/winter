package org.winter.admin.controller.monitor;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.winter.common.annotation.LogOperation;
import org.winter.common.rest.Result;
import org.winter.common.util.PageUtils;
import org.winter.monitor.service.OnlineService;
import org.winter.monitor.service.criteria.OnlineCriteria;
import org.winter.monitor.service.dto.Online;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RequestMapping("/monitor/online")
@RestController
public class OnlineController {

    @Autowired
    private OnlineService onlineService;

    @PreAuthorize("@permissionService.hasPermission('monitor:online:query')")
    @GetMapping("/list")
    public Result list(OnlineCriteria online) {
        List<Online> onlineDtoList = onlineService.getOnlineDtoList(online);
        Pageable pageable = PageUtils.startPage();
        Map<String, Object> map = PageUtils.toPage(pageable.getPageNumber(), pageable.getPageSize(), onlineDtoList, onlineDtoList.size());
        return Result.success(map);
    }

    @LogOperation("强退用户")
    @PreAuthorize("@permissionService.hasPermission('monitor:online:kickout')")
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<String> ids) {
        Long i = onlineService.deleteBatch(ids);
        if (i > 0) {
            return Result.success("强退成功！");
        }
        return Result.success("强退失败！");
    }

    @LogOperation("导出在线用户")
    @PreAuthorize("@permissionService.hasPermission('monitor:online:export')")
    @GetMapping("/export")
    public void exportExcel(OnlineCriteria online, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=online_list.xls");
        OutputStream out = response.getOutputStream();
        List<Online> list = onlineService.getOnlineDtoList(online);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Online.class, list);
        workbook.write(out);
        out.flush();
        out.close();
    }
}
