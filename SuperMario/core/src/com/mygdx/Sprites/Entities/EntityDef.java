package com.mygdx.Sprites.Entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Class to define the vector of the position of the entity
 */
public class EntityDef {
    public Vector2 position;
    public Class<?> type;

    /**
     * Construtor for the entity definition
     * @param position
     * @param type
     */
    public EntityDef(Vector2 position, Class<?> type){
        this.position = position;
        this.type = type;
    }
}
