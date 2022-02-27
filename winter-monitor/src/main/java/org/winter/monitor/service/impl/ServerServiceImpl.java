package org.winter.monitor.service.impl;

import org.springframework.stereotype.Service;
import org.winter.monitor.service.ServerService;
import org.winter.monitor.service.dto.Server;

@Service
public class ServerServiceImpl implements ServerService {

    @Override
    public Server getServer() {
        Server server = new Server();
        server.init();
        return server;
    }
}
