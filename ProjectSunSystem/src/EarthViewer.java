import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EarthViewer extends Application {

	private static final double	sun_RADIUS		= 200;
	private static final double	earth_RADIUS	= 100;
	private static final double	um_RADIUSa		= 700;
	private static final double	um_RADIUSb		= 550;
	private static double		angle			= 0;
	private static final double	step			= 0.007;
	private static final double	VIEWPORT_SIZEX	= 960;
	private static final double	VIEWPORT_SIZEY	= 540;
	private static final double	ROTATE_SECS		= 30;

	private static final double	sMAP_WIDTH		= 3000 / 2d;
	private static final double	sMAP_HEIGHT		= 1500 / 2d;
	private static final double	eMAP_WIDTH		= 8192 / 2d;
	private static final double	eMAP_HEIGHT		= 4096 / 2d;

	private static final String	SUN_MAP			= "http://www.nasa.gov/images/content/700328main_20121014_003615_flat.jpg";
	private static final String	EARTH_MAP		= "http://naturalearth.springercarto.com/ne3_data/8192/textures/1_earth_8k.jpg";
	private static final String	MOON_MAP		= "http://vignette4.wikia.nocookie.net/crossing-jordan/images/1/14/Schwarz.png/revision/latest?cb=20100710033304&path-prefix=de";

	Point3D						earth			= new Point3D();
	Point3D						sun				= new Point3D();

	private Sphere buildSunScene() {
		Sphere sun = new Sphere(sun_RADIUS);

		sun.setTranslateX(VIEWPORT_SIZEX / 2d);
		sun.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial sunMaterial = new PhongMaterial();
		sunMaterial.setDiffuseMap(new Image(SUN_MAP, sMAP_WIDTH, sMAP_HEIGHT, true, true));

		sun.setMaterial(sunMaterial);

		return sun;
	}

	private Sphere buildMoonScene() {
		Sphere sun = new Sphere(sun_RADIUS);

		sun.setTranslateX(VIEWPORT_SIZEX / 2d);
		sun.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial sunMaterial = new PhongMaterial();
		sunMaterial.setDiffuseMap(new Image(MOON_MAP, sMAP_WIDTH, sMAP_HEIGHT, true, true));

		sun.setMaterial(sunMaterial);

		return sun;
	}

	private Sphere buildEarthScene() {
		Sphere earth = new Sphere(earth_RADIUS);

		earth.setTranslateX(VIEWPORT_SIZEX / 2d);
		earth.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(EARTH_MAP, eMAP_WIDTH, eMAP_HEIGHT, true, true));

		earth.setMaterial(earthMaterial);

		return earth;
	}

	@Override
	public void start(Stage stage) {
		sun.setAll(960 - sun_RADIUS, 540 - sun_RADIUS, 0);
		GridPane g1 = new GridPane();
		g1.getChildren().addAll(buildSunScene(), buildEarthScene());

		g1.getChildren().get(0).setTranslateX(960 - sun_RADIUS);
		g1.getChildren().get(0).setTranslateY(540 - sun_RADIUS);

		Scene scene = new Scene(g1, VIEWPORT_SIZEX, VIEWPORT_SIZEY, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.rgb(0, 0, 0));

		PerspectiveCamera camera = new PerspectiveCamera();
		// camera.setTranslateZ(-1000);
		// camera.setNearClip(0.1);
		// camera.setFarClip(200.0);
		// camera.setFieldOfView(35);
		scene.setCamera(camera);
		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);

		Timeline tl = new Timeline();
		tl.setCycleCount(Animation.INDEFINITE);
		KeyFrame moveEarth = new KeyFrame(Duration.seconds(.0200), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				earth.x = (int) (um_RADIUSa * Math.sin(angle)) + sun.x;
				earth.z = (int) (um_RADIUSb * Math.cos(angle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(earth.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(earth.z);
				angle += step;
				angle %= 360;
			}
		});

		tl.getKeyFrames().add(moveEarth);
		tl.play();

		rotateAroundYAxis(g1.getChildren().get(0)).play();
		rotateAroundYAxis(g1.getChildren().get(1)).play();
	}

	private RotateTransition rotateAroundYAxis(Node node) {
		RotateTransition rotate = new RotateTransition(Duration.seconds(ROTATE_SECS), node);
		rotate.setAxis(Rotate.Y_AXIS);
		rotate.setFromAngle(360);
		rotate.setToAngle(0);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setCycleCount(RotateTransition.INDEFINITE);

		return rotate;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
