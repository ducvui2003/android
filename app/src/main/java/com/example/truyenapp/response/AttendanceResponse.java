package com.example.truyenapp.response;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceResponse {
    private Double point;
    private Date date;
}
