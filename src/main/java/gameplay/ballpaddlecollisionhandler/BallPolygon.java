package gameplay.ballpaddlecollisionhandler;

import gameplay.ball.Ball;

import java.awt.*;

class BallPolygon {

    private static final int N_POINTS = 30;
    private Polygon polygon;

    public BallPolygon(int ballCenterX, int ballCenterY){
        int[] xPoints = new int[N_POINTS];
        int[] yPoints = new int[N_POINTS];

        for(int i = 0; i < N_POINTS; i++){
            xPoints[i] = ballCenterX + (int)(Ball.RADIUS * Math.cos((i * 2 * Math.PI / N_POINTS)));
            yPoints[i] = ballCenterY + (int)(Ball.RADIUS * Math.sin((i * 2 * Math.PI / N_POINTS)));
        }

        polygon = new Polygon(xPoints, yPoints, N_POINTS);

    }

    public boolean intersects(double x, double y, double w, double h){
        return polygon.intersects(x, y, w, h);
    }
}
