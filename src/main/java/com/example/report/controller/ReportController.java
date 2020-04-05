package com.example.report.controller;

import com.example.report.conf.EndpointConfiguration;
import com.example.report.conf.RestConfiguration;
import com.example.report.converter.ReportConverter;
import com.example.report.converter.ServerConverter;
import com.example.report.domain.Log;
import com.example.report.dto.ReportDTO;
import com.example.report.dto.ServerDTO;
import com.example.report.producer.LogProducer;
import com.example.report.report.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(EndpointConfiguration.REPORT_BASE_URL)
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    private static final String LOG_GET = "action=getReport user=%s";

    private static final String SERVICE_NAME = "report";
    private static final String DEFAULT_USER = "public";

    private final ReportService service;
    private final RestTemplate restTemplate;
    private final RestConfiguration configuration;
    private final LogProducer logProducer;

    public ReportController(ReportService service, RestTemplate restTemplate,
                            RestConfiguration configuration, LogProducer logProducer) {
        this.service = service;
        this.restTemplate = restTemplate;
        this.configuration = configuration;
        this.logProducer = logProducer;
    }

    @GetMapping
    public ResponseEntity<ReportDTO> getAll() throws URISyntaxException {
        String logContent = String.format(LOG_GET, DEFAULT_USER);
        LOGGER.info(logContent);
        logProducer.send(new Log(SERVICE_NAME, logContent));
        return new ResponseEntity<>(
                ReportConverter.fromEntity(
                        service.generateReport(
                                ServerConverter.toEntityList(
                                        restTemplate.getForEntity(getEndpoint(), ServerDTO[].class).getBody(),
                                        ServerConverter::toEntity))),
                HttpStatus.OK);

    }

    private URI getEndpoint() throws URISyntaxException {
        return new URI(configuration.url() + EndpointConfiguration.SERVER_BASE_URL);
    }
}
