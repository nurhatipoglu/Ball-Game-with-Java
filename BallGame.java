package sample.OKUL.odev2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HareketliTop extends Application {
    static int dx = 1;
    static int dy = 1;

    public static void main(String[] args) {
        Application.launch(args);/*from   w w w . 2 1 do c. net*/
    }

    @Override
    public void start(final Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 433, 312, Color.WHITE); //scene nin boyutunu ve rengini ayarllıyoruz.

        primaryStage.setScene(scene);//start parametresinden gelen stage e scene i ekliyoruz.
        primaryStage.show();

        final Circle ball = new Circle(100, 100, 20); //topu oluşturuyoruz.

        root = (Group) scene.getRoot();
        root.getChildren().add(ball); //root a ball u ekliyoruz.

/*
        //mouse ile topun üstüne basınca GUI kapanır.
        //Sürükleme özelliğini kullanabilmek için bunu yorum satırına aldım çünkü sürüklerken topun üstüne
        //basınca GUI kapanıyor.

        //setOnMousePressed ifadesi mouse ile üstüne basınca olackları gösterir.
        //olaylar ball üzerinde gerçekleştiği için. ball.setOnMousePressed kullandım.
        ball.setOnMousePressed(e ->{
            Platform.exit(); //arayüz kapanır.
            System.exit(0); //sistemden başarılı şekilde çıkış gerçekleşir.
        });*/

/*
        //mouse ile topun üstüne gelince GUI kapanır.
        //Sürükleme özelliğini kullanabilmek için bunu yorum satırına aldım çünkü sürüklerken topun üstüne
        //gelince GUI kapanıyor.
        //setOnMouseEntered ifadesi mouse ile üstüne gelince olacakları gösterir.
        //olaylar ball üzerinde gerçekleştiği için ball.setOnMouseEntered kullandım.

        ball.setOnMouseEntered(e ->{
            Platform.exit(); //arayüz kapanır.
            System.exit(0); //sistemden başarılı şekilde çıkış gerçekleşir.
        });*/

        //mouse ile topun üstüne basınca top yön değiştiriyor.
        //olaylar ball üzerinde gerçekleştiği için. ball.setOnMousePressed kullandım.
        ball.setOnMousePressed(e ->{
            dx = dx*-1;
            dy = dy*-1;
            ball.setTranslateX(ball.getTranslateX() + dx);
            ball.setTranslateX(ball.getTranslateX() + dy);
        });

        /*
        //mouse ile topun üstüne gelince top yön değiştiriyor.
        //olaylar ball üzerinde gerçekleştiği için. ball.setOnMouseEntered kullandım.
        ball.setOnMouseEntered(e ->{
            dx = dx*-1;
            dy = dy*-1;
            ball.setTranslateX(ball.getTranslateX() + dx);
            ball.setTranslateX(ball.getTranslateX() + dy);
        });*/

        //topu mouse ile tutup sürüklememizi sağlar.
        //setOnMouseDragged ifadesi mouse ile ball u(topu) x ve y ekseninde sürüklemeyi sağlar.
        //olaylar scene üzerinde gerçekleştiği için. scene.setOnMouseDragged kullandım.
        scene.setOnMouseDragged(e -> {
            ball.setTranslateX(e.getX()); //topu mouse ile tutup sürükleyince konumunu x ters yöne şeklinde dğiştirir.
            ball.setTranslateY(e.getY()); //topu mouse ile tutup sürükleyince konumunu y ters yöne şeklinde dğiştirir.

        });

        //setOnKeyPressed ifadesi klavye tuşları ile topun hareket etmesini ifade eder.
        //olaylar scene üzerinde gerçekleştiği için. scene.setOnKeyPressed kullandım.
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN: //klavyeden aşağı tuşuna basarsak ball un konumu y ekseninde 20 artar.
                    ball.setTranslateY(ball.getTranslateY() + 20);
                    break;
                case UP: //klavyeden yukarı tuşuna basarsak ball un konumu y ekseninde 20 azalır.
                    ball.setTranslateY(ball.getTranslateY() - 20);
                    break;
                case LEFT: //klavyeden sol tuşuna basarsak ball un konumu x ekseninde 20 azalır.
                    ball.setTranslateX(ball.getTranslateX() - 20);
                    break;
                case RIGHT: //klavyeden sağ tuşuna basarsak ball un konumu x ekseninde 20 artar.
                    ball.setTranslateX(ball.getTranslateX() + 20);
                    break;
                default: //eğer klavyeden hiçbir tuşa basılmazsa bu döngüden çık olanlar olmaya devam etsin.
                    break;
            }
        });

        // Timeline kullanarak gerçek zamanlı animasyon gerçekleştiriyoruz.
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        // Duration.seconds(.0150) ile topun hızını belirliyoruz.
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.0150),
                (ActionEvent event) -> {
                    double xMin = ball.getBoundsInParent().getMinX();
                    double yMin = ball.getBoundsInParent().getMinY();
                    double xMax = ball.getBoundsInParent().getMaxX();
                    double yMax = ball.getBoundsInParent().getMaxY();

                    //Çarpışma ve sınırları belirliyoruz.
                    if (xMin < 0 || xMax > scene.getWidth()) {
                        //eger x ekseninde scene nin dışına çıkma olursa:
                        dx = dx * -1; //eger x eksenindeki duvara çarparsa geri dönsün
                    }
                    if (yMin < 0 || yMax > scene.getHeight()) {
                        //eger x ekseninde scene nin dışına çıkma olursa:
                        dy = dy * -1; //eger x eksenindeki duvara çarparsa geri dönsün
                    }

                    ball.setTranslateX(ball.getTranslateX() + dx);
                    ball.setTranslateY(ball.getTranslateY() + dy);

                });
        //topu Frame e ekliyoruz.
        tl.getKeyFrames().add(moveBall);
        tl.play(); //animasyonu görüyoruz.
    }
    //addBouncyBall(scene);
}

