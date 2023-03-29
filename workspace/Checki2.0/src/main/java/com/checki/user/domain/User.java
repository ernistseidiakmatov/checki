package com.checki.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class User {
	
	private long idx;
	
	private String user_type;
	
	private String user_id;
	
	private String user_passwd;
	
	private String user_name;
	
	private String user_tel;
	
	private String user_mail;
	
	private long dept_idx;
	
	private String start_hour;
	
	private String start_min;
	
	private String end_hour;
	
	private String end_min;
	
	private String user_auth;
	
	private String del_yn;
	
	private String create_by;
	
	private String create_dt;
	
	private String update_by;
	
	private String update_dt;
	

}
