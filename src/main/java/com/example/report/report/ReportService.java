package com.example.report.report;

import com.example.report.domain.OS;
import com.example.report.domain.Report;
import com.example.report.domain.Server;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    public Report generateReport(List<Server> servers) {
        return Report.builder()
                .linux(servers.stream().filter(server -> server.getOs() == OS.LINUX).count())
                .windows(servers.stream().filter(server -> server.getOs() == OS.WINDOWS).count())
                .bsd(servers.stream().filter(server -> server.getOs() == OS.BSD).count())
                .other(servers.stream().filter(server -> server.getOs() == OS.OTHER).count())
                .build();

    }
}
