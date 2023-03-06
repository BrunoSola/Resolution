package com.sola.extractor;

import com.sola.extractor.dto.FaturamentoEstadoDTO;
import com.sola.extractor.service.DesafiosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ExtractorApplication implements CommandLineRunner {
    private final DesafiosService desafiosService;

    public static void main(String[] args) {
        SpringApplication.run(ExtractorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("");
        log.info("##### EXERCICIO 2 #####");
        log.info("FIBONACCI: {}",desafiosService.fibonacci(89));
        log.info("");

        log.info("##### EXERCICIO 3 #####");
        log.info(String.format("Maior faturamento: %.2f",desafiosService.maiorFaturamento()));
        log.info(String.format("Menor faturamento: %.2f",desafiosService.menorFaturamento()));
        log.info(String.format("Média mensal: %.2f",desafiosService.getMediaFaturamento()));
        log.info("Dias média diária maior que mensal: {}",desafiosService.getDiasMediaDiariaMiorQueMensal());
        log.info("");

        log.info("##### EXERCICIO 4 #####");
        List<FaturamentoEstadoDTO> faturamentoPorEstado = List.of(
                new FaturamentoEstadoDTO("SP", 67836.43),
                new FaturamentoEstadoDTO("RJ", 36678.66),
                new FaturamentoEstadoDTO("MG", 29229.88),
                new FaturamentoEstadoDTO("ES", 27165.48),
                new FaturamentoEstadoDTO("Outros", 19849.53)
        );
        log.info(String.format("Faturamento total: %.2f",faturamentoPorEstado.stream().mapToDouble(FaturamentoEstadoDTO::getValor).sum()));
        log.info("Faturamento por estado: ");
        desafiosService.getFaturamentoTotal(faturamentoPorEstado);
        log.info("");

        log.info("##### EXERCICIO 5 #####");
        log.info("Inverter String: {}",desafiosService.inverterString("Java"));
    }
}
