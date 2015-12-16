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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SunSystemViewer extends Application {

	private static final double	sun_RADIUS			= 600;
	private static final double	Merkur_RADIUS		= 24;
	private static final double	Venus_RADIUS		= 60;
	private static final double	earth_RADIUS		= 100;
	private static final double	moon_RADIUS			= 10;
	private static final double	Mars_RADIUS			= 33;
	private static final double	Jupiter_RADIUS		= 400;
	private static final double	Saturn_RADIUS		= 300;
	private static final double	Uranus_RADIUS		= 250;
	private static final double	Neptun_RADIUS		= 240;

	private static final double	um_Merkur_RADIUSa	= 400;
	private static final double	um_Merkur_RADIUSb	= 285;
	private static final double	um_Venus_RADIUSa	= 700;
	private static final double	um_Venus_RADIUSb	= 500;
	private static final double	um_earth_RADIUSa	= 1700;
	private static final double	um_earth_RADIUSb	= 1214;
	private static final double	um_Mars_RADIUSa		= 2500;
	private static final double	um_Mars_RADIUSb		= 1785;
	private static final double	um_Jupiter_RADIUSa	= 4000;
	private static final double	um_Jupiter_RADIUSb	= 2857;
	private static final double	um_Saturn_RADIUSa	= 6500;
	private static final double	um_Saturn_RADIUSb	= 4642;
	private static final double	um_Uranus_RADIUSa	= 7500;
	private static final double	um_Uranus_RADIUSb	= 5357;
	private static final double	um_Neptun_RADIUSa	= 10000;
	private static final double	um_Neptun_RADIUSb	= 7142;

	private static double		Merkurangle			= -180;
	private static double		Venusangle			= 90;
	private static double		Earthangle			= -90;
	private static double		Moonangle			= 45;
	private static double		Marsangle			= -45;
	private static double		Jupiterangle		= 320;
	private static double		Saturnangle			= 280;
	private static double		Uranusangle			= 240;
	private static double		Neptunangle			= 200;

	private static final double	Merkurstep			= 0.1;
	private static final double	Venusstep			= 0.0391493584808256;
	private static final double	Earthstep			= 0.0240842039555818;
	private static final double	Moonstep			= 0.0321974840511388;
	private static final double	Marsstep			= 0.0128051762787854;
	private static final double	Jupiterstep			= 0.0020302962322887;
	private static final double	Saturnstep			= 0.0008176054572965;
	private static final double	Uranusstep			= 0.0002866791724367;
	private static final double	Neptunstep			= 0.0001461508826724;

	private static final double	VIEWPORT_SIZEX		= 960;
	private static final double	VIEWPORT_SIZEY		= 540;

	private static final double	sunROTATE_SECS		= 10;
	private static final double	MerkurROTATE_SECS	= 0.009291249;
	private static final double	VenusROTATE_SECS	= 0.002357957;
	private static final double	EarthROTATE_SECS	= 0.0608021041;
	private static final double	MoonROTATE_SECS		= 3;
	private static final double	MarsROTATE_SECS		= 0.598634604;
	private static final double	JupiterROTATE_SECS	= 2.609798749;
	private static final double	SaturnROTATE_SECS	= 2.172901589;
	private static final double	UranusROTATE_SECS	= 2.609798749;
	private static final double	NeptunROTATE_SECS	= 2.609798749;

	private static final double	sunMAP_WIDTH		= 3000 / 2d;
	private static final double	sunMAP_HEIGHT		= 1500 / 2d;
	private static final double	MerkurMAP_WIDTH		= 1024 / 2d;
	private static final double	MerkurMAP_HEIGHT	= 512 / 2d;
	private static final double	VenusMAP_WIDTH		= 4096 / 2d;
	private static final double	VenusMAP_HEIGHT		= 2048 / 2d;
	private static final double	earthMAP_WIDTH		= 8192 / 2d;
	private static final double	earthMAP_HEIGHT		= 4096 / 2d;
	private static final double	MoonMAP_WIDTH		= 4000 / 2d;
	private static final double	MoonMAP_HEIGHT		= 2000 / 2d;
	private static final double	MarsMAP_WIDTH		= 8192 / 2d;
	private static final double	MarsMAP_HEIGHT		= 4096 / 2d;
	private static final double	JupiterMAP_WIDTH	= 4096 / 2d;
	private static final double	JupiterMAP_HEIGHT	= 2048 / 2d;
	private static final double	SaturnMAP_WIDTH		= 1800 / 2d;
	private static final double	SaturnMAP_HEIGHT	= 900 / 2d;
	private static final double	UranusMAP_WIDTH		= 4000 / 2d;
	private static final double	UranusMAP_HEIGHT	= 2000 / 2d;
	private static final double	NeptunMAP_WIDTH		= 4000 / 2d;
	private static final double	NeptunMAP_HEIGHT	= 2000 / 2d;

	private static final String	SUN_MAP				= "http://i.imgur.com/uUDu05S.jpg";
	private static final String	Merkur_MAP			= "http://i.imgur.com/VI9tFf3.jpg";
	private static final String	Venus_MAP			= "http://i.imgur.com/499ruBn.jpg";
	private static final String	EARTH_MAP			= "http://i.imgur.com/K0egsBw.jpg";
	private static final String	MOON_MAP			= "http://i.imgur.com/WgBAJEt.jpg";
	private static final String	Mars_MAP			= "http://i.imgur.com/xAXOyWO.jpg";
	private static final String	Jupiter_MAP			= "http://i.imgur.com/lrzry2n.jpg";
	private static final String	Saturn_MAP			= "http://i.imgur.com/6aXtrX8.jpg";
	private static final String	Uranus_MAP			= "http://i.imgur.com/mrPhKTx.jpg";
	private static final String	Neptun_MAP			= "http://i.imgur.com/gA0gayx.jpg";

	private Point3D				sun					= new Point3D();
	private Point3D				Merkur				= new Point3D();
	private Point3D				Venus				= new Point3D();
	private Point3D				earth				= new Point3D();
	private Point3D				moon				= new Point3D();
	private Point3D				Mars				= new Point3D();
	private Point3D				Jupiter				= new Point3D();
	private Point3D				Saturn				= new Point3D();
	private Point3D				Uranus				= new Point3D();
	private Point3D				Neptun				= new Point3D();

	private int					x, y, z;
	private Rotate				rotateX, rotateY, rotateZ;
	private boolean				pause				= false;

	private Sphere buildSunScene() {
		Sphere sun = new Sphere(sun_RADIUS);

		sun.setTranslateX(VIEWPORT_SIZEX / 2d);
		sun.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial sunMaterial = new PhongMaterial();
		sunMaterial.setDiffuseMap(new Image(SUN_MAP, sunMAP_WIDTH, sunMAP_HEIGHT, true, true));

		sun.setMaterial(sunMaterial);

		return sun;
	}

	private Sphere buildMerkurScene() {
		Sphere Merkur = new Sphere(Merkur_RADIUS);

		Merkur.setTranslateX(VIEWPORT_SIZEX / 2d);
		Merkur.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial MerkurMaterial = new PhongMaterial();
		MerkurMaterial.setDiffuseMap(new Image(Merkur_MAP, MerkurMAP_WIDTH, MerkurMAP_HEIGHT, true, true));

		Merkur.setMaterial(MerkurMaterial);

		return Merkur;
	}

	private Sphere buildVenusScene() {
		Sphere Venus = new Sphere(Venus_RADIUS);

		Venus.setTranslateX(VIEWPORT_SIZEX / 2d);
		Venus.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial VenusMaterial = new PhongMaterial();
		VenusMaterial.setDiffuseMap(new Image(Venus_MAP, VenusMAP_WIDTH, VenusMAP_HEIGHT, true, true));

		Venus.setMaterial(VenusMaterial);

		return Venus;
	}

	private Sphere buildEarthScene() {
		Sphere earth = new Sphere(earth_RADIUS);

		earth.setTranslateX(VIEWPORT_SIZEX / 2d);
		earth.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial earthMaterial = new PhongMaterial();
		earthMaterial.setDiffuseMap(new Image(EARTH_MAP, earthMAP_WIDTH, earthMAP_HEIGHT, true, true));

		earth.setMaterial(earthMaterial);

		return earth;
	}

	private Sphere buildMoonScene() {
		Sphere moon = new Sphere(moon_RADIUS);

		moon.setTranslateX(VIEWPORT_SIZEX / 2d);
		moon.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial moonMaterial = new PhongMaterial();
		moonMaterial.setDiffuseMap(new Image(MOON_MAP, MoonMAP_WIDTH, MoonMAP_HEIGHT, true, true));

		moon.setMaterial(moonMaterial);

		return moon;
	}

	private Sphere buildMarsScene() {
		Sphere Mars = new Sphere(Mars_RADIUS);

		Mars.setTranslateX(VIEWPORT_SIZEX / 2d);
		Mars.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial MarsMaterial = new PhongMaterial();
		MarsMaterial.setDiffuseMap(new Image(Mars_MAP, MarsMAP_WIDTH, MarsMAP_HEIGHT, true, true));

		Mars.setMaterial(MarsMaterial);

		return Mars;
	}

	private Sphere buildJupiterScene() {
		Sphere Jupiter = new Sphere(Jupiter_RADIUS);

		Jupiter.setTranslateX(VIEWPORT_SIZEX / 2d);
		Jupiter.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial JupiterMaterial = new PhongMaterial();
		JupiterMaterial.setDiffuseMap(new Image(Jupiter_MAP, JupiterMAP_WIDTH, JupiterMAP_HEIGHT, true, true));

		Jupiter.setMaterial(JupiterMaterial);

		return Jupiter;
	}

	private Sphere buildSaturnScene() {
		Sphere Saturn = new Sphere(Saturn_RADIUS);

		Saturn.setTranslateX(VIEWPORT_SIZEX / 2d);
		Saturn.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial SaturnMaterial = new PhongMaterial();
		SaturnMaterial.setDiffuseMap(new Image(Saturn_MAP, SaturnMAP_WIDTH, SaturnMAP_HEIGHT, true, true));

		Saturn.setMaterial(SaturnMaterial);

		return Saturn;
	}

	private Sphere buildUranusScene() {
		Sphere Uranus = new Sphere(Uranus_RADIUS);

		Uranus.setTranslateX(VIEWPORT_SIZEX / 2d);
		Uranus.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial UranusMaterial = new PhongMaterial();
		UranusMaterial.setDiffuseMap(new Image(Uranus_MAP, UranusMAP_WIDTH, UranusMAP_HEIGHT, true, true));

		Uranus.setMaterial(UranusMaterial);

		return Uranus;
	}

	private Sphere buildNeptunScene() {
		Sphere Neptun = new Sphere(Neptun_RADIUS);

		Neptun.setTranslateX(VIEWPORT_SIZEX / 2d);
		Neptun.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial NeptunMaterial = new PhongMaterial();
		NeptunMaterial.setDiffuseMap(new Image(Neptun_MAP, NeptunMAP_WIDTH, NeptunMAP_HEIGHT, true, true));

		Neptun.setMaterial(NeptunMaterial);

		return Neptun;
	}

	@Override
	public void start(Stage stage) {
		sun.setAll(960 - sun_RADIUS, 540 - sun_RADIUS, 0);
		GridPane g1 = new GridPane();
		g1.getChildren().addAll(buildSunScene(), buildMerkurScene(), buildVenusScene(), buildEarthScene(), buildMoonScene(), buildMarsScene(), buildJupiterScene(), buildSaturnScene(), buildUranusScene(), buildNeptunScene());

		g1.getChildren().get(0).setTranslateX(960 - sun_RADIUS);
		g1.getChildren().get(0).setTranslateY(540 - sun_RADIUS);

		Scene scene = new Scene(g1, VIEWPORT_SIZEX, VIEWPORT_SIZEY, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.rgb(0, 0, 0));

		PerspectiveCamera camera = new PerspectiveCamera();
		camera.setTranslateX(960 - sun_RADIUS);
		camera.setTranslateY(-16000);
		camera.setTranslateZ(0);
		camera.setRotationAxis(Rotate.X_AXIS);
		camera.setRotate(-90);

		scene.setCamera(camera);
		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);

		Timeline tl = new Timeline();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()) {
					case A:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(1, Rotate.Z_AXIS));
						z += rotateZ.getAngle();
						break;

					case D:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(-1, Rotate.Z_AXIS));
						z += rotateZ.getAngle();
						break;

					case W:
						camera.getTransforms().addAll(rotateX = new Rotate(1, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						x += rotateX.getAngle();
						break;

					case S:
						camera.getTransforms().addAll(rotateX = new Rotate(-1, Rotate.X_AXIS), rotateY = new Rotate(0, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						x += rotateX.getAngle();
						break;

					case Q:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(1, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						y += rotateY.getAngle();
						break;

					case E:
						camera.getTransforms().addAll(rotateX = new Rotate(0, Rotate.X_AXIS), rotateY = new Rotate(-1, Rotate.Y_AXIS), rotateZ = new Rotate(0, Rotate.Z_AXIS));
						y += rotateY.getAngle();
						break;

					case NUMPAD8:
						camera.setTranslateY(camera.getTranslateY() + 100);
						break;

					case NUMPAD2:
						camera.setTranslateY(camera.getTranslateY() - 100);
						break;

					case NUMPAD4:
						camera.setTranslateX(camera.getTranslateX() + 100);
						break;

					case NUMPAD6:
						camera.setTranslateX(camera.getTranslateX() - 100);
						break;

					case NUMPAD9:
						camera.setTranslateZ(camera.getTranslateZ() + 100);
						break;

					case NUMPAD1:
						camera.setTranslateZ(camera.getTranslateZ() - 100);
						break;

					case NUMPAD0:
						if (tl.getStatus() == Animation.Status.RUNNING) {
							tl.pause();
						} else {
							tl.play();
						}
						break;

					case R:
						camera.setTranslateX(960 - sun_RADIUS);
						camera.setTranslateY(-16000);
						camera.setTranslateZ(0);
						camera.getTransforms().addAll(rotateX = new Rotate((x * (-1)), Rotate.X_AXIS), rotateY = new Rotate((y * (-1)), Rotate.Y_AXIS), rotateZ = new Rotate((z * (-1)), Rotate.Z_AXIS));
						x = 0;
						y = 0;
						z = 0;
						break;

					default:
						System.out.println("klappt nicht");
						break;
				}
			}
		});

		tl.setCycleCount(Animation.INDEFINITE);
		KeyFrame moveEarth = new KeyFrame(Duration.seconds(.0200), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				Merkur.x = (um_Merkur_RADIUSa + sun_RADIUS) * Math.sin(Merkurangle) + sun.x;
				Merkur.z = (um_Merkur_RADIUSb + sun_RADIUS) * Math.cos(Merkurangle) + sun.z;
				g1.getChildren().get(1).setTranslateX(Merkur.x + sun_RADIUS);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Merkur.z);

				Venus.x = (um_Venus_RADIUSa + sun_RADIUS) * Math.sin(Venusangle) + sun.x;
				Venus.z = (um_Venus_RADIUSb + sun_RADIUS) * Math.cos(Venusangle) + sun.z;
				g1.getChildren().get(2).setTranslateX(Venus.x + sun_RADIUS);
				g1.getChildren().get(2).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(2).setTranslateZ(Venus.z);

				earth.x = (um_earth_RADIUSa + sun_RADIUS) * Math.sin(Earthangle) + sun.x;
				earth.z = (um_earth_RADIUSb + sun_RADIUS) * Math.cos(Earthangle) + sun.z;
				g1.getChildren().get(3).setTranslateX(earth.x);
				g1.getChildren().get(3).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(3).setTranslateZ(earth.z);

				moon.x = Math.sin(Moonangle) * (earth_RADIUS * 2) + (earth.x);
				moon.z = Math.cos(Moonangle) * (earth_RADIUS * 2) + (earth.z);
				g1.getChildren().get(4).setTranslateX(moon.x + earth_RADIUS / 2);
				g1.getChildren().get(4).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(4).setTranslateZ(moon.z);

				Mars.x = (um_Mars_RADIUSa + sun_RADIUS) * Math.sin(Marsangle) + sun.x;
				Mars.z = (um_Mars_RADIUSb + sun_RADIUS) * Math.cos(Marsangle) + sun.z;
				g1.getChildren().get(5).setTranslateX(Mars.x);
				g1.getChildren().get(5).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(5).setTranslateZ(Mars.z);

				Jupiter.x = (um_Jupiter_RADIUSa + sun_RADIUS) * Math.sin(Jupiterangle) + sun.x;
				Jupiter.z = (um_Jupiter_RADIUSb + sun_RADIUS) * Math.cos(Jupiterangle) + sun.z;
				g1.getChildren().get(6).setTranslateX(Jupiter.x);
				g1.getChildren().get(6).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(6).setTranslateZ(Jupiter.z);

				Saturn.x = (um_Saturn_RADIUSa + sun_RADIUS) * Math.sin(Saturnangle) + sun.x;
				Saturn.z = (um_Saturn_RADIUSb + sun_RADIUS) * Math.cos(Saturnangle) + sun.z;
				g1.getChildren().get(7).setTranslateX(Saturn.x);
				g1.getChildren().get(7).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(7).setTranslateZ(Saturn.z);

				Uranus.x = (um_Uranus_RADIUSa + sun_RADIUS) * Math.sin(Uranusangle) + sun.x;
				Uranus.z = (um_Uranus_RADIUSb + sun_RADIUS) * Math.cos(Uranusangle) + sun.z;
				g1.getChildren().get(8).setTranslateX(Uranus.x);
				g1.getChildren().get(8).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(8).setTranslateZ(Uranus.z);

				Neptun.x = (um_Neptun_RADIUSa + sun_RADIUS) * Math.sin(Neptunangle) + sun.x;
				Neptun.z = (um_Neptun_RADIUSb + sun_RADIUS) * Math.cos(Neptunangle) + sun.z;
				g1.getChildren().get(9).setTranslateX(Neptun.x);
				g1.getChildren().get(9).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(9).setTranslateZ(Neptun.z);

				Merkurangle -= Merkurstep;
				Merkurangle %= -360;
				Venusangle -= Venusstep;
				Venusangle %= -360;
				Earthangle -= Earthstep;
				Earthangle %= -360;
				Moonangle -= Moonstep;
				Moonangle %= -360;
				Marsangle -= Marsstep;
				Marsangle %= -360;
				Jupiterangle -= Jupiterstep;
				Jupiterangle %= -360;
				Saturnangle -= Saturnstep;
				Saturnangle %= -360;
				Uranusangle -= Uranusstep;
				Uranusangle %= -360;
				Neptunangle -= Neptunstep;
				Neptunangle %= -360;
			}
		});

		tl.getKeyFrames().add(moveEarth);
		tl.play();

		rotateAroundYAxis(g1.getChildren().get(0), sunROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(1), MerkurROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(2), VenusROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(3), EarthROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(4), MoonROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(5), MarsROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(6), JupiterROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(7), SaturnROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(8), UranusROTATE_SECS).play();
		rotateAroundYAxis(g1.getChildren().get(9), NeptunROTATE_SECS).play();
	}

	private RotateTransition rotateAroundYAxis(Node node, double s) {
		RotateTransition rotate = new RotateTransition(Duration.seconds(s), node);
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
