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

public class SunSystemViewer extends Application {

	private static final double	sun_RADIUS			= 69;
	private static final double	Merkur_RADIUS		= 24;
	private static final double	Venus_RADIUS		= 60;
	private static final double	earth_RADIUS		= 63;
	private static final double	moon_RADIUS			= 17;
	private static final double	Mars_RADIUS			= 33;
	private static final double	Jupiter_RADIUS		= 699;
	private static final double	Saturn_RADIUS		= 582;
	private static final double	Uranus_RADIUS		= 253;
	private static final double	Neptun_RADIUS		= 246;

	private static final double	um_Merkur_RADIUSa	= 200;
	private static final double	um_Merkur_RADIUSb	= 100;
	private static final double	um_Venus_RADIUSa	= 500;
	private static final double	um_Venus_RADIUSb	= 250;
	private static final double	um_earth_RADIUSa	= 800;
	private static final double	um_earth_RADIUSb	= 500;
	private static final double	um_Mars_RADIUSa		= 1000;
	private static final double	um_Mars_RADIUSb		= 750;
	private static final double	um_Jupiter_RADIUSa	= 1200;
	private static final double	um_Jupiter_RADIUSb	= 1000;
	private static final double	um_Saturn_RADIUSa	= 1400;
	private static final double	um_Saturn_RADIUSb	= 1250;
	private static final double	um_Uranus_RADIUSa	= 1600;
	private static final double	um_Uranus_RADIUSb	= 1500;
	private static final double	um_Neptun_RADIUSa	= 1800;
	private static final double	um_Neptun_RADIUSb	= 1750;
	
	private static double		Merkurangle			= 0;
	private static double		Venusangle			= 0;
	private static double		Earthangle			= 0;
	private static double		Moonangle			= 0;
	private static double		Marsangle			= 0;
	private static double		Jupiterangle		= 0;
	private static double		Saturnangle			= 0;
	private static double		Uranusangle			= 0;
	private static double		Neptunangle			= 0;
		
	private static final double	Merkurstep		= 0.014;
	private static final double	Venusstep		= 0.014;
	private static final double	Earthstep		= 0.007;
	private static final double	Moonstep		= 0.014;
	private static final double	Marsstep		= 0.014;
	private static final double	Jupiterstep		= 0.014;
	private static final double	Saturnstep		= 0.014;
	private static final double	Uranusstep		= 0.014;
	private static final double	Neptunstep		= 0.014;	
	
	private static final double	VIEWPORT_SIZEX			= 960;
	private static final double	VIEWPORT_SIZEY			= 540;
	
	private static final double	sunROTATE_MSECS			= 30;
	private static final double	MerkurROTATE_MSECS		= 30;
	private static final double	VenusROTATE_MSECS		= 30;
	private static final double	EarthROTATE_MSECS		= 30;
	private static final double	MoonROTATE_MSECS		= 30;
	private static final double	MarsROTATE_MSECS		= 30;
	private static final double	JupiterROTATE_MSECS		= 30;
	private static final double	SaturnROTATE_MSECS		= 30;
	private static final double	UranusROTATE_MSECS		= 30;
	private static final double	NeptunROTATE_MSECS		= 30;

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
	private static final double	UranusMAP_WIDTH		= 4000/ 2d;
	private static final double	UranusMAP_HEIGHT	= 2000 / 2d;
	private static final double	NeptunMAP_WIDTH		= 720 / 2d;
	private static final double	NeptunMAP_HEIGHT	= 360 / 2d;
	

