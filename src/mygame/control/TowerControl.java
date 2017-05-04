/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.control;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;
import mygame.Charges;
import mygame.GamePlayAppState;

/**
 *
 * @author dli
 */
public class TowerControl extends AbstractControl{

    GamePlayAppState state;
    
    ArrayList<CreepControl> reachable;
    
    ArrayList<CreepControl> allCreepList;
    
    Integer attachRange;
    
    Charges charges;
    
    public TowerControl(GamePlayAppState state) {
        this.state = state;
        reachable = new ArrayList<>();
        allCreepList = new ArrayList<>();
        attachRange = 5;
        charges = new Charges(10, 1000000);
    }
    
    public Integer getTowerIndex(){
        return spatial.getUserData("index");
    }
    
    public Integer getTowerHeight(){
        return spatial.getUserData("height");
    }
    
    public Integer getTowerChargesNum() {
        return spatial.getUserData("chargesNum");
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    
    }

    @Override
    protected void controlUpdate(float tpf) {
        
        // clean up the reachable list and get ready to refresh
        reachable.clear();
        
        List<Spatial> creeps = state.getCreepNode().getChildren();
        CreepControl creepControl;
        for(Spatial creep: creeps){
            creepControl = creep.getControl(CreepControl.class);
            
            if(creep.getLocalTranslation().distance(spatial.getLocalTranslation()) < attachRange ){
                reachable.add(creepControl);
            }
        }
        
        if(charges.getRemainingBullets() > 0){
            if(reachable.size() != 0){
                for(CreepControl control: reachable){
                    // shoot them
                    state.placeBeam((Geometry)spatial, (Geometry)control.getSpatial());

                    // decrease creep health
                    control.setCreepHealth(control.getCreepHealth()-1);
                    // decrease bullet counts
                    charges.setRemainingBullets(charges.getRemainingBullets()-1);
                    
                    //state.getRootNode().detachChild(geometry);
                }
            }
        }
        
        int numOfChild = state.getBeamNode().getChildren().size();
        if(numOfChild !=0 )
            state.getBeamNode().getChildren().remove(0);
    }
    
    
}
