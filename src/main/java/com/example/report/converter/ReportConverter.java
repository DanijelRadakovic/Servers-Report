package com.example.report.converter;

import com.example.report.domain.Report;
import com.example.report.dto.ReportDTO;

public class ReportConverter extends AbstractConverter {

    public static ReportDTO fromEntity(Report report) {
        return ReportDTO.builder()
                .linux(report.getLinux())
                .windows(report.getWindows())
                .bsd(report.getBsd())
                .other(report.getOther())
                .build();
    }

    public static Report toEntity(ReportDTO report) {
        return Report.builder()
                .linux(report.getLinux())
                .windows(report.getWindows())
                .bsd(report.getBsd())
                .other(report.getOther())
                .build();
    }
}
