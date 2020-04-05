package com.example.report.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    private long linux;
    private long windows;
    private long bsd;
    private long other;
}
