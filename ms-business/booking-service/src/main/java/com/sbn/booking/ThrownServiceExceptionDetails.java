package com.sbn.booking;

import java.lang.reflect.Constructor;

public class ThrownServiceExceptionDetails
{
    private Class<? extends ServiceException> clazz;
    private Constructor<? extends ServiceException> emptyConstructor;
    private Constructor<? extends ServiceException> messageConstructor;
    //getters and setters omitted
	public Class<? extends ServiceException> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends ServiceException> clazz) {
		this.clazz = clazz;
	}
	public Constructor<? extends ServiceException> getEmptyConstructor() {
		return emptyConstructor;
	}
	public void setEmptyConstructor(Constructor<? extends ServiceException> emptyConstructor) {
		this.emptyConstructor = emptyConstructor;
	}
	public Constructor<? extends ServiceException> getMessageConstructor() {
		return messageConstructor;
	}
	public void setMessageConstructor(Constructor<? extends ServiceException> messageConstructor) {
		this.messageConstructor = messageConstructor;
	}
    
    
}