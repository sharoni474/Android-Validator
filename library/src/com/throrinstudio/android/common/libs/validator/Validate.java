package com.throrinstudio.android.common.libs.validator;

import java.util.ArrayList;
import java.util.Iterator;

import android.widget.TextView;

public class Validate extends BaseValidate {

	/**
     * Validator chain
     */
    protected ArrayList<BaseValidator> _validators = new ArrayList<BaseValidator>();
    
    /**
     * Validation failure messages
     */
    protected String _message = new String();
    
    /**
     * 
     */
    protected TextView _source;
    
    
    public Validate(TextView source){
    	this._source = source;
    }

    /**
     * Adds a validator to the end of the chain
     *
     * @param validator
     */
    public void addValidator(BaseValidator validator)
    {
    	this._validators.add(validator);
    	return;
    }
    
    public boolean isValid(String value){
    	boolean result = true;
    	this._message = new String();
    	
    	Iterator<BaseValidator> it = this._validators.iterator();
    	while(it.hasNext()){
    		BaseValidator validator = it.next();
            try{
                if(!validator.isValid(value)){
                    this._message = validator.getMessage();
                    result = false;
                    break;
                }
            }catch(ValidatorException e){
                System.err.println(e.getMessage());
                System.err.println(e.getStackTrace());
                this._message = e.getMessage();
                result = false;
                break;
            }
    	}
    	
    	return result;
    }
    
    public String getMessages(){
    	return this._message;
    }
    
    public TextView getSource(){
    	return this._source;
    }
    
}
