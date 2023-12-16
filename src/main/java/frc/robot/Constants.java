// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of

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
  //constants for turrentShot subsystem
  public static class ShooterConstants{
    public static final int KShooterMotorID = 20;
    public static final int KFeederMotorID = 20;

    public static final int MotionMagicJerk = 50;
    public static final int MotionMagicCruiseVelocity = 5;
    public static final int MotionMagicAcceleration = 10;

    public static final double PeakForwardVoltage = 11.5;
    public static final double PeakReverseVoltage = -11.5;

    public static final int SensorToMechanismRatio  = 50;

    public static final int kP  = 24;
    public static final double kD = 0.1;
    public static final double kV = 0.12;
    public static final double kS = 0.25;
    
    public static final int ShooterSpeed = 12700;
    public static final int FeederSpeed = 2000;
    public static final int LimitSpeed = 13;
    public static final double TimeLimit = 3;

    public static final InterpolationMap ShooterInterpolation = new InterpolationMap()
                .put(1, 9)
                .put(1.2, 9.2)
                .put(1.4, 9.4)
                .put(1.6, 9.6)
                .put(1.8, 9.8)
                .put(2, 10)
                .put(2.1, 10.2);

  }
}
