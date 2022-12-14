package com.example.demo.models.errors;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class InvalidRequestExceptionResponse
{
    private int status;
    private String message;

    public InvalidRequestExceptionResponse(InvalidRequestException ex)
    {
        this.status = ex.getStatus();
        this.message = ex.getMessage();
    }

    public InvalidRequestExceptionResponse(int status, String message)
    {
        this.status = status;
        this.message = message;
    }
}
