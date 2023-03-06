package com.sola.extractor.service;

import com.sola.extractor.dto.DataDTO;
import com.sola.extractor.dto.FaturamentoEstadoDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class DesafiosService {

    private final ParseService parseService;

    public String fibonacci(Integer numero) {
        int a = 0;
        int b = 1;
        int c = 0;
        while (numero > c) {
            c = a + b;
            a = b;
            b = c;
        }
        return numero == c
                ? "O número " + numero + " pertence a sequência de Fibonacci"
                : "O número " + numero + " não pertence a sequência de Fibonacci";
    }

    public List<DataDTO> getFaturamento() {
        List<DataDTO> xmlData = parseService.xmlToObject("dados.xml");
        List<DataDTO> jsonData = parseService.jsonToObject("dados.json");
        List<DataDTO> faturamento = new ArrayList<>();
        for (DataDTO json : jsonData) {
            for (DataDTO xml : xmlData) {
                if (Objects.equals(json.getDia(), xml.getDia())) {
                    double sum = json.getValor() + xml.getValor();
                    if (sum == 0.0) continue;
                    faturamento.add(new DataDTO(json.getDia(), sum));
                }
            }
        }
        return faturamento;
    }

    public Double menorFaturamento() {
        return getFaturamento().stream()
                .map(DataDTO::getValor)
                .min(Double::compareTo).orElse(null);
    }

    public Double maiorFaturamento() {
        return getFaturamento().stream()
                .map(DataDTO::getValor)
                .max(Double::compareTo).orElse(null);
    }


    public Double getMediaFaturamento() {
        return getFaturamento().stream()
                .mapToDouble(DataDTO::getValor)
                .average().orElse(0.0);
    }

    public Integer getDiasMediaDiariaMiorQueMensal() {
        return (int) getFaturamento().stream().filter(obj -> obj.getValor() > getMediaFaturamento()).count();
    }

    public String inverterString(String string) {
        StringBuilder reversed = new StringBuilder();
        int i = string.length();
        while (i > 0) {
            reversed.append(string.charAt(i - 1));
            i--;
        }
        return reversed.toString();
    }

    public void getFaturamentoTotal(List<FaturamentoEstadoDTO> faturamentoPorEstado) {
        double total = faturamentoPorEstado.stream().mapToDouble(FaturamentoEstadoDTO::getValor).sum();
        for (FaturamentoEstadoDTO estadoDTO : faturamentoPorEstado) {
            log.info(String.format("Estado: %s, Percentual: %.2f",estadoDTO.getEstado(),estadoDTO.getValor() / total * 100));
        }
    }
}
