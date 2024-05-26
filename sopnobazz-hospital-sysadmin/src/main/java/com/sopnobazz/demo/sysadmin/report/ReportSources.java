package com.sopnobazz.demo.sysadmin.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Component
public class ReportSources {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ReportSources.class);

    //..........
    @Value("${report.source.dir}")
    public String REPORT_SOURCE_DIR;

    @Value("${report.source.subreport.dir}")
    public String REPORT_SOURCE_SUBREPORT_DIR;

    @Value("${report.compile.dir}")
    public String REPORT_COMPILE_DIR;

    @Value("${report.image.dir}")
    public String REPORT_IMAGE_DIR;

    @Value("${report.output.dir}")
    public String REPORT_OUTPUT_DIR;
    ;

    //............
    //Image classpath file name
    public final String IBCS_LOGO = "ibcs-logo.png";
    public final String BOF_LOGO = "bof-logo.png";


    //.........
    public String getSourceReport(String filename) {
        return String.format("%s%s", REPORT_SOURCE_DIR, filename);
    }


    public String getSourceSubReport(String filename) {
        return String.format("%s%s", REPORT_SOURCE_SUBREPORT_DIR, filename);
    }

    public String getCompiledReport(String filename) {
        return String.format("%s%s", REPORT_COMPILE_DIR, filename);
    }

    public String getImage(String image) {
        return String.format("%s%s", REPORT_IMAGE_DIR, image);
    }

    public String getOutputReport(String filename) {
        return String.format("%s%s", REPORT_OUTPUT_DIR, filename);
    }

}
