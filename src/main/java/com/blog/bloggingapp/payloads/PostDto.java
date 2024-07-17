
package com.blog.bloggingapp.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.bloggingapp.entities.Comment;

public class PostDto 
{
	private Integer postId;
	private String title;
	private String content;
	private String imgName;
	private Date addedDate; 
	private UserDto user;
	private CategoryDto category;
	private Set<CommentDto> comments=new HashSet<>();
	//private String imgName="default.png";
	
	
	


	public String getTitle() {
		return title;
	}



	public PostDto(Integer postId, String title, String content, String imgName, Date addedDate, UserDto user,
			CategoryDto category, Set<CommentDto> comments) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imgName = imgName;
		this.addedDate = addedDate;
		this.user = user;
		this.category = category;
		this.comments = comments;
	}


	public Set<CommentDto> getComments() {
		return comments;
	}


	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImgName() {
		return imgName;
	}


	public void setImgName(String imgName) {
		this.imgName = imgName;
	}


	public Date getAddedDate() {
		return addedDate;
	}


	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}


	public UserDto getUser() {
		return user;
	}


	public void setUser(UserDto user) {
		this.user = user;
	}


	public CategoryDto getCategory() {
		return category;
	}


	public void setCategory(CategoryDto category) {
		this.category = category;
	}


}
