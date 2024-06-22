package com.example.truyenapp.response;

import com.example.truyenapp.enums.ExchangeStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {
    ExchangeStatus status;
    Double totalScore;
}
