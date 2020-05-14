package com.movie.catalogue.Exception;

public enum ExceptionMessage {
	EMPTY("Movie Not Found with id: "), 
	DUPLICATE("err"), 
	DATABASE("Try after some time"), 
	NOTEMPTY("* feild cannot be empty "), 
	Movie_Director_Not_Found("Movie Not Found of director name: "), 
	Movie_Name_Not_Found("Movie Not Found with name: "),
	Movie_withyear_Not_Found("Movie Not Found with year: "),
	NO_RECORD("No record found by id: ");
	private final String message;
	ExceptionMessage(String message)
	{
		this.message = message;
	}
	public String getMessage()
	{
		return this.message;
	}

}
