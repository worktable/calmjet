package com.worktable.calmjet;

import java.io.PrintStream;

public class Cylinder implements ObjObject {
	private static final double MIN_RADIUS = 1.25;

	Circle _circles[];
	int _zSteps;
	double _zMax, _zDelta;

	public Cylinder(UnitRangeFunction f, double zMax, int zSteps, double rMax, int aSteps) {
		_zMax = zMax;
		_zSteps = zSteps;
		_zDelta = _zMax/_zSteps;
		_circles = new Circle[2*_zSteps+1];
		for (int zStep = -_zSteps; zStep <= _zSteps; zStep++) {
			double zRatio = (double)zStep/(double)_zSteps;
			if (zRatio < 0) zRatio = -zRatio;
			if (zRatio > 1) zRatio = 1.0;
			double radius = f.f(zRatio)*rMax;
			_circles[zStep+_zSteps] = new Circle(zStep*_zDelta, (radius < MIN_RADIUS ? MIN_RADIUS : radius), aSteps);
		}
	}
	
	@Override
	public void printObj(PrintStream ps) throws RuntimeException {
		for (int i=-_zSteps; i<=_zSteps; i++) {
			Circle curr = _circles[i+_zSteps];
			curr.printObj(ps);
			if (i>-_zSteps) {
				Circle prev = _circles[i-1+_zSteps];
				prev.printFaces(ps, curr);
			}
		}
	}
	
}
