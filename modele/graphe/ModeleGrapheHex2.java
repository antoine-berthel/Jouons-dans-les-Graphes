package modele.graphe;

import modele.point.PointCouleur;
import modele.segment.Segment;


public class ModeleGrapheHex2 extends ModeleGraphe{
	private static final long serialVersionUID = 106L;
	
	public ModeleGrapheHex2() {
		super("Petit Hex");
		
		PointCouleur depart0 = new PointCouleur(100,360);
		PointCouleur depart1 = new PointCouleur(600, 25);
		PointCouleur arrive0 = new PointCouleur(1100,360);
		PointCouleur arrive1 = new PointCouleur(600, 725);
		
		PointCouleur ligne1 = new PointCouleur(100,100);


		addPoint(depart0);
		addPoint(arrive0);
		addPoint(depart1);
		addPoint(arrive1);
		addPoint(ligne1);
		
		addPointSpeciaux("depart0", depart0);
		addPointSpeciaux("arrive0", arrive0);
		addPointSpeciaux("depart1", depart1);
		addPointSpeciaux("arrive1", arrive1);
		addSegment(new Segment(depart0, ligne1));
		addSegment(new Segment(arrive0, ligne1));
		addSegment(new Segment(depart1, ligne1));
		addSegment(new Segment(arrive1, ligne1));
	}
}
