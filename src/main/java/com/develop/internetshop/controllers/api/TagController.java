package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Tag.Tag;
import com.develop.internetshop.entities.Tag.TagRepository;

/**
* TagController
*/
@RestController
@RequestMapping(path = "api/v1/tag")
public class TagController extends BaseApiController<Tag, String> {
    public TagController(TagRepository tagRepository) {
        super(tagRepository);
    }
}
