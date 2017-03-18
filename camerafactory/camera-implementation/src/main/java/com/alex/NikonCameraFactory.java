package com.alex;

/**
 * Created by kevinj.
 */
public class NikonCameraFactory implements ICameraFactory {
    @Override
    public ICamera createCamera() {
        return new NikonCamera();
    }
}

