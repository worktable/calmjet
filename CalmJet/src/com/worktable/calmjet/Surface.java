package com.worktable.calmjet;

import java.io.PrintStream;

public class Surface implements ObjObject {
	// private static final String BEGIN_FACE = "surf -1.0 2.5 -2.0 2.0";
	// private static final String END_FACE = "\nparm u -1.00 -1.00 -1.00 2.50 2.50 2.50\nparm v -2.00 -2.00 -2.00 -2.00 -2.00 -2.00\ntrim 0.0 2.0 1\nend";
	private static final String BEGIN_FACE = "f";
	private static final String END_FACE = "";
	private Vertex _v[];
		
	public Surface(Vertex p, Vertex q, Vertex r) {
		_v = new Vertex[3];
		_v[0] = p;
		_v[1] = q;
		_v[2] = r;
	}

	public Surface(Vertex p, Vertex q, Vertex r, Vertex s) {
		_v = new Vertex[4];
		_v[0] = p;
		_v[1] = q;
		_v[2] = r;
		_v[3] = s;
	}
	
	public Surface(Vertex p, Vertex q, Vertex r, Vertex s, Vertex t) {
		_v = new Vertex[5];
		_v[0] = p;
		_v[1] = q;
		_v[2] = r;
		_v[3] = s;
		_v[4] = t;
	}
	
	@Override
	public void printObj(PrintStream ps) throws RuntimeException {
		ps.print(BEGIN_FACE);
		for (int i=0; i<_v.length; i++) {
			ps.print(' ');	ps.print(_v[i].getId());
		}
		ps.println(END_FACE);
	}

}
