package br.com.fiap.food.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.Instant;

public class StandardError {

    public static ProblemDetail create(
            HttpStatus status,
            String title,
            String detail,
            String path
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);

        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);

        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}