package com.example.report.dto;

import com.example.report.domain.OS;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerDTO {

    private Long id;
    private String hostname;
    private String domain;
    private OS os;
}
