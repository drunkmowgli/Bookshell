package org.asm.labs.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"commentDescription"
})
public class CommentPostRequestBody {

	@JsonProperty("commentDescription")
	private String commentDescription;

	@JsonProperty("commentDescription")
	public String getCommentDescription() {
		return commentDescription;
	}

	@JsonProperty("commentDescription")
	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}
}
