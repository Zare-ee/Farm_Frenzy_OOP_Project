package model.animal.wild;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.io.File;

public class Lion extends Wild {

    public Lion() {
        super(3, 300, 1.0);
    }

    @Override
    public void setAnimation() {
        Image[] images = new Image[8];
        images[0] = new Image(new File("src/resource/Lion/right.png").toURI().toString());
        images[1] = new Image(new File("src/resource/Lion/down_right.png").toURI().toString());
        images[2] = new Image(new File("src/resource/Lion/down.png").toURI().toString());
        images[3] = new Image(new File("src/resource/Lion/down_left.png").toURI().toString());
        images[4] = new Image(new File("src/resource/Lion/left.png").toURI().toString());
        images[5] = new Image(new File("src/resource/Lion/up_left.png").toURI().toString());
        images[6] = new Image(new File("src/resource/Lion/up.png").toURI().toString());
        images[7] = new Image(new File("src/resource/Lion/up_right.png").toURI().toString());

        int[] numberOfRows = {8, 6, 5, 6, 8, 4, 4, 4};
        int[] numberOfColumns = {3, 4, 5, 4, 3, 6, 6, 6};
        boolean[] leftStart = {false, false, true, true, true, true, true, false};

        Rectangle2D[][] cells = new Rectangle2D[8][24];
        for (int i = 0; i < 8; i++) {
            cells[i] = new Rectangle2D[24];
            double cellWidth = images[i].getWidth() / numberOfColumns[i];
            double cellHeight = images[i].getHeight() / numberOfRows[i];

            for (int j = 0; j < numberOfRows[i]; j++) {
                for (int k = 0; k < Math.min(numberOfColumns[i],24 - j * numberOfColumns[i]); k++) {
                    cells[i][j * numberOfColumns[i] + k] = new Rectangle2D((leftStart[i] ? k : numberOfColumns[i] - 1 - k) * cellWidth, j * cellHeight, cellWidth, cellHeight);
                }
            }
        }

        Image cagedWildImage = new Image(new File("src/resource/" + getClass().getSimpleName() + "/caged.png").toURI().toString());
        int numberOfCageRows = 4;
        int numberOfCageColumns = 6;

        Rectangle2D[] cagedWildCells = new Rectangle2D[24];
        double cellWidth = cagedWildImage.getWidth() / numberOfCageColumns;
        double cellHeight = cagedWildImage.getHeight() / numberOfCageRows;

        for (int j = 0; j < numberOfCageRows; j++) {
            for (int k = 0; k < numberOfCageColumns; k++) {
                cagedWildCells[j * numberOfCageColumns + k] = new Rectangle2D(k * cellWidth, j * cellHeight, cellWidth, cellHeight);
            }
        }

        super.setAnimation(images, cells, cagedWildImage, cagedWildCells);
    }
}
