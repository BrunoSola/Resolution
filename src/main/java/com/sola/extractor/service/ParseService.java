package com.sola.extractor.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sola.extractor.dto.DataDTO;
import com.sola.extractor.exceptions.ParseException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ParseService {
    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    public List<DataDTO> jsonToObject(String filename) {
        try {
            return objectMapper.readValue(new ClassPathResource(filename).getInputStream(),  new TypeReference<>() {});
        } catch (IOException e) {
            throw new ParseException("Erro ao ler arquivo JSON", e);
        }
    }

    public List<DataDTO> xmlToObject(String filename){
        List<InputStream> streams;
        try {
            streams = Arrays.asList(
                    new ByteArrayInputStream("<root>".getBytes()),
                    new ClassPathResource(filename).getInputStream(),
                    new ByteArrayInputStream(("</root>".getBytes())));
        } catch (IOException e) {
            throw new ParseException("Erro ao ler converter XML",e);
        }
        InputStream dados = new SequenceInputStream(Collections.enumeration(streams));
        try {
            return xmlMapper.readValue(dados,  new TypeReference<>() {});
        } catch (IOException e) {
            throw new ParseException("Erro ao ler converter XML",e);
        }
    }
}
