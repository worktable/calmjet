package com.worktable.calmjet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

public class Vertex implements ObjObject {
	
	private static final String OBJ_FOR_VERTEX = "v ";

	private final static ArrayList<Vertex> VERTICES = new ArrayList<Vertex>();
	private static int PRINTED_HWM = 0;
	private int _id;
	private double _x;
	private double _y;	
	private double _z;
	
	public Vertex(double x, double y, double z) {
		_x = x;
		_y = y;
		_z = z;
		VERTICES.add(this);
		_id = VERTICES.size();
	}
	
	public static Collection<Vertex> getCollection() {
		return (Collection<Vertex>)VERTICES;
	}
	

	public int getId() {
		return _id;
	}

	@Override
	public void printObj(PrintStream ps) throws RuntimeException {
		if (++PRINTED_HWM != _id) {
			throw new RuntimeException("Attempt to print vertex out-of-sequence") ;
		}
		ps.print(OBJ_FOR_VERTEX);
		ps.print(' ');	ps.print(_x);
		ps.print(' ');	ps.print(_y);
		ps.print(' ');	ps.print(_z);
		ps.println();
	}

	public Vertex centerPoint(Vertex that) {
		// TODO Auto-generated method stub
		return new Vertex((this._x+that._x)/2, (this._y+that._y)/2, (this._z+that._z)/2);
	}
}
