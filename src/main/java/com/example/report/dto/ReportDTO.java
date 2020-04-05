package com.example.report.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {

    private long linux;
    private long windows;
    private long bsd;
    private long other;
}
