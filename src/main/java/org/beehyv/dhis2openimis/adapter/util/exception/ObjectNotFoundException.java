package org.beehyv.dhis2openimis.adapter.util.exception;

public class ObjectNotFoundException extends Exception{
	
	public ObjectNotFoundException() {
		super();
	}
	
	public ObjectNotFoundException(String message){
		super(message);
	}
}
