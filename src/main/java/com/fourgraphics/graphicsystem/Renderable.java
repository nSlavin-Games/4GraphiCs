import com.fourgraphics.gameobjects;
import processing.core.PApplet;

/**
 * 
 */

/**
 * @author axel9
 *
 */
public abstract class Renderable {
	public GameObject gameObject;      
	public Transform transform; 
	protected PApplet sketch;
	
	abstract void Render();



}
