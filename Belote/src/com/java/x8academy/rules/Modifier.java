package com.java.x8academy.rules;

public enum Modifier {
	Normal(1), Contra(2), ReContra(4);

	public int pointsToIncrease;

	Modifier(int pointsToIncrease) {
		this.pointsToIncrease = pointsToIncrease;
	}
}
