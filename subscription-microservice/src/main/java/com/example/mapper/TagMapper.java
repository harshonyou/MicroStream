package com.example.mapper;

import com.example.dto.TagDTO;
import com.example.model.Tag;

public class TagMapper {
    public static Tag fromDTO(TagDTO tagDTO) {
        Tag t = new Tag();
        t.setName(tagDTO.getName());
        return t;
    }

    public static TagDTO fromEntity(Tag tag) {
        TagDTO dto = new TagDTO();
        dto.setName(tag.getName());
        return dto;
    }
}
