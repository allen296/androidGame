package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import Tools.B2WorldCreator;

/**
 * @author Antonio Valladares García
 *
 * Clase principal en la que se reunen las demas clases para (hud, personaje, etc)
 */
public class PlayScreen implements Screen {


    private Lol game;
    private TextureAtlas atlas;
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

    /**
     * @author Antonio Valladares García
     * Constructor de la clase
     * @param game
     */
    public PlayScreen (Lol game){
        //atlas = new TextureAtlas("maps/mariogoomba.pack");
        this.game=game;
        b2dr=new Box2DDebugRenderer();
        gameCamera= new OrthographicCamera();

        //Hace que el aspecto sea mas o menos generico independientemente de la pantalla
        gamePort=new FitViewport(Lol.V_WIDTH/Lol.PPM,Lol.V_HEIGHT/Lol.PPM,gameCamera);

        //crea el hud en la pantalla
        hud=new Hud(game.batch);


        maploader= new TmxMapLoader();
        //Coge el mapa de la carpeta de assets
        map = new TmxMapLoader().load("maps/mapa1.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/Lol.PPM);

        gameCamera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        //Añade gravedad al mundo
        world=new World(new Vector2(0,-7f),true);

        //Añade las capas de colision al mapa y el mapa al mundo
        new B2WorldCreator(world,map);

        player = new Antonio(world, this);
    }

    /**
     * Working progress class
     * @return
     */
    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    /**
     * @author Antonio Valladares García
     *
     * @param dt
     * Clase que controla el movimiento a traves de las flechas de direccion. asi como la suma de nivel al
     * saltar para probar el guardado de datos
     */
    public void handleInput(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.b2body.applyLinearImpulse(new Vector2(0, 3f), player.b2body.getWorldCenter(), true);
            hud.lvl=hud.lvl+1;
            hud.lvlLabel.setText("NIVEL: "+hud.lvl);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
    }

    /**
     * @author Antonio Valladares García
     *
     * @param dt
     * Clase que actualiza la posicion de la camara, asi como de los elementos del mundo
     */
    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);

        gameCamera.position.x=player.b2body.getPosition().x;

        gameCamera.position.x=player.b2body.getPosition().x;
        gameCamera.update();;
    }

    /**
     * @author Antonio Valladares García
     * renderiza la camara, el fondo de pantalla y los elementos como el hud, world, etc
     * @param delta
     */
    @Override
        public void render(float delta) {

        update(delta);
        renderer.setView(gameCamera);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //renderiza las fisicas

        b2dr.render(world,gameCamera.combined);

//        game.batch.setProjectionMatrix(gameCamera.combined);
//        game.batch.begin();
//        player.draw(game.batch);
//        game.batch.end();


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }


    /**
     * @author Antonio Valladares García
     *
     * Redimensiona la pantalla en caso de ser necesario
     * @param width
     * @param height
     */
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

    /**
     * Metodo autogenerado para hacer dispose y evitar consumo excesivo de recursos
     */
    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
