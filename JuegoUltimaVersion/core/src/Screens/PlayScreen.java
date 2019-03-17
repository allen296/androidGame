package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Lol;

import Scenes.Hud;
import Sprites.Antonio;

public class PlayScreen implements Screen {

    private Lol game;
    private Hud hud;
    private Antonio player;


    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer render;

    private OrthographicCamera gameCamera;
    private Viewport gamePort;
    private OrthogonalTiledMapRenderer renderer;

    //Fisicas box2d

    private World world;
    private Box2DDebugRenderer b2dr;


    public PlayScreen (Lol game){
        this.game=game;
        world=new World(new Vector2(0,-7f),true);
        b2dr=new Box2DDebugRenderer();
        player = new Antonio(world);

        gameCamera= new OrthographicCamera();

        //Hace que el aspecto sea mas o menos generico independientemente de la pantalla
        gamePort=new FitViewport(Lol.V_WIDTH/Lol.PPM,Lol.V_HEIGHT/Lol.PPM,gameCamera);

        //crea el hud en la pantalla
        hud=new Hud(game.batch);


        maploader= new TmxMapLoader();
        map = new TmxMapLoader().load("maps/mapa1.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/Lol.PPM);

        gameCamera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);



        BodyDef bdef=new BodyDef();
        PolygonShape shape=new PolygonShape();
        FixtureDef fdef=new FixtureDef();
        Body body;

        //Creando el cuerpo del suelo
        //Usa la capa de objetos del mapa que pongas (empezando desde 0 en la primera capa de abajo
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            //para algo que sea afectado por la gravedad dinamic, los kinematic es para objetos que no tienen velocidad y solo son afectados por la gravedad (como plataformas)
            bdef.type= BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/Lol.PPM, (rect.getY()+rect.getHeight()/2)/Lol.PPM);

            body= world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/Lol.PPM, (rect.getHeight()/2)/Lol.PPM);
            fdef.shape=shape;
            body.createFixture(fdef);

        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gameCamera.position.x+=(100*dt)/Lol.PPM;
            //player.b2body.applyLinearImpulse(0,4f,0,0,true);
        }
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);

        gameCamera.update();;
    }

    @Override
    public void render(float delta) {

        update(delta);
        renderer.setView(gameCamera);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //renderiza las fisicas

        b2dr.render(world,gameCamera.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}