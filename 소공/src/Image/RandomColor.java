package Image;

import java.awt.Color;

public class RandomColor {
	
	public Color randomColor(){
		int r=(int)(Math.random()*256);
		int g=(int)(Math.random()*256);
		int b=(int)(Math.random()*256);
		
		Color color=new Color(r,g,b);
		
		return color;
	}

}
