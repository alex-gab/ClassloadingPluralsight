package com.alex;

public class CanonCameraFactory implements ICameraFactory {
    @Override
    public ICamera createCamera() {
        return new CanonCamera();
    }
}
