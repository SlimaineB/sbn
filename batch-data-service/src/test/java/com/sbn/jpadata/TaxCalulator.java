package com.sbn.jpadata;

import java.text.DecimalFormat;
import java.util.function.Function;

import com.sbn.jpadata.BaremePalier.TypeBareme;
import com.sbn.jpadata.Palier.TypePalier;

public class TaxCalulator {

	public static final Double REMUNERATION_NET = 30000d;
	
	public static final Double INFINI = 100000000d;
	
	public static final Double PLAFOND_SECURITE_SOCIALE = 39732d;
	
	public static final Double CSG_NON_DEDUCTIBLE = 0.029d;
	
	public static final Double RESERVE_LEGALE = 0.05d;
	
	public static  Double plafond110Pourcent = 110*PLAFOND_SECURITE_SOCIALE/100;
	
	public static  Double plafond140Pourcent = 140*PLAFOND_SECURITE_SOCIALE/100;

	public static BaremePalier cipavBase = new BaremePalier().withLibelle("CIPAV_BASE").withType(TypeBareme.CUMULE)
			.addPalier(new Palier("TRANCHE 0", 0d, 4569d, 461d, null,null,  TypePalier.FORFAIT))
			.addPalier(new Palier("TRANCHE 1", 0d, 39732d, null, 0.0823,null,  TypePalier.TAUX))
			.addPalier(new Palier("TRANCHE 2", 0d, 198660d, null, 0.0187,null,  TypePalier.TAUX));	

