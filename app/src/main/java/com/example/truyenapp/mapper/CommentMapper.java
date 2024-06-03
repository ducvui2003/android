package com.example.truyenapp.mapper;

import com.example.truyenapp.model.Comment;
import com.example.truyenapp.response.CommentResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "email", source = "user.email")
    Comment commentResponseToComment(CommentResponse bookResponse);
}
