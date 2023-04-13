package br.com.krossft.SpringAngular.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorApi {

    private Integer status;

    private LocalDateTime dataHora;

    private String titulo;

    private List<Campo> campos;

    @AllArgsConstructor
    @Getter
    public static class Campo {
        private String nome;
        private String mensagem;
    }
}
