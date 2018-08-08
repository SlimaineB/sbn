package com.sbn.jpadata;

import java.util.ArrayList;
import java.util.List;

import com.sbn.jpadata.Palier.TypePalier;

public class BaremePalier {

	private String libelleBareme;
	private TypeBareme typeBareme;

	public TypeBareme getTypeBareme() {
		return typeBareme;
	}

	public void setTypeBareme(TypeBareme typeBareme) {
		this.typeBareme = typeBareme;
	}

	private List<Palier> paliers = new ArrayList<Palier>();

	public enum TypeBareme {
		SIMPLE, CUMULE, MANUAL;
	}

	public BaremePalier() {
		super();
	}

	public BaremePalier(String libelleBareme, List<Palier> paliers) {
		super();
		this.libelleBareme = libelleBareme;
		this.paliers = paliers;
	}

	public String getLibelleBareme() {
		return libelleBareme;
	}

	public void setLibelleBareme(String libelleBareme) {
		this.libelleBareme = libelleBareme;
	}

	public List<Palier> getPaliers() {
		return paliers;
	}

	public void setPaliers(List<Palier> paliers) {
		this.paliers = paliers;
	}

	public BaremePalier withLibelle(String libelleBareme) {
		this.libelleBareme = libelleBareme;
		return this;
	}

	public BaremePalier withType(TypeBareme typeBareme) {
		this.typeBareme = typeBareme;
		return this;
	}

	public BaremePalier addPalier(Palier palier) {
		this.paliers.add(palier);
		return this;
	}

	public Double applyBareme(Double base) {

		Double result = 0d;

		for (Palier aPalier : this.paliers) {
			if (TypeBareme.CUMULE.equals(typeBareme)) {
				// Bareme CUMULE
				if (base >= aPalier.getPalierMin()) {
					if (TypePalier.TAUX.equals(aPalier.getTypePalier())) {
						if (base > aPalier.getPalierMax()) {
							result += (aPalier.getPalierMax() - aPalier.getPalierMin()) * aPalier.getTauxPalier();
						} else {
							result += (base - aPalier.getPalierMin()) * aPalier.getTauxPalier();
						}

					} else if (TypePalier.VALEUR.equals(aPalier.getTypePalier())) {
						result += aPalier.getValeurPalier();

					} else if (TypePalier.TAUX_DYNAMIQUE.equals(aPalier.getTypePalier())) {
						//System.out.println("Dynamic Rate is  : " + aPalier.getComputeDynamic().apply(base));
						result += base * aPalier.getComputeDynamic().apply(base);

					} else if (TypePalier.VALEUR_DYNAMIQUE.equals(aPalier.getTypePalier())) {
						//System.out.println("Dynamic Value is  : " + aPalier.getComputeDynamic().apply(base));
						result += aPalier.getComputeDynamic().apply(base);

					} else if (TypePalier.FORFAIT.equals(aPalier.getTypePalier())) {
						if (base >= aPalier.getPalierMin() && base <= aPalier.getPalierMax()) {
							result += aPalier.getValeurPalier();
						}
					}
				}
			} else if (TypeBareme.SIMPLE.equals(typeBareme)) {
				// Bareme SIMPLE
				if (base >= aPalier.getPalierMin() && base <= aPalier.getPalierMax()) {
					if (TypePalier.TAUX.equals(aPalier.getTypePalier())) {
						result += base * aPalier.getTauxPalier();
					} else if (TypePalier.VALEUR.equals(aPalier.getTypePalier())) {
						result += aPalier.getValeurPalier();
					} else if (TypePalier.TAUX_DYNAMIQUE.equals(aPalier.getTypePalier())) {
						//System.out.println("Dynamic Rate is  : " + aPalier.getComputeDynamic().apply(base));
						result += base * aPalier.getComputeDynamic().apply(base);
					} else if (TypePalier.VALEUR_DYNAMIQUE.equals(aPalier.getTypePalier())) {
						//System.out.println("Dynamic Value is  : " + aPalier.getComputeDynamic().apply(base));
						result += aPalier.getComputeDynamic().apply(base);
					}
				}

			}
		}

		return result;

	}

	public Double applyBaremeManual(Double base, String palierName) {

		Double result = 0d;

		for (Palier aPalier : this.paliers) {
			if (palierName.equals(aPalier.getLibelle())) {
				if (TypePalier.TAUX.equals(aPalier.getTypePalier())) {
					result += base * aPalier.getTauxPalier();
				} else if (TypePalier.VALEUR.equals(aPalier.getTypePalier())) {
					result += aPalier.getValeurPalier();
				}
			}

		}

		return result;
	}

}
