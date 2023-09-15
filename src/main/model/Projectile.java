package model;

import java.awt.geom.Rectangle2D;

public abstract class Projectile {

    private Rectangle2D.Float hitbox;
    private Rectangle2D.Float attackbox;

    public Projectile() {
        initAttackBox();
        initHitbox();
    }

    public abstract void draw();

    public abstract void update();

    public abstract void move();

    protected void initHitbox() {
        hitbox = new Rectangle2D.Float();
    }

    protected void initAttackBox() {
        attackbox = new Rectangle2D.Float();
    }

}
