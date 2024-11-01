package org.example.repository;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TemplateRepository {

    @SuppressWarnings("DataFlowIssue")
    public static String getStringFromTemplate(String templateName) {
        try {
            return IOUtils.toString(TemplateRepository.class.getResource("/templates/" + templateName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
