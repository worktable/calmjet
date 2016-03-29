package com.worktable.calmjet;

import java.io.PrintStream;

public class CalmJet implements ObjObject {
	private static final double Z_MAX = 50.0;
	private static final double Z_SPACER = 3.0;
	private static final int Z_STEPS = 100;
	private static final double R_MAX = 20.0;
	private static final int A_STEPS = 90; // Must be > 2
	private static final int ROTATIONS = 5;
	private static final String CS_TYPE = "#Use Cardinal for curved surfaces\ncstype cardinal\nstech cparma 1.0 1.0\ndeg 3 3";
	
	private Cylinder _out;
	private Cylinder _mid;
	private Cylinder _cor;
	private Helix _blade;

	public static void main(String[] args) {
		CalmJet _calmJet = new CalmJet();
		_calmJet.printObj(System.out);
	}
	
	public CalmJet() {
		_out = new Cylinder(new OutFunction(), Z_MAX, Z_STEPS, R_MAX, A_STEPS);
		_mid = new Cylinder(new MidFunction(), Z_MAX, Z_STEPS, R_MAX, A_STEPS);
		_cor = new Cylinder(new CorFunction(), Z_MAX, Z_STEPS, R_MAX, A_STEPS);
		_blade = new Helix(Z_MAX-Z_SPACER, R_MAX, A_STEPS, ROTATIONS, new MidFunction(), new CorFunction());
	}
	

	@Override
	public void printObj(PrintStream ps) throws RuntimeException {
		ps.println(CS_TYPE);
		_out.printObj(ps);
		_mid.printObj(ps);
		_cor.printObj(ps);
		_blade.printObj(ps);
	}

}
