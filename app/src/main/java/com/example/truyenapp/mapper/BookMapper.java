package com.example.truyenapp.mapper;

import com.example.truyenapp.model.ModelSearch;
import com.example.truyenapp.response.BookResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "view", target = "view")
    @Mapping(source = "quantityChapter", target = "chapter")
    @Mapping(source = "rating", target = "evaluate")
    @Mapping(source = "name", target = "nameStory")
    @Mapping(source = "thumbnail", target = "linkImage")
    ModelSearch bookResponseToModelSearch(BookResponse bookResponse);
}
