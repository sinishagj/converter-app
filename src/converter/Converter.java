package converter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Converter {


    public static void convertImage(String path) throws RuntimeException {
        BufferedImage bufferedImage;
        try {
            //read image file
            bufferedImage = ImageIO.read(new File(path));

            // create a blank, RGB, same width and height, and a white background
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

            // write to png file
            String outputPath = path.replace("jpeg", "png");

                ImageIO.write(newBufferedImage, "png", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");


    }


}