	private static final String	SUN_MAP			= "http://www.nasa.gov/images/content/700328main_20121014_003615_flat.jpg";
	private static final String	Merkur_MAP		= "http://www.pur3d.de/images/design_pur3d/upload/space33.jpg";
	private static final String	Venus_MAP		= "http://laps.noaa.gov/albers/sos/venus/venus4/venus4_rgb_cyl_www.jpg";
	private static final String	EARTH_MAP		= "http://naturalearth.springercarto.com/ne3_data/8192/textures/1_earth_8k.jpg";
	private static final String	MOON_MAP		= "http://www.uni-weimar.de/architektur/dsmbfl/blog/alotta/user/home/img/000/000//452.jpg";
	private static final String	Mars_MAP		= "http://sos.noaa.gov/ge/astronomy/mars/textures/m46_color_8k.jpg";
	private static final String	Jupiter_MAP		= "http://laps.noaa.gov/albers/sos/jupiter/jupiter/jupiter_rgb_cyl_www.jpg";
	private static final String	Saturn_MAP		= "http://webuser.hs-furtwangen.de/~kieferaa/grundstudium/Computergrafik/CG/planets/planets/tex/saturnmap.jpg";
	private static final String	Uranus_MAP		= "http://orig03.deviantart.net/a155/f/2010/235/d/1/blue_gas_giant_texture_by_avmorgan.jpg";
	private static final String	Neptun_MAP		= "http://csdrive.srru.ac.th/55122420111/texture/neptun.jpg";

	Point3D						sun				= new Point3D();
	Point3D						Merkur			= new Point3D();
	Point3D						Venus			= new Point3D();
	Point3D						earth			= new Point3D();
	Point3D						moon			= new Point3D();
	Point3D						Mars			= new Point3D();
	Point3D						Jupiter			= new Point3D();
	Point3D						Saturn			= new Point3D();
	Point3D						Uranus			= new Point3D();
	Point3D						Neptun			= new Point3D();
	

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
		MerkurMaterial.setDiffuseMap(new Image(SUN_MAP, MerkurMAP_WIDTH, MerkurMAP_HEIGHT, true, true));

		Merkur.setMaterial(MerkurMaterial);

