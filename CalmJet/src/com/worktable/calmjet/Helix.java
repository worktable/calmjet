package com.worktable.calmjet;

import java.io.PrintStream;

public class Helix implements ObjObject {

	private int _aSteps;
	double _zMax, _aDelta;
	double _rMax;
	double _pitch;
	UnitRangeFunction _midF, _corF;

	public Helix(double zMax, double rMax, int aSteps, int rotations, UnitRangeFunction midF, UnitRangeFunction corF) {
		_zMax = zMax;
		_rMax = rMax;
		_aSteps = aSteps;
		_aDelta = 2*Math.PI/_aSteps;
		_pitch = aSteps * rotations;
		_midF = midF;
		_corF = corF;
	}
	
	@Override
	public void printObj(PrintStream ps) throws RuntimeException {
		
		double z = -_zMax;
		Vertex vcPrev = new Vertex(  0.0, 0.0, z); vcPrev.printObj(ps);
		Vertex voPrev = new Vertex(_rMax, 0.0, z); voPrev.printObj(ps);
		for (int aStep = 1; z < _zMax; aStep++) {
			double theta = aStep * _aDelta;
			double zRatio = (z < 0) ? -z/_zMax : z/_zMax;
			double fMid = _midF.f(zRatio);
			double fCor = _corF.f(zRatio);
			double zDelta =  (_zMax/_pitch) / (fMid*fMid - fCor*fCor);
			double x = Math.round(_rMax * Math.cos(theta) * 1000000)/1000000.0;
			double y = Math.round(_rMax * Math.sin(theta) * 1000000)/1000000.0;
			z += zDelta;
			Vertex vcCurr = new Vertex(0.0,0.0,z); vcCurr.printObj(ps);
			Vertex voCurr = new Vertex(  x,  y,z); voCurr.printObj(ps);
			(new Surface(vcCurr,vcPrev, voPrev, voCurr)).printObj(ps);
			vcPrev = vcCurr;
			voPrev = voCurr;
		}
		
	}

}
