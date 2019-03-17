package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Lol;

/**
 * @author Antonio Valladares García
 * Esta clase crea las capas de colisiones con el suelo
 */
public class B2WorldCreator {

    /**
     * Constructor de la clase B2WorldCreator
     * @param world
     * @param map
     */
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bdef=new BodyDef();
        PolygonShape shape=new PolygonShape();
        FixtureDef fdef=new FixtureDef();
        Body body;

        //Creando el cuerpo del suelo
        //Usa la capa de objetos del mapa que pongas(de los que he creado con tiledmap) (empezando desde 0 en la primera capa de abajo
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            //para algo que sea afectado por la gravedad dinamic, los kinematic es para objetos que no tienen velocidad
            // y solo son afectados por la gravedad (como plataformas)
            //static es para los suelos por ejemplo
            bdef.type= BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/Lol.PPM, (rect.getY()+rect.getHeight()/2)/Lol.PPM);

            body= world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/Lol.PPM, (rect.getHeight()/2)/Lol.PPM);
            fdef.shape=shape;
            body.createFixture(fdef);
        }
    }
}
