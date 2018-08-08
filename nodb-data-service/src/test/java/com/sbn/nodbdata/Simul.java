package com.sbn.nodbdata;

import java.math.BigDecimal;

public class Simul {

	public static void main(String[] args) {

		
		BigDecimal remun = new BigDecimal(2000);
		BigDecimal arej = new BigDecimal(72);
		BigDecimal nbJourMois = new BigDecimal(30);
		BigDecimal tx1 = new BigDecimal(0.7);
		
		BigDecimal total = remun.add((arej.multiply(nbJourMois).subtract(remun.multiply(tx1)))).multiply(BigDecimal.valueOf(1-0.11)).add(remun.multiply(BigDecimal.valueOf(1.1)));

		
		System.out.println(total);
	}

}
