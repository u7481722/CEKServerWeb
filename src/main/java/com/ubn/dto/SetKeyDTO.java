package com.ubn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetKeyDTO {

	private String m3u8Name;
	private String key;

	@JsonProperty("M3U8Name")
	public String getM3u8Name() {
		return this.m3u8Name;
	}

	public void setM3u8Name(String m3u8Name) {
		this.m3u8Name = m3u8Name;
	}

	@JsonProperty("Key")
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String toString() {
		return "SetKeyDTO [m3u8Name=" + this.m3u8Name + ", key=" + this.key + "]";
	}

}
