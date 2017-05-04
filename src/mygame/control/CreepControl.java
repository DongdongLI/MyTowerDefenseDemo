/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.control;

import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import mygame.GamePlayAppState;

/**
 *
 * @author dli
 */
public class CreepControl extends AbstractControl{

    private GamePlayAppState state;
    
    public CreepControl(GamePlayAppState state) {
        this.state = state;
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }

    @Override
    protected void controlUpdate(float tpf) {
        
        if(spatial.getLocalTranslation().getZ() == 0 && getCreepHealth() > 0){
            // TOUCH DOWN!
            // player health minus 1, creep detach itself
            state.setHealth(state.getHealth()-1);
            spatial.removeFromParent();
        }
        if(getCreepHealth() <= 0){
            // creep is dead
            // add bonus to player and detach it self
            state.setBudget(state.getBudget()+1);
            spatial.removeFromParent();
        }
        
        if(getCreepHealth() >0 ){
            spatial.move(0, 0, -tpf);
        }
    }

    public Integer getCreepHealth() {
        return spatial.getUserData("health");
    }
    
    public Integer getCreepIndex() {
        return spatial.getUserData("index");
    }
    
    public void setCreepHealth(Integer health){
        spatial.setUserData("health", health);
    }
}
