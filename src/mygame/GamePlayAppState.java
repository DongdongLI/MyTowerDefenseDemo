/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import mygame.control.CreepControl;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;
import mygame.control.BeamControl;
import mygame.control.TowerControl;

/**
 *
 * @author dli
 */
public class GamePlayAppState extends AbstractAppState{

    private AssetManager assetManager;
    private Camera cam;
    private SimpleApplication app;
    private Node rootNode;
    
    private Integer level;
    private Integer score;
    private Integer health;
    private Integer budget;
    private Boolean lastGameWon;
    
    private static Integer towerIndex = 0;
    private static Integer creepIndex = 0;

    public Camera getCam() {
        return cam;
    }

    public Node getRootNode() {
        return rootNode;
    }
    
    public Node getCreepNode() {
        return creepNode;
    }
    
    public AssetManager getAssetManager() {
        return assetManager;
    }
    // nodes
    Node towerNode = new Node("towerNode");
    Node playerNode = new Node("playerNode");
    Node creepNode = new Node("creepNode");
    Node beamNode = new Node("beam");
    Geometry floorGeom;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Boolean getLastGameWon() {
        return lastGameWon;
    }

    public void setLastGameWon(Boolean lastGameWon) {
        this.lastGameWon = lastGameWon;
    }
    
    
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.cam = this.app.getCamera();
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.budget = 20;
        
        // hide the stats
//        setDisplayFps(false);
//        setDisplayStatView(false);
        
        // floor
        Box floor = new Box(32f, 0, 32f);
        floorGeom = new Geometry("Box", floor);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Orange);
        floorGeom.setMaterial(mat);

               
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
        
        
        rootNode.attachChild(floorGeom);
        rootNode.attachChild(towerNode);
        rootNode.attachChild(playerNode);
        rootNode.attachChild(creepNode);
        rootNode.attachChild(beamNode);
    }

    @Override
    public void cleanup() {
        super.cleanup(); //To change body of generated methods, choose Tools | Templates.
        rootNode.detachChild(floorGeom);
        rootNode.detachChild(towerNode);
        rootNode.detachChild(playerNode);
        rootNode.detachChild(creepNode);
    }
    
    private void placeTower(Vector3f v){
        Box tower = new Box(0.5f, 3f, 0.5f);
        Geometry towerGeom = new Geometry("Box", tower);
        Material towerMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        towerMat.setColor("Color", ColorRGBA.Green);
        towerGeom.setMaterial(towerMat);
        towerGeom.setLocalTranslation(v.x,v.y+tower.yExtent,v.z);
        towerNode.attachChild(towerGeom);
        towerGeom.setUserData("index", towerIndex++);
        towerGeom.setUserData("chargesNum", 0);
        towerGeom.setUserData("height", 10);
        towerGeom.addControl(new TowerControl(this));
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
    
    public void placeBeam (Geometry tower, Geometry creep) {
        Vector3f towerLocation = tower.getLocalTranslation();
        
        Line beam = new Line(new Vector3f(towerLocation.x, towerLocation.y+3f, towerLocation.z), creep.getLocalTranslation());
        Geometry beamGeometry = new Geometry("beam", beam);
        
        Material blue = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        blue.setColor("Color", ColorRGBA.Blue);
        beamGeometry.setMaterial(blue);
        beamNode.attachChild(beamGeometry);
        beamGeometry.addControl(new BeamControl(tower, creep, this));
    }
    
    private void placeCreep(Vector3f v){
        Box creep = new Box(0.2f, 0.2f, 0.2f);
        Geometry creepGeom = new Geometry("Box", creep);
        Material creepMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        creepMat.setColor("Color", ColorRGBA.Black);
        creepGeom.setMaterial(creepMat);
        creepGeom.setLocalTranslation(v.x,v.y+creep.yExtent,v.z);
        creepNode.attachChild(creepGeom);
        creepGeom.setUserData("index", creepIndex++);
        creepGeom.setUserData("health", 99);
        creepGeom.addControl(new CreepControl(this));
    }
}
