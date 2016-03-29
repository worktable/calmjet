package com.worktable.calmjet;

public class MidFunction implements UnitRangeFunction {
	private static final double SHARPNESS = 0.5;
	private static final double ADJUSTMENT = Math.sqrt(1.0-SHARPNESS);

	@Override
	public double f(double zRatio) {
		// TODO Auto-generated method stub
		return 1.0-(0.6/SHARPNESS)*(Math.sqrt(1-zRatio*zRatio*SHARPNESS)-ADJUSTMENT);
	}

}