	public static BaremePalier cipavComplementaire = new BaremePalier().withLibelle("CIPAV_COMPLEMENTAIRE").withType(TypeBareme.SIMPLE)
			.addPalier(new Palier("CLASSE A", 0d, 26580d, 1315d, null, null, TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE B", 26581d, 49280d, 2630d, null,null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE C", 49281d, 57850d, 3945d, null,null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE D", 57851d, 66400d, 6575d, null,null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE E", 66401d, 83060d, 9205d, null,null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE F", 83061d, 103180d, 14465d, null,null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE G", 103181d, 123300d, 15780d, null,null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE H", 123000d, 103180d, 17095d, null,null,  TypePalier.VALEUR));

	public static BaremePalier cipavPrevoyance = new BaremePalier().withLibelle("CIPAV_PREVOYANCE").withType(TypeBareme.SIMPLE)
			.addPalier(new Palier("CLASSE A", null, null, 76d, null,null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE B", null, null, 228d, null, null,  TypePalier.VALEUR))
			.addPalier(new Palier("CLASSE C", null, null, 380d, null, null,  TypePalier.VALEUR));
	
	public static Function<Double, Double> funcUrssafAllocationFamilliale = (Double input)-> {return (0.031d / (0.3d * PLAFOND_SECURITE_SOCIALE)) * (input - 1.1d * PLAFOND_SECURITE_SOCIALE); };
	
	public static Function<Double, Double> funcUrssafMaladieMaternite = (Double input)-> {return ((0.065d - 0.015d) / plafond110Pourcent) * input + 0.015d; };
	
	public static BaremePalier urssafAllocationFamiliale = new BaremePalier().withLibelle("URSSAF_ALLOCATION_FAMILIALE").withType(TypeBareme.SIMPLE)
			.addPalier(new Palier("TRANCHE 0", 0d, plafond110Pourcent , null, 0d,null,  TypePalier.TAUX))
			.addPalier(new Palier("TRANCHE 1", plafond110Pourcent, plafond140Pourcent, null, null, funcUrssafAllocationFamilliale, TypePalier.TAUX_DYNAMIQUE))
			.addPalier(new Palier("TRANCHE 2", plafond140Pourcent, INFINI, null, 0.031,null, TypePalier.TAUX));	
	
	public static BaremePalier urssafMaladieMaternite = new BaremePalier().withLibelle("URSSAF_MALADIE_MATERNITE").withType(TypeBareme.SIMPLE)
			.addPalier(new Palier("TRANCHE 1", 0d, plafond110Pourcent, null, null, funcUrssafMaladieMaternite, TypePalier.TAUX_DYNAMIQUE))
			.addPalier(new Palier("TRANCHE 2", plafond110Pourcent, INFINI, null, 0.065,null, TypePalier.TAUX));	
	
	public static BaremePalier urssafCsgCrds = new BaremePalier().withLibelle("URSSAF_CSG_CRDS").withType(TypeBareme.SIMPLE)
			.addPalier(new Palier("TRANCHE 0", 0d, INFINI, null, 0.097,null,  TypePalier.TAUX));
	
	public static BaremePalier urssafFormationProfessionnelle = new BaremePalier().withLibelle("URSSAF_FOMATION_PROFESSIONNELLE").withType(TypeBareme.SIMPLE)
			.addPalier(new Palier("TRANCHE 0", 0d, INFINI, null, 0.0025,null,  TypePalier.TAUX));
	
	public static BaremePalier impotRevenu = new BaremePalier().withLibelle("IMPOT_REVENU").withType(TypeBareme.CUMULE)
			.addPalier(new Palier("TRANCHE 0", 0d, 9807d, null, 0d, null, TypePalier.TAUX))
			.addPalier(new Palier("TRANCHE 1", 9807d, 27086d, null, 0.14d, null, TypePalier.TAUX))
			.addPalier(new Palier("TRANCHE 2", 27086d, 72617d, null, 0.3d, null, TypePalier.TAUX))
			.addPalier(new Palier("TRANCHE 3", 72617d, 153783d, null, 0.41d, null, TypePalier.TAUX))
			.addPalier(new Palier("TRANCHE 4", 153783d, INFINI, null, 0.45d, null, TypePalier.TAUX));

	public static BaremePalier impotSociete = new BaremePalier().withLibelle("IMPOT_SOCIETE").withType(TypeBareme.CUMULE)
			.addPalier(new Palier("TRANCHE 1", 0d, 38120d, null, 0.15, null, TypePalier.TAUX))
			.addPalier(new Palier("TRANCHE 2", 38120d, INFINI, null, 0.28,null, TypePalier.TAUX));	
	
	public static BaremePalier flatTax = new BaremePalier().withLibelle("IMPOT_SOCIETE").withType(TypeBareme.SIMPLE)
			.addPalier(new Palier("TRANCHE 1", 0d, INFINI, null, 0.128d, null, TypePalier.TAUX));

	
	
	public static void main(String[] args) {

		System.out.println(calculChargesSociales(57000d));
		
		Double chiffreAffaire = 0d;
		
		Double netMax = 0d;
		Double remunerationMax = 0d;
		Double salaireMax = 0d;
		Double dividendeDistribueeMax = 0d;
		Double dividendeNetteMax = 0d;
		Double reserveLegaleMax = 0d;
		Double beneficeMax = 0d;
		Double chargesSocialesRemunerationMax = 0d;
		
		
		for(Double remunerationNette = 0d; remunerationNette <= chiffreAffaire; remunerationNette+=1000) {
			
			Double chargesSocialesRemuneration = calculChargesSociales(remunerationNette);
			Double benefice = chiffreAffaire - remunerationNette - chargesSocialesRemuneration; 
			Double dividendesDistribuable = calculDividendeDistribuable(benefice);
			Double reserveLegale =  RESERVE_LEGALE * benefice;
			
			Double dividendeNetteMaxRestante = calculDividendeMaxByBeneficeDistribuable(remunerationNette, chargesSocialesRemuneration, dividendesDistribuable - reserveLegale);
			
			Double salaireNet = remunerationNette - calculImpotRevenu(remunerationNette);
			
			if(remunerationNette + chargesSocialesRemuneration < chiffreAffaire && salaireNet + dividendeNetteMaxRestante > netMax) {
				netMax = salaireNet + dividendeNetteMaxRestante;
				remunerationMax = remunerationNette;
				salaireMax = salaireNet;
				dividendeDistribueeMax = dividendesDistribuable;
				dividendeNetteMax = dividendeNetteMaxRestante;	
				reserveLegaleMax = reserveLegale;
				beneficeMax = benefice;
				chargesSocialesRemunerationMax = chargesSocialesRemuneration;
								
				
			}			
		}
		
		System.out.println("Le net d'impot maximum pour un CA de "+chiffreAffaire+" est de "+netMax+" ("+netMax / chiffreAffaire+"% du CA) obtenu avec : "
				+ "\nRemuneration versée : "+remunerationMax
				+ "\nRemuneration nette : "+salaireMax
				+ "\nCharges sociales  : "+chargesSocialesRemunerationMax
				+ "\nBenefice : "+beneficeMax	
				+ "\nReserve légale : "+reserveLegaleMax	
				+ "\nDividende versée : "+dividendeDistribueeMax
				+ "\nDividende nette : "+dividendeNetteMax);
		
				}

	
	public static Double calculDividendeMaxByBeneficeDistribuable(Double remuneration, Double chargesSocialesRemuneration, Double beneficeDistribuable) {
		
		Double chargesSocialesDividende = 0d;
		Double flatTaxDiv = 0d;
		Double divNette=0d;
		
		for(divNette=0d; divNette + chargesSocialesDividende + flatTaxDiv < beneficeDistribuable; divNette++ ) {
			chargesSocialesDividende = calculChargesSociales(remuneration + divNette) - chargesSocialesRemuneration;
			flatTaxDiv = flatTax.applyBareme(divNette + chargesSocialesDividende);
		}
		
		return divNette;
	}
	

	
	

	public static Double calculDividendeDistribuable(Double benefice) {
		return benefice - impotSociete.applyBareme(benefice) ;
	}
			
	public static Double calculImpotRevenu(Double remunerationNet) {
		return impotRevenu.applyBareme(0.9d * remunerationNet * (1 + CSG_NON_DEDUCTIBLE ));
	}
	
	public static Double calculCoutSocieteRemuneration(Double remunerationNet) {
		return remunerationNet + calculChargesSociales(remunerationNet);
	}	
	

	
	/**
	 * 
	 * @param remunerationNet
	 * @return
	 */
	public static Double calculChargesSociales(Double remunerationNet) {
		
		Double totalChargesSociales = cipavBase.applyBareme(remunerationNet) + 
				cipavComplementaire.applyBareme(remunerationNet) + 
				cipavPrevoyance.applyBaremeManual(remunerationNet, "CLASSE C") +
				urssafAllocationFamiliale.applyBareme(remunerationNet) +
				urssafMaladieMaternite.applyBareme(remunerationNet) + 
				urssafFormationProfessionnelle.applyBareme(PLAFOND_SECURITE_SOCIALE);
		
		Double baseCsgChargesSociales = cipavBase.applyBareme(remunerationNet) + 
				cipavComplementaire.applyBareme(remunerationNet) + 
				cipavPrevoyance.applyBaremeManual(remunerationNet, "CLASSE C") +
				urssafAllocationFamiliale.applyBareme(remunerationNet) +
				urssafMaladieMaternite.applyBareme(remunerationNet) + 
				urssafFormationProfessionnelle.applyBareme(PLAFOND_SECURITE_SOCIALE) +
				urssafCsgCrds.applyBareme(remunerationNet);
		
		System.out.println("totalChargesSociales :" + totalChargesSociales);
		
		System.out.println("baseCsgChargesSociales :" + baseCsgChargesSociales);
		
		Double csgCrds =  0d;
		for(int i=0; i< 100; i++) {
			csgCrds = urssafCsgCrds.applyBareme(remunerationNet + baseCsgChargesSociales + csgCrds);
		}
		
		System.out.println("csgCrds : "+csgCrds);
		totalChargesSociales = totalChargesSociales + csgCrds;
		
		return totalChargesSociales;
		
	}
	
	/**
	 * 
	 * @param chiffreAffaire
	 */
	public static Double getMaxRemuneration(Double chiffreAffaire) {
		
		Double coutRemuneration = 0d;
		Double remunerationNet=0d ;
		
		for(remunerationNet=0d ; coutRemuneration < chiffreAffaire ; remunerationNet++) {
			coutRemuneration = remunerationNet + calculChargesSociales(remunerationNet);
		}
		
	//	System.out.println("La remuneration max pour un CA de "+chiffreAffaire+" est de "+new DecimalFormat("###.##").format(remunerationNet)+" et les charges sociales " + new DecimalFormat("###.##").format(computeTax(remunerationNet)));
	
		return remunerationNet;
	}
	
	
	public static Double getMaxDividende(Double chiffreAffaire) {
		
		Double coutDividende = 0d;
		Double remunerationNet=0d ;
		
		for(remunerationNet=0d ; coutDividende < chiffreAffaire ; remunerationNet++) {
			coutDividende = remunerationNet + calculChargesSociales(remunerationNet);
		}
		
	//	System.out.println("La remuneration max pour un CA de "+chiffreAffaire+" est de "+new DecimalFormat("###.##").format(remunerationNet)+" et les charges sociales " + new DecimalFormat("###.##").format(computeTax(remunerationNet)));
	
		return remunerationNet;
	}
	
	public static void test() {
		
		System.out.println("Cotisation CIPAV BASE : " + cipavBase.applyBareme(REMUNERATION_NET));
		
		System.out.println("Cotisation CIPAV COMPLEMENTAIRE : "  + cipavComplementaire.applyBareme(REMUNERATION_NET));
				
		System.out.println("Cotisation CIPAV PREVOYANCE : "  + cipavPrevoyance.applyBaremeManual(REMUNERATION_NET, "CLASSE C"));
		
		System.out.println("Cotisation URSSAF ALLOCATION FAMILIALE : "  + urssafAllocationFamiliale.applyBareme(REMUNERATION_NET));
		
		System.out.println("Cotisation URSSAF MALADIE MATERNITE : "  + urssafMaladieMaternite.applyBareme(REMUNERATION_NET));
		
		System.out.println("Cotisation URSSAF COTISATION FORMATION PROFESSIONNELLE : "  + urssafFormationProfessionnelle.applyBareme(PLAFOND_SECURITE_SOCIALE));
			
		Double totalChargesSociales = cipavBase.applyBareme(REMUNERATION_NET) + 
				cipavComplementaire.applyBareme(REMUNERATION_NET) + 
				cipavPrevoyance.applyBaremeManual(REMUNERATION_NET, "CLASSE A") +
				urssafAllocationFamiliale.applyBareme(REMUNERATION_NET) +
				urssafMaladieMaternite.applyBareme(REMUNERATION_NET) + 
				urssafFormationProfessionnelle.applyBareme(PLAFOND_SECURITE_SOCIALE);
		
		Double csgCrds =  0d;
		for(int i=0; i< 100; i++) {
			csgCrds = urssafCsgCrds.applyBareme(REMUNERATION_NET + totalChargesSociales + csgCrds);
		}
		
		System.out.println("Cotisation URSSAF CSG-CRDS : "  + csgCrds);
		
		totalChargesSociales+= csgCrds;
		
		System.out.println("Total charges sociales : " + new DecimalFormat("###.##").format(totalChargesSociales) + " représentant "+new DecimalFormat("###.##").format((totalChargesSociales / REMUNERATION_NET)*100)+"% de la rémunération");
	
	}
	
	
}
