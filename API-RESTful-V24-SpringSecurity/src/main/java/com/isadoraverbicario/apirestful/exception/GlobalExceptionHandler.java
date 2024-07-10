package com.isadoraverbicario.apirestful.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleEntidadeNaoEncontrada(
            EntidadeNaoEncontradaException e, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntidadeDestacadaException.class)
    public ResponseEntity<ErrorResponse> handleEntidadeDestacada(
            EntidadeDestacadaException e, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntidadeTransienteException.class)
    public ResponseEntity<ErrorResponse> handleEntidadeTransiente(
            EntidadeTransienteException e, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException e, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            map.put(cv.getPropertyPath().toString(), cv.getMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                request.getMethod(),
                request.getRequestURI(),
                map,
                e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> handleLogin(
            LoginException e, HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage()), HttpStatus.BAD_REQUEST);
        // 400 Bad Request - quando falta a conta ou a senha.
        // 401 Unauthorized - conta existente mas senha inválida.
        // 403 Forbidden - autenticado, mas falta permissão para acessar o recurso.
        // 422 Unprocessable Entity - conta informada, mas não existe no bd.
    }

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioJaCadastrado(
            UsuarioJaCadastradoException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // InternalAuthenticationServiceException e BadCredentialsException são
    // subclasses de AuthenticationException.

    // Conta e senha erradas ou conta e senha não informadas
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> handleInternalAuthenticationService(
            InternalAuthenticationServiceException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Conta existente, senha errada
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(
            BadCredentialsException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Quando o usuario, sem efetuar login consegue submeter um
    // formulário para cadastro de alguma informação.
    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<ErrorResponse> handleJWTDecode(
            JWTDecodeException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                "Não foi enviado ao servidor um token de autorização.");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponse> handleJWTVerification(
            JWTVerificationException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(),
                request.getMethod(),
                request.getRequestURI(),
                null,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
