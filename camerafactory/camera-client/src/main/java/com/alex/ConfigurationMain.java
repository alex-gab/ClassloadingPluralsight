package com.alex;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by kevinj.
 */
public class ConfigurationMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Configuration configuration = Configuration.loadConfiguration(args[0]);
        String location = configuration.getLocation();
        URL url = new URL(location);
        URLClassLoader ucl = new URLClassLoader(new URL[]{url});
        Class<ICameraFactory> cls = (Class<ICameraFactory>) Class.forName(configuration.getFactoryType(), true, ucl);
        ICameraFactory cameraFactory = cls.newInstance();
        ICamera camera = cameraFactory.createCamera();

        camera.takePhoto();
    }
}

