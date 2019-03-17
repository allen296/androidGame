package Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Lol;



public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    public static int lvl;

    private Label countdownLabel;
    public Label lvlLabel;
    //Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label antonioLabel;

    public Hud (SpriteBatch sb){
        //worldTimer=200;
        timeCount=0;
        lvl =1;

        viewport=new FitViewport(Lol.V_WIDTH,Lol.V_HEIGHT,new OrthographicCamera());
        stage =new Stage(viewport,sb);

        Table table= new Table();
        table.top();
        table.setFillParent(true);

        //countdownLabel=new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        lvlLabel =new Label("NIVEL "+lvl,new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        //timeLabel=new Label("TIEMPO",new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        levelLabel=new Label("ALDEA",new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        //worldLabel=new Label( "RUNATERRA",new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        antonioLabel=new Label("ANTONIO",new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));


        //colocamos en las posiciones los distintos labels
        table.add(antonioLabel).expandX().padTop(10);
        table.add(levelLabel).expandX();
        //table.add(worldLabel).expandX().padTop(10);
        //table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(lvlLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
