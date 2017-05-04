/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.control;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Line;
import mygame.GamePlayAppState;

/**
 *
 * @author dli
 */
public class BeamControl extends AbstractControl{

    Geometry tower;
    Geometry creep;
    GamePlayAppState state;

    public BeamControl(Geometry tower, Geometry creep, GamePlayAppState state) {
        this.tower = tower;
        this.creep = creep;
        this.state = state;
    }

    @Override
    protected void controlUpdate(float tpf) {
        state.placeBeam(tower, creep);
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }
}
