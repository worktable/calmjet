package com.worktable.calmjet;

import java.io.PrintStream;

public class Circle implements ObjObject {

	private double _radius;
	private double _z;
	private int _aSteps; // Must be > 2
	private double _aDelta;	
	private Vertex _v[];
	
	public Circle(double z, double radius, int aSteps) {
		_z = z;
		_radius = radius;
		_aSteps = aSteps;
		_aDelta = 2*Math.PI/_aSteps;
		_v = new Vertex[_aSteps];
		for (int aStep = 0; aStep < _aSteps; aStep++) {
			double theta = aStep * _aDelta;
			double x = Math.round(_radius * Math.cos(theta) * 1000000)/1000000.0;
			double y = Math.round(_radius * Math.sin(theta) * 1000000)/1000000.0;
			_v[aStep] = new Vertex(x, y, _z);
		}
	}
	
	public int size() {
		return _aSteps;
	}
	
	public void printFaces(PrintStream ps, Circle that) {
		if (this.size() != that.size() || this.size() <= 2 || that.size() <= 2)
			throw new RuntimeException("Cannot join a " + this.size() + "-circle with a " + that.size() + "-circle.");
		
		for (int i=1; i<_aSteps; i++) {
			(new Surface(this._v[i-1], this._v[i], that._v[i], that._v[i-1])).printObj(ps);
		}
		(new Surface(this._v[_aSteps-1], this._v[0], that._v[0], that._v[_aSteps-1])).printObj(ps);
	}
	
	@Override
	public void printObj(PrintStream ps) throws RuntimeException {
		for (int i=0; i<_aSteps; i++) {
			_v[i].printObj(ps);
		}
	}

}
