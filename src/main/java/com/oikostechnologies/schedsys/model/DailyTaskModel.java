package com.oikostechnologies.schedsys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DailyTaskModel {

	
	private String title;
	private String taskdetail;
	private String until;
	private boolean recurring;
	private String note = "No Notes";
	
	
}
