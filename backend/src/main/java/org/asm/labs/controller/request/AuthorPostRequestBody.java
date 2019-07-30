package org.asm.labs.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name"
})
public class AuthorPostRequestBody {
    
    @JsonPropertyOrder("name")
    private String name;
    
    @JsonPropertyOrder("name")
    public String getName() {
        return name;
    }
    
    @JsonPropertyOrder("name")
    public void setName(String name) {
        this.name = name;
    }
}
