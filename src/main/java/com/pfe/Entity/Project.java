package com.pfe.Entity;

public class Project {
	private String id;
	private String name;
    private String description;
	private String user_id;
	private int type;
	private float surface;
    
	public Project(){}
	//constructeur recyclerview project
    public Project(String id,String name, String description) {
		super();
		this.id=id;
		this.name = name;
		this.description = description;
	}
  //constructeur medical cabinet and commercial project
    public Project(String id,String name, String description,int type) {
		super();
		this.id=id;
		this.name = name;
		this.description = description;
		this.type=type;
	}
    //constructeur agriculture project
    public Project(String id,String name, String description,int type,float surface) {
		super();
		this.id=id;
		this.name = name;
		this.description = description;
		this.type=type;
		this.surface=surface;
	}
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	} 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public float getSurface() {
		return surface;
	}
	public void setSurface(float surface) {
		this.surface = surface;
	}
}