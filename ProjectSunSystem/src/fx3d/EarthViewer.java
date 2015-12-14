package fx3d;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EarthViewer extends Application {

	private static final double	sun_RADIUS		= 200;
	private static final double	earth_RADIUS	= 100;
	private static final double	VIEWPORT_SIZE	= 800;
	private static final double	ROTATE_SECS		= 30;

	private static final double	sMAP_WIDTH		= 3000 / 2d;
	private static final double	sMAP_HEIGHT		= 1500 / 2d;
	private static final double	eMAP_WIDTH		= 8192 / 2d;
	private static final double	eMAP_HEIGHT		= 4096 / 2d;

	private static final String	SUN_MAP			= "http://www.nasa.gov/images/content/700328main_20121014_003615_flat.jpg";
	private static final String	EARTH_MAP		= "http://naturalearth.springercarto.com/ne3_data/8192/textures/1_earth_8k.jpg";
	private static final String	MOON_MAP		= "http://planetmaker.wthr.us/img/sun_specularmap_flat_8192x4096.jpg";

	private Sphere buildSunScene() {
		Sphere sun = new Sphere(sun_RADIUS);
		sun.setTranslateX(VIEWPORT_SIZE / 2d - 200);
		sun.setTranslateY(VIEWPORT_SIZE / 2d);

		PhongMaterial sunMaterial = new PhongMaterial();
		sunMaterial.setDiffuseMap(new Image(SUN_MAP, sMAP_WIDTH, sMAP_HEIGHT, true, true));

		sun.setMaterial(sunMaterial);
		// asdasda
		return sun;
	}

	private Sphere buildEarthScene() {
		Sphere earth = new Sphere(earth_RADIUS);
		earth.setTranslateX(VIEWPORT_SIZE / 2d + 200);
		earth.setTranslateY(VIEWPORT_SIZE / 2d);

		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(EARTH_MAP, eMAP_WIDTH, eMAP_HEIGHT, true, true));

		earth.setMaterial(earthMaterial);

		return earth;
	}

	@Override
	public void start(Stage stage) {
		Group group = new Group();
		group.getChildren().add(buildSunScene());
		group.getChildren().add(buildEarthScene());

		Scene scene = new Scene(new StackPane(group), VIEWPORT_SIZE, VIEWPORT_SIZE, true, SceneAntialiasing.BALANCED);

		scene.setFill(Color.rgb(0, 0, 0));

		scene.setCamera(new PerspectiveCamera());

		stage.setScene(scene);
		stage.show();

		stage.setFullScreen(true);

		rotateAroundYAxis(group).play();
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
