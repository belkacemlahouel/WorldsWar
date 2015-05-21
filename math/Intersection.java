package math;

public class Intersection {
	
	public final float time;
	public final MyPoint2D position;
	
	public Intersection(float time, int x, int y) {
		this.time = time;
		position = new MyPoint2D(x, y);
	}
	
	public String toString() {
		return "(in " + time + "s, in" + position.toString() + ")";
	}
}

//	public static Intersection intersection(MyPoint2D pos1, MyVector2D vel1, MyPoint2D pos2, MyVector2D vel2) {
// 		if (pos2.equals(pos1))
// 		if (pos2.equals(pos1))
//-			return new Intersection(0, pos1.getX(), pos1.getY());
//+			return new Intersection(0f, pos1.getX(), pos1.getY());
// 		else {
// 		else {
// 			if (vel1.equals(vel2))
// 			if (vel1.equals(vel2))
// 				return null;
// 				return null;
// 			else {
// 			else {
// 				final float TX, TY, DX, DY;
// 				final float TX, TY, DX, DY;
//-				final int X, Y;
//+				final float X, Y;
// 				
// 				
//-				X = (int) (pos2.getX() - pos1.getX());
//+				X = (pos2.getX() - pos1.getX());
//-				Y = (int) (pos2.getY() - pos1.getY());
//+				Y = (pos2.getY() - pos1.getY());
// 				DX = vel1.getDx() - vel2.getDx();
// 				DX = vel1.getDx() - vel2.getDx();
// 				DY = vel1.getDy() - vel2.getDy();
// 				DY = vel1.getDy() - vel2.getDy();
// 				
// 				
// 				TX = X/DX;
// 				TX = X/DX;
// 				TY = Y/DY;
// 				TY = Y/DY;
// 				
// 				
//-				System.err.println("TX == TY ? " + (TX == TY));
//+				Intersection I1 = new Intersection(TX, (int) (TX*vel1.getDx()+pos1.getX()), (int) (TX*vel1.getDy()+pos1.getY()));
//-				// We just need to check if we have the same result whatever what coordinate we use for this.
// 				
// 				
//-				return new Intersection(TX, X, Y);
//+				if (TX != TY) return null;
//+				
//+				return I1;
// 			}
// 			}
// 		}
// 		}
// 	}
// 	}
