package com.sbn.jpadata;

import java.util.function.Function;

public class Palier {

	
	private String libelle;
	private Double palierMin;
	private Double palierMax;
	private Double valeurPalier;
	private Double tauxPalier;
	private TypePalier typePalier;
	Function<Double, Double> computeDynamic;
	
	
	public Palier() {
		super();
	}
	
	public Palier(String libelle, Double palierMin, Double palierMax, Double valeurPalier, Double tauxPalier, Function<Double, Double> computeDynamic,
			TypePalier typePalier) {
		super();
		this.libelle = libelle;
		this.palierMin = palierMin;
		this.palierMax = palierMax;
		this.valeurPalier = valeurPalier;
		this.tauxPalier = tauxPalier;
		this.computeDynamic = computeDynamic;
		this.typePalier = typePalier;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Double getPalierMin() {
		return palierMin;
	}
	public void setPalierMin(Double palierMin) {
		this.palierMin = palierMin;
	}
	public Double getPalierMax() {
		return palierMax;
	}
	public void setPalierMax(Double palierMax) {
		this.palierMax = palierMax;
	}
	public Double getValeurPalier() {
		return valeurPalier;
	}
	public void setValeurPalier(Double valeurPalier) {
		this.valeurPalier = valeurPalier;
	}
	public Double getTauxPalier() {
		return tauxPalier;
	}
	public void setTauxPalier(Double tauxPalier) {
		this.tauxPalier = tauxPalier;
	}
	
	public TypePalier getTypePalier() {
		return typePalier;
	}
	public void setTypePalier(TypePalier typePalier) {
		this.typePalier = typePalier;
	}

	public Function<Double, Double> getComputeDynamic() {
		return computeDynamic;
	}

	public void setComputeDynamic(Function<Double, Double> computeDynamic) {
		this.computeDynamic = computeDynamic;
	}

	public enum TypePalier {
		  TAUX,
		  VALEUR,
		  FORFAIT,
		  VALEUR_DYNAMIQUE,
		  TAUX_DYNAMIQUE
		}
	
}
