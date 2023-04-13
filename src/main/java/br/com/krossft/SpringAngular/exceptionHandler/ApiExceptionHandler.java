package br.com.krossft.SpringAngular.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErrorApi.Campo> campos = new ArrayList<>();

        for (ObjectError er: ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) er).getField();
            String mensagem = er.getDefaultMessage();
            campos.add(new ErrorApi.Campo(nome,mensagem));
        }

        ErrorApi errorApi = new ErrorApi();
        errorApi.setStatus(status.value());
        errorApi.setDataHora(LocalDateTime.now());
        errorApi.setTitulo("um o mais campos com erro");
        errorApi.setCampos(campos);
        return handleExceptionInternal(ex,errorApi,headers,status,request);
    }
}
