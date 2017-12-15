package com.almasb.fxglgames.spaceinvaders.level;

import static com.almasb.fxgl.app.DSLKt.geti;
import static com.almasb.fxglgames.spaceinvaders.Config.ENEMIES_PER_ROW;
import static com.almasb.fxglgames.spaceinvaders.Config.ENEMY_ROWS;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.control.RandomMoveControl;
import com.almasb.fxglgames.spaceinvaders.Config;
import com.almasb.fxglgames.spaceinvaders.SpaceInvadersApp;

import javafx.geometry.Rectangle2D;

public class Level0 extends SpaceLevel {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		for (int y = 0; y < ENEMY_ROWS; y++) {
            for (int x = 0; x < ENEMIES_PER_ROW; x++) {
                GameEntity enemy = spawnEnemy((x*ENEMIES_PER_ROW+y)*40,400);
               
            }
        }

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
