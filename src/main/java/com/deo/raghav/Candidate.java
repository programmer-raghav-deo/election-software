/**
 * 
 */
package com.deo.raghav;

/**
 * 
 */
public class Candidate {
	private String image_path;
	private String name;
	
	public Candidate(String image_path, String name) {
		this.image_path = image_path;
		this.name = name;
	}
	
	public void set_image_path(String image_path) {
		this.image_path = image_path;
	}
	public String get_image_path() {
		return image_path;
	}
	public void set_name(String name) {
		this.name = name;
	}
	public String get_name() {
		return name;
	}
}
