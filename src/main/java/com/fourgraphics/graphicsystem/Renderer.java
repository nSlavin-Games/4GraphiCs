import processing.core.*;

/**
 * 
 */

/**
 * @author axel9
 *
 */
public class Renderer extends Renderable {
	private int color;
	private PImage texture;
	private DrawType renderType;
	

	public Renderer(int color,PImage texture,DrawType renderType, PApplet sketch) {
		this.color=color;
		this.texture=texture;
		this.renderType=renderType;
		this.sketch=sketch;
	}

	@Override
	void Render() {
		float x=transform.GetPosition().GetX();
		float y=transform.GetPosition().GetY();
		float w=transform.GetScale().GetX();
		float h=transform.GetScale().GetY();
		switch(renderType) {
		case TEXTURED:
			sketch.imageMode(sketch.CENTER);
			sketch.image(texture, x, y, w, h );
			sketch.imageMode(sketch.CORNER);
			break;
		case RECT:
			sketch.rectMode(sketch.CENTER);
			sketch.fill(color);
			sketch.rect(x,y,w,h);
			sketch.rectMode(sketch.CORNER);
			break;
		case CIRCLE:
			sketch.fill(color);
			sketch.circle(x, y, w);
			
			break;

		}

	}
}