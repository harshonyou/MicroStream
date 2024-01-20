package com.example.mapper;

import com.example.dto.TagDTO;
import com.example.model.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagMapperTest {

    @Test
    public void testClassExists() {
        TagMapper tagMapper = new TagMapper();
        assertNotNull(tagMapper, "TagMapper class should exist");
    }
    
    @Test
    public void testFromDTO() {
        TagDTO tagDTO = new TagDTO("TestTag");
        Tag tag = TagMapper.fromDTO(tagDTO);

        assertEquals(tagDTO.getName(), tag.getName(), "Mapping from DTO to Tag failed");
    }

    @Test
    public void testFromEntity() {
        Tag tag = new Tag();
        tag.setName("TestTag");
        TagDTO tagDTO = TagMapper.fromEntity(tag);

        assertEquals(tag.getName(), tagDTO.getName(), "Mapping from Tag to DTO failed");
    }
}