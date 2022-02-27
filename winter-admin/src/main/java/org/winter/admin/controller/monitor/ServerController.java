package org.winter.admin.controller.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winter.common.rest.Result;
import org.winter.monitor.service.ServerService;

@RequestMapping("/monitor/server")
@RestController
public class ServerController {

    @Autowired
    private ServerService serverService;

    @GetMapping
    public Result server() {
        return Result.success(serverService.getServer());
    }


}
