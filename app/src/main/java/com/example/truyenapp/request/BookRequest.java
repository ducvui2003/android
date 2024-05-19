package com.example.truyenapp.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    Integer id;
    String name;
    String author;
    String description;
    String thumbnail;
    List<String> categoryNames;
    String status;


}
