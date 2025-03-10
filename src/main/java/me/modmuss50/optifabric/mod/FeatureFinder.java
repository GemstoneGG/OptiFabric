package me.modmuss50.optifabric.mod;

import java.util.function.BooleanSupplier;

public abstract class FeatureFinder implements BooleanSupplier {
	private boolean haveLooked, isPresent;

	protected abstract boolean isPresent();

	@Override
	public boolean getAsBoolean() {
		if (!haveLooked) {
			isPresent = isPresent();
			haveLooked = true;
		}

		return isPresent;
	}
}