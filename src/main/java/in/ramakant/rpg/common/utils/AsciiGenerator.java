package in.ramakant.rpg.common.utils;

import in.ramakant.rpg.persistence.SerializationRealmConfigurationGenerator;
import in.ramakant.rpg.persistence.dto.EnemyConfiguration;
import in.ramakant.rpg.persistence.dto.MedicConfiguration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static in.ramakant.rpg.common.utils.AsciiArtLoader.ASCII_ART_FOLDER;
import static in.ramakant.rpg.common.utils.ExternalIO.resourcesPath;

public class AsciiGenerator {
    private static final int FONT_SIZE = 20;
    private static final int FONT_TYPE = Font.BOLD;
    private static final String FONT_NAME = "Dialog";
    private static final String FILE_FORMAT = "png";

    public static String generateFromString(String input) throws IOException {
        File tempFile = new File(input + "." + FILE_FORMAT);
        BufferedImage image = buildPngImage(input, tempFile);
        String asciiArtFromImage = buildAsciiArtFromImage(input, image);
        tempFile.delete();

        return asciiArtFromImage;
    }

    private static BufferedImage buildPngImage(String input, File tempFile) throws IOException {
        BufferedImage image = new BufferedImage(width(input), FONT_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font(FONT_NAME, FONT_TYPE, FONT_SIZE));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(input.toUpperCase(), 0, FONT_SIZE);
        ImageIO.write(image, FILE_FORMAT, tempFile);
        return image;
    }

    private static String buildAsciiArtFromImage(String input, BufferedImage image) {
        StringBuilder theWholeThing = new StringBuilder();

        for (int y = 0; y < FONT_SIZE; y++) {
            StringBuilder oneLine = new StringBuilder();

            for (int x = 0; x < width(input); x++) {
                oneLine.append(getOneSign(image, y, x));
            }
            if (oneLine.toString().trim().isEmpty()) {
                continue;
            }
            theWholeThing.append(oneLine).append("\n");
        }

        return theWholeThing.toString();
    }

    private static String getOneSign(BufferedImage image, int y, int x) {
        return image.getRGB(x, y) == -16777216 ? " " : image.getRGB(x, y) == -1 ? "#" : "*";
    }

    private static int width(String input) {
        return input.length() * (FONT_SIZE - 6) + 3;
    }

    public static void main(String[] args) {
        SerializationRealmConfigurationGenerator.realms()
                .forEach(realm -> realm.getEnemies()
                        .forEach(AsciiGenerator::generateAsciiArtAndSaveToFile));

        SerializationRealmConfigurationGenerator.realms()
                .forEach(realm -> realm.getMedics()
                        .forEach(AsciiGenerator::generateAsciiArtAndSaveToFile));
    }

    private static void generateAsciiArtAndSaveToFile(EnemyConfiguration enemy) {
        try {
            String asciiArt = generateFromString(enemy.getName());
            ExternalIO.stringToFile(asciiArt, resourcesPath() + ASCII_ART_FOLDER, enemy.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateAsciiArtAndSaveToFile(MedicConfiguration medic) {
        try {
            String asciiArt = generateFromString(medic.getName());
            ExternalIO.stringToFile(asciiArt, resourcesPath() + ASCII_ART_FOLDER, medic.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
