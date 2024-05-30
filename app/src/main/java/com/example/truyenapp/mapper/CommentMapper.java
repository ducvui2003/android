package com.example.truyenapp.mapper;

import com.example.truyenapp.model.Comment;
import com.example.truyenapp.response.CommentResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "idAccount")
    @Mapping(source = "bookName", target = "bookName")
    @Mapping(source = "chapterNumber", target = "chapterNumber")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "state", target = "state")
    Comment commentResponseToComment(CommentResponse bookResponse);
}
