package dev.nathan.studentmanagementv1.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalExceptionResponse {

    private int status;
    private String message;
    private long timestamp;

}
