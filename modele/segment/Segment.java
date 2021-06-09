package modele.segment;

import java.awt.geom.Line2D;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import modele.point.Point;

public class Segment implements Serializable {
	private static final long serialVersionUID = 20L;

	private Point point1;
	private Point point2;
	private boolean oriente;

	public Segment(Point point1, Point point2, boolean oriente) {
		this.point1 = point1;
		this.point2 = point2;
		this.oriente = oriente;
	}

	public Segment(Point point1, Point point2) {
		this(point1, point2, false);
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public boolean isOriente() {
		return oriente;
	}

	public void setOriente(boolean oriente) {
		this.oriente = oriente;
	}

	public Point getVoisin(Point p) {
		if (point1 == p) {
			return point2;
		} else if (point2 == p) {
			return point1;
		}
		return null;
	}
	
	public boolean sommetCommun(Segment segment) {
		return point1.equals(segment.point1) || point1.equals(segment.point2) || point2.equals(segment.point1) || point2.equals(segment.point2);
	}
	
	public Line toLine() {
		Line l = new Line(point1.getX(), point1.getY(), point2.getX(), point2.getY());
		l.setStroke(Color.BLACK);
		l.setStrokeWidth(3);
		l.setVisible(true);
		return l;
	}
	
	public Line2D toLine2D() {
		return new Line2D.Double(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Segment) {
			Segment segment = (Segment) obj;
			return point1.equals(segment.point1) && point2.equals(segment.point2)
					|| point1.equals(segment.point2) && point2.equals(segment.point1);
		}
		return false;
	}
}