		return Merkur;
	}
	
	private Sphere buildVenusScene() {
		Sphere Venus = new Sphere(Venus_RADIUS);

		Venus.setTranslateX(VIEWPORT_SIZEX / 2d);
		Venus.setTranslateY(VIEWPORT_SIZEY / 2d);

		PhongMaterial VenusMaterial = new PhongMaterial();
		VenusMaterial.setDiffuseMap(new Image(SUN_MAP, VenusMAP_WIDTH, VenusMAP_HEIGHT, true, true));

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

		PhongMaterial sunMaterial = new PhongMaterial();
		sunMaterial.setDiffuseMap(new Image(MOON_MAP, MoonMAP_WIDTH, MoonMAP_HEIGHT, true, true));

		moon.setMaterial(sunMaterial);

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
		g1.getChildren().addAll(buildSunScene(),buildMerkurScene(),buildVenusScene(),buildEarthScene(), buildMoonScene(),buildMarsScene(),buildJupiterScene(),buildSaturnScene(),buildUranusScene(),buildNeptunScene());

		g1.getChildren().get(0).setTranslateX(960 - sun_RADIUS);
		g1.getChildren().get(0).setTranslateY(540 - sun_RADIUS);

		Scene scene = new Scene(g1, VIEWPORT_SIZEX, VIEWPORT_SIZEY, true, SceneAntialiasing.BALANCED);
		scene.setFill(Color.rgb(0, 0, 0));

		PerspectiveCamera camera = new PerspectiveCamera();
		camera.setTranslateZ(300);
		camera.setTranslateY(-6000);
		camera.setTranslateX(0);
		camera.setRotationAxis(Rotate.X_AXIS);
		camera.setRotate(-90);
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

				Merkur.x = (int) (um_Merkur_RADIUSa * Math.sin(Merkurangle)) + sun.x;
				Merkur.z = (int) (um_Merkur_RADIUSb * Math.cos(Merkurangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(Merkur.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Merkur.z);

				Venus.x = (int) (um_Venus_RADIUSa * Math.sin(Venusangle)) + sun.x;
				Venus.z = (int) (um_Venus_RADIUSb * Math.cos(Venusangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(Venus.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Venus.z);
				
				earth.x = (int) (um_earth_RADIUSa * Math.sin(Earthangle)) + sun.x;
				earth.z = (int) (um_earth_RADIUSb * Math.cos(Earthangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(earth.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(earth.z);

				moon.x = (int) (Math.sin(Moonangle) * (earth_RADIUS + 100) + (earth.x));
				moon.z = (int) (Math.cos(Moonangle) * (earth_RADIUS + 100) + (earth.z));
				g1.getChildren().get(2).setTranslateX(moon.x + earth_RADIUS / 2);
				g1.getChildren().get(2).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(2).setTranslateZ(moon.z);
				
				Mars.x = (int) (um_Mars_RADIUSa * Math.sin(Marsangle)) + sun.x;
				Mars.z = (int) (um_Mars_RADIUSb * Math.cos(Marsangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(Mars.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Mars.z);
				
				Jupiter.x = (int) (um_Jupiter_RADIUSa * Math.sin(Jupiterangle)) + sun.x;
				Jupiter.z = (int) (um_Jupiter_RADIUSb * Math.cos(Jupiterangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(Jupiter.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Jupiter.z);
				
				Saturn.x = (int) (um_Saturn_RADIUSa * Math.sin(Saturnangle)) + sun.x;
				Saturn.z = (int) (um_Saturn_RADIUSb * Math.cos(Saturnangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(Saturn.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Saturn.z);
				
				Uranus.x = (int) (um_Uranus_RADIUSa * Math.sin(Uranusangle)) + sun.x;
				Uranus.z = (int) (um_Uranus_RADIUSb * Math.cos(Uranusangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(Uranus.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Uranus.z);
				
				Neptun.x = (int) (um_Neptun_RADIUSa * Math.sin(Neptunangle)) + sun.x;
				Neptun.z = (int) (um_Neptun_RADIUSb * Math.cos(Neptunangle)) + sun.z;
				g1.getChildren().get(1).setTranslateX(Neptun.x);
				g1.getChildren().get(1).setTranslateY(540 - sun_RADIUS);
				g1.getChildren().get(1).setTranslateZ(Neptun.z);
				

				Merkurangle += Merkurstep;
				Merkurangle %= 360;
				Venusangle += Venusstep;
				Venusangle %= 360;
				Earthangle += Earthstep;
				Earthangle %= 360;
				Moonangle += Moonstep;
				Moonangle %= 360;
				Marsangle += Marsstep;
				Marsangle %= 360;
				Jupiterangle += Jupiterstep;
				Jupiterangle %= 360;
				Saturnangle += Saturnstep;
				Saturnangle %= 360;
				Uranusangle += Uranusstep;
				Uranusangle %= 360;
				Neptunangle += Neptunstep;
				Neptunangle %= 360;
			}
		});

		tl.getKeyFrames().add(moveEarth);
		tl.play();

		rotateAroundYAxis(g1.getChildren().get(0), 10000000).play();
		rotateAroundYAxis(g1.getChildren().get(1), MerkurROTATE_MSECS).play();
		rotateAroundYAxis(g1.getChildren().get(2), VenusROTATE_MSECS).play();
		rotateAroundYAxis(g1.getChildren().get(3), EarthROTATE_MSECS).play();
        rotateAroundYAxis(g1.getChildren().get(3), MoonROTATE_MSECS).play();
        rotateAroundYAxis(g1.getChildren().get(4), MarsROTATE_MSECS).play();
        rotateAroundYAxis(g1.getChildren().get(5), JupiterROTATE_MSECS).play();
        rotateAroundYAxis(g1.getChildren().get(6), SaturnROTATE_MSECS).play();
        rotateAroundYAxis(g1.getChildren().get(7), UranusROTATE_MSECS).play();
        rotateAroundYAxis(g1.getChildren().get(8), NeptunROTATE_MSECS).play();
	}

	private RotateTransition rotateAroundYAxis(Node node, double s) {
        RotateTransition rotate = new RotateTransition(Duration.millis(s), node);
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
