package com.cmc.eval.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String convertir(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaComoCadena = sdf.format(date);
		return fechaComoCadena;
	}
	
	public static Date convertirDate(String dato) throws ParseException {
		SimpleDateFormat SDFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaConvertida = new Date();
		fechaConvertida = SDFormat.parse(dato);
		return fechaConvertida;
	}
	
	
	public static String convertirDate2(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String fechaComoCadena = sdf.format(date);
		return fechaComoCadena;
	}
	
	

}
