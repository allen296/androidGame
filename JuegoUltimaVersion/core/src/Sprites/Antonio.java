package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Lol;

public class Antonio extends Sprite {
    public World world;
    public Body b2body;

    public Antonio(World world){
        this.world=world;
        defineAntonio();

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
