package com.company.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class DeserializeService {
    Logger LOGGER = LoggerFactory.getLogger(DeserializeService.class);

    public <T> T getObjectOfResources(String pathFile, Class<T> type) {
        XmlMapper xmlMapper = new XmlMapper();
        InputStream inputStream = this.getClass().getResourceAsStream(pathFile);
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            int i = -1;
            while ((i = isr.read()) != -1) {
                sb.append((char) i);
            }
            return xmlMapper.readValue(sb.toString(), type);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public <T> T getObjectFromFile(MultipartFile file, Class<T> type) {
        XmlMapper xmlMapper = new XmlMapper();
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            int i = -1;
            while ((i = isr.read()) != -1) {
                sb.append((char) i);
            }
            return xmlMapper.readValue(sb.toString(), type);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }
}
