package com.samwellstore.librarymanagement.DTOs;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String errorCode;
    private String details;
    private String path;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public static ErrorResponse of(String message, String details, String path, String errorCode) {
        return ErrorResponse.builder()
                .message(message)
                .details(details)
                .path(path)
                .errorCode(errorCode)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
