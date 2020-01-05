package entities;

import imageprocessing.GifDecoder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedGIF {

    private Point point;
    private BufferedImage[] frames;
    private int frameCount = -1;
    private int currentFrame = -1;
    private GifDecoder decoder;
    public int imageWIDTH;
    public int imageHEIGHT;
    private boolean isReady = false;

    public AnimatedGIF(int x,int y) { point = new Point(x,y); }

    public void setPoint(int x,int y) {
        point.x = x;
        point.y = y;
    }

    public void readGIF(String gifFile) {
        decoder = new GifDecoder();
        decoder.read(getClass().getResourceAsStream(gifFile));
        frameCount = decoder.getFrameCount();
        currentFrame = 0;
        frames = new BufferedImage[frameCount];
        for(int i = 0; i < frameCount; ++i) {
            frames[i] = decoder.getFrame(i);
        }
        imageWIDTH = frames[0].getWidth();
        imageHEIGHT = frames[0].getHeight();
        isReady = true;
    }

    public void drawFrame(Graphics2D g) {
        if(isReady) {
            g.drawImage(frames[currentFrame],point.x,point.y,null);
        }
    }

    public void nextFrame() {
        ++currentFrame;
        if(currentFrame == frameCount) {
            currentFrame = 0;
        }
    }
}
