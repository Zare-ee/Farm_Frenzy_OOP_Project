package model.good;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Game;

import java.io.File;

public abstract class Good {
    protected int space;
    protected int price;
    protected double lifetime;
    protected double fullLifeTime;

    protected GoodImageView imageView;
    protected GoodAnimation goodAnimation;

    public Good(int space, double lifetime, int price) {
        this.space = space;
        this.lifetime = lifetime;
        this.fullLifeTime = lifetime;
        this.price = price;
    }

    public void setPosition(double x, double y) {
        imageView = new GoodImageView(this);
        imageView.setPickOnBounds(false);
        imageView .setImage(new Image(new File("src/resource/Product/" + getClass().getSimpleName() + ".png").toURI().toString()));
        imageView.setLayoutX(x - imageView.getImage().getWidth() / 2);
        imageView.setLayoutY(y - imageView.getImage().getHeight() / 2);

        Game game = Game.getInstance();
        game.getGoods().add(this);
        game.getGoodPane().getChildren().add(imageView);
        //imageView.toBack();

        //imageView.setOnMouseClicked(mouseEvent -> setOnMouseClicked());

        goodAnimation = new GoodAnimation(this);
        goodAnimation.play();
        goodAnimation.setOnFinished(event -> {
            game.getGoods().remove(this);
            game.getGoodPane().getChildren().remove(imageView);
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getFullLifeTime() {
        return fullLifeTime;
    }

    public int getSpace() {
        return space;
    }

    public int getPrice() {
        return price;
    }

    public double getY() {
        return imageView.getLayoutY() + imageView.getImage().getHeight() / 2;
    }

    public double getX() {
        return imageView.getLayoutX() + imageView.getImage().getWidth() / 2;
    }

    public void update(double v) {
        lifetime = (1 - v) * fullLifeTime;
        if (lifetime < 2 && lifetime > 1.5) {
            imageView.setOpacity(0.5);
        } else if (lifetime < 1.5 && lifetime > 1) {
            imageView.setOpacity(1);
        } else if (lifetime < 1 && lifetime > 0.5) {
            imageView.setOpacity(0.5);
        } else {
            imageView.setOpacity(1);
        }
    }

    public void play() {
        if (goodAnimation.getCurrentRate() == 0.0)
            goodAnimation.play();
    }

    public void pause() {
        if (goodAnimation.getCurrentRate() != 0.0)
            goodAnimation.pause();
    }

    public void update() {
        if (lifetime > 0) {
            lifetime--;
            if (lifetime == 0) {
                Game.getInstance().getGoods().remove(this);
            }
        }
    }

    public void setOnMouseClicked() {
        Game game = Game.getInstance();
        if (game.getWarehouse().addGood(this)) {
            goodAnimation.pause();
            game.getGoodPane().getChildren().remove(imageView);
            game.getGoods().remove(this);
            game.updateTask(this.getClass().getSimpleName(), true);
        } else {
            game.setResult("No Capacity!");
        }
    }
}
