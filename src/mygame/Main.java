package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    // nodes
    Node towerNode = new Node("towerNode");
    Node playerNode = new Node("playerNode");
    Node creepNode = new Node("creepNode");
    
    public static void main(String[] args) {
        Main app = new Main();
        
        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Tower Defense Demo");
        
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        flyCam.setMoveSpeed(6);
        
        // floor
        Box floor = new Box(32f, 0, 32f);
        Geometry geom = new Geometry("Box", floor);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Orange);
        geom.setMaterial(mat);

               
        // place these dame things
        placeBase(new Vector3f(0, 0, 0));
        placeTower(new Vector3f(4, 0, 4));
        placeTower(new Vector3f(-4, 0, 4));
        
        placeCreep(new Vector3f(3,0,6));
        placeCreep(new Vector3f(-3,0,6));
        placeCreep(new Vector3f(3,0,7));
        placeCreep(new Vector3f(-3,0,7));
        placeCreep(new Vector3f(3,0,8));
        placeCreep(new Vector3f(-3,0,8));
        placeCreep(new Vector3f(3,0,9));
        placeCreep(new Vector3f(-3,0,9));
        
        
        rootNode.attachChild(geom);
        rootNode.attachChild(towerNode);
        rootNode.attachChild(playerNode);
        rootNode.attachChild(creepNode);
    }

    private void placeBase (Vector3f v){
        Box base = new Box(1f, 0.5f, 1f);
        Geometry baseGeom = new Geometry("Box", base);
        Material baseMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        baseMat.setColor("Color", ColorRGBA.Yellow);
        baseGeom.setMaterial(baseMat);
        baseGeom.setLocalTranslation(v.x,v.y+base.yExtent,v.z);
        playerNode.attachChild(baseGeom);
    }
    
    private void placeTower(Vector3f v){
        Box tower = new Box(0.5f, 3f, 0.5f);
        Geometry towerGeom = new Geometry("Box", tower);
        Material towerMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        towerMat.setColor("Color", ColorRGBA.Green);
        towerGeom.setMaterial(towerMat);
        towerGeom.setLocalTranslation(v.x,v.y+tower.yExtent,v.z);
        towerNode.attachChild(towerGeom);
    }
    
    private void placeCreep(Vector3f v){
        Box creep = new Box(0.2f, 0.2f, 0.2f);
        Geometry creepGeom = new Geometry("Box", creep);
        Material creepMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        creepMat.setColor("Color", ColorRGBA.Black);
        creepGeom.setMaterial(creepMat);
        creepGeom.setLocalTranslation(v.x,v.y+creep.yExtent,v.z);
        creepNode.attachChild(creepGeom);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
