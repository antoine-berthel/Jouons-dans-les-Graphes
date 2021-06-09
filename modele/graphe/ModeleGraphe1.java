package modele.graphe;

import modele.point.Point;
import modele.point.PointCouleur;
import modele.segment.Segment;

public class ModeleGraphe1 extends ModeleGraphe {
	private static final long serialVersionUID = 101L;

	public ModeleGraphe1() {
		super("1");
		Point p1 = new PointCouleur(200, 100);
		Point p2 = new PointCouleur(300, 200);
		Point p3 = new PointCouleur(200, 200);
		Point p4 = new PointCouleur(400, 200);
		Point p5 = new PointCouleur(500, 100);
		Point p6 = new PointCouleur(500, 200);
		Point p7 = new PointCouleur(200, 300);
		Point p8 = new PointCouleur(500, 300);
		Point p9 = new PointCouleur(600, 200);
		Point p10 = new PointCouleur(100, 200);
		addPoint(p1);
		addPoint(p2);
		addPoint(p3);
		addPoint(p4);
		addPoint(p5);
		addPoint(p6);
		addPoint(p7);
		addPoint(p8);
		addPoint(p9);
		addPoint(p10);
		addSegment(new Segment(p1, p2));
		addSegment(new Segment(p2, p3));
		addSegment(new Segment(p1, p3));
		addSegment(new Segment(p4, p2));
		addSegment(new Segment(p5, p6));
		addSegment(new Segment(p4, p5));
		addSegment(new Segment(p4, p6));
		addSegment(new Segment(p3, p7));
		addSegment(new Segment(p7, p2));
		addSegment(new Segment(p8, p6));
		addSegment(new Segment(p8, p4));
		addSegment(new Segment(p3, p10));
		addSegment(new Segment(p9, p6));
		addPointSpeciaux("depart0", p9);
		addPointSpeciaux("depart1", p10);
	}
}