package entities;

import gifprocessing.GifDecoder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedGIF {
    private Point position;
    private BufferedImage[] frames;
    private int imageWIDTH;
    private int imageHEIGHT;
    private int frameCount = -1;
    private int currentFrame = -1;
    private boolean isReady = false;

    AnimatedGIF(Point position) { this.position = position; }
    public Point getPosition() { return position; }
    public BufferedImage[] getFrames() { return frames; }
    public int getImageWIDTH() { return imageWIDTH; }
    public int getImageHEIGHT() { return imageHEIGHT; }
    public int getFrameCount() { return frameCount; }
    public int getCurrentFrame() { return currentFrame; }
    public boolean isReady() { return isReady; }
    public void setPosition(Point position) { this.position = position; }
    public void setFrames(BufferedImage[] frames) { this.frames = frames; }
    public void setImageWIDTH(int imageWIDTH) { this.imageWIDTH = imageWIDTH; }
    public void setImageHEIGHT(int imageHEIGHT) { this.imageHEIGHT = imageHEIGHT; }
    public void setFrameCount(int frameCount) { this.frameCount = frameCount; }
    public void setCurrentFrame(int currentFrame) { this.currentFrame = currentFrame; }
    public void isReady(boolean isReady) { this.isReady = isReady; }

    public void readGIF(String gifFile) {
        GifDecoder decoder = new GifDecoder();
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

    public void nextFrame() {
        ++currentFrame;
        if(currentFrame == frameCount) { currentFrame = 0; }
    }

    public void draw(Graphics2D g) {
        if(frames != null) {
            g.drawImage(frames[currentFrame],position.x,position.y,null);
        }
    }
}
