package com.vemdaroca.vemdarocaapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandReturnDTO {

	private int exitVal = -1;
	private String logError = "";
}
