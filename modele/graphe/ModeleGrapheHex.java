package modele.graphe;

import modele.point.Point;
import modele.point.PointCouleur;
import modele.segment.Segment;


public class ModeleGrapheHex extends ModeleGraphe{
	private static final long serialVersionUID = 106L;
	
	public ModeleGrapheHex() {
		super("Hex");
		
		PointCouleur red1 = new PointCouleur(100,360);
		PointCouleur blue1 = new PointCouleur(600, 25);
		PointCouleur red2 = new PointCouleur(1100,360);
		PointCouleur blue2 = new PointCouleur(600, 725);
		
		Point[] ligne1 = ligne(100);
		Point[] ligne2 = ligne(175);
		Point[] ligne3 = ligne(250);
		Point[] ligne4 = ligne(325);
		Point[] ligne5 = ligne(400);
		Point[] ligne6 = ligne(475);
		Point[] ligne7 = ligne(550);
		Point[] ligne8 = ligne(625);
				
		addPoint(red1);
		addPoint(red2);
		addPoint(blue1);
		addPoint(blue2);
		
		addPointSpeciaux("depart0", red1);
		addPointSpeciaux("arrive0", red2);
		addPointSpeciaux("depart1", blue1);
		addPointSpeciaux("arrive1", blue2);
		
		for(int i = 0; i< 7; i++) {
			addSegment(new Segment(ligne1[i], ligne1[i+1]));
			addSegment(new Segment(ligne2[i], ligne2[i+1]));
			addSegment(new Segment(ligne3[i], ligne3[i+1]));
			addSegment(new Segment(ligne4[i], ligne4[i+1]));
			addSegment(new Segment(ligne5[i], ligne5[i+1]));
			addSegment(new Segment(ligne6[i], ligne6[i+1]));
			addSegment(new Segment(ligne7[i], ligne7[i+1]));
			addSegment(new Segment(ligne8[i], ligne8[i+1]));
			
			addSegment(new Segment(ligne1[i], ligne2[i+1]));
			addSegment(new Segment(ligne2[i], ligne3[i+1]));
			addSegment(new Segment(ligne3[i], ligne4[i+1]));
			addSegment(new Segment(ligne4[i], ligne5[i+1]));
			addSegment(new Segment(ligne5[i], ligne6[i+1]));
			addSegment(new Segment(ligne6[i], ligne7[i+1]));
			addSegment(new Segment(ligne7[i], ligne8[i+1]));
			
			addSegment(new Segment(ligne1[i+1], ligne2[i]));
			addSegment(new Segment(ligne2[i+1], ligne3[i]));
			addSegment(new Segment(ligne3[i+1], ligne4[i]));
			addSegment(new Segment(ligne4[i+1], ligne5[i]));
			addSegment(new Segment(ligne5[i+1], ligne6[i]));
			addSegment(new Segment(ligne6[i+1], ligne7[i]));
			addSegment(new Segment(ligne7[i+1], ligne8[i]));
		}
		
		for(int i=0;i<8;i++) {
			addSegment(new Segment(ligne1[i], ligne2[i]));
			addSegment(new Segment(ligne2[i], ligne3[i]));
			addSegment(new Segment(ligne3[i], ligne4[i]));
			addSegment(new Segment(ligne4[i], ligne5[i]));
			addSegment(new Segment(ligne5[i], ligne6[i]));
			addSegment(new Segment(ligne6[i], ligne7[i]));
			addSegment(new Segment(ligne7[i], ligne8[i]));
			
			addSegment(new Segment(blue1,ligne1[i]));
			addSegment(new Segment(blue2,ligne8[i]));
			
		}
		
		addSegment(new Segment(red1, ligne1[0]));
		addSegment(new Segment(red1, ligne2[0]));
		addSegment(new Segment(red1, ligne3[0]));
		addSegment(new Segment(red1, ligne4[0]));
		addSegment(new Segment(red1, ligne5[0]));
		addSegment(new Segment(red1, ligne6[0]));
		addSegment(new Segment(red1, ligne7[0]));
		addSegment(new Segment(red1, ligne8[0]));
		
		addSegment(new Segment(red2, ligne1[7]));
		addSegment(new Segment(red2, ligne2[7]));
		addSegment(new Segment(red2, ligne3[7]));
		addSegment(new Segment(red2, ligne4[7]));
		addSegment(new Segment(red2, ligne5[7]));
		addSegment(new Segment(red2, ligne6[7]));
		addSegment(new Segment(red2, ligne7[7]));
		addSegment(new Segment(red2, ligne8[7]));

	}
	
	public Point[] ligne(int l) {
		Point p1 = new PointCouleur(250,l);
		Point p2 = new PointCouleur(350,l);
		Point p3 = new PointCouleur(450,l);
		Point p4 = new PointCouleur(550,l);
		Point p5 = new PointCouleur(650,l);
		Point p6 = new PointCouleur(750,l);
		Point p7 = new PointCouleur(850,l);
		Point p8 = new PointCouleur(950,l);
		addPoint(p1);
		addPoint(p2);
		addPoint(p3);
		addPoint(p4);
		addPoint(p5);
		addPoint(p6);
		addPoint(p7);
		addPoint(p8);
		return new Point[] {p1,p2,p3,p4,p5,p6,p7,p8};
	}
	
}
