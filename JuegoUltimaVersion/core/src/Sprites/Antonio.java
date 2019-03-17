package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Lol;

import Screens.PlayScreen;

public class Antonio extends Actor {
    private Sprite sprite;
    public World world;
    public Body b2body;
    private TextureRegion marioStand;


    public Antonio(World world, PlayScreen screen, Sprite sprite){
        this.world=world;
        defineAntonio();
        sprite= new Sprite

    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void defineAntonio(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(60/Lol.PPM,130/Lol.PPM);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        CircleShape shape =new CircleShape();
        shape.setRadius(5/Lol.PPM);

        fdef.shape=shape;
        b2body.createFixture(fdef);


    }

}
