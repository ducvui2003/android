package com.example.truyenapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardPointResponse {
    private Integer dateAttendanceContinuous;
    private Double totalPoint;
}
