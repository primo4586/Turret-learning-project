// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.util.interpolation.InterpolationMap;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class HoodConstants{

    //technical Constants
    public static final int HoodID = 1;
    public static final int encoderCountsPerRevolution = 1024;
    public static final double gearRatio = 50.0;
    public static final double TICKS_PER_DEGREE = encoderCountsPerRevolution * gearRatio / 360.0;

    //condition Costants
    public static final double minimumError = 2.0;

    //motionMagic Constants
    public static final double mmCruise = 5;
    public static final double mmAcceleration = 10;
    public static final double mmJerk = 50;

    public static final double kp = 24;
    public static final double kd = 0.1;
    public static final double ks = 24;
    public static final double ka = 24;
    public static final double kv = 24;

    //MaxVol Constant
    public static final double peekReverseVoltage = -11.5;
    public static final double peekForwardVoltage = 11.5;

    //Constant limit values 
    public static final double forwardLimit = 300;
    public static final double backwordLimit = 300;

    //iterpolation map values
    //To do: give noraml values
    public static final InterpolationMap HOOD_VISION_MAP = new InterpolationMap()
                .put(1, 14100)
                .put(1.2, 13800)
                .put(1.4, 12800)
                .put(1.6, 12800)
                .put(1.8, 12900)
                .put(2, 13200)
                .put(2.1, 13300);

  }
  
}
