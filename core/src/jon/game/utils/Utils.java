package jon.game.utils;

public class Utils {
	
	public static boolean within(float original, float val, float lowerBound, float upperBound) {
		return (original - lowerBound) <= val && val <= (original + upperBound);
	}
	
    public static float bakhshaliRoot(float x) {
    	//Bakhshali
        int nearest_square = 0;
        int root_of_nearest_square = 0; 
        for(int i = (int)(x); i > 0; i--) {
            for(int j = 1; j < i; j++) {
                if (j*j == i) {
                    nearest_square = i;
                    root_of_nearest_square = j;
                    break;
                }
            }
            if (nearest_square > 0) break;
        }
        float xn = (x-nearest_square)/(2*root_of_nearest_square); 
        float n = root_of_nearest_square+xn; 
        return n-((xn*xn)/(2*n));
    }

}
