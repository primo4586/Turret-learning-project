// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static final int HoodPort = 1;
    public static final int encoderCountsPerRevolution = 1024;
    public static final double gearRatio = 50.0;
    public static final double TICKS_PER_DEGREE = encoderCountsPerRevolution * gearRatio / 360.0;

    //condition Costants
    public static final double READY_DEG_TOLERANCE = 2.0;

    //motionMagic Constants
    public static final double mmc = 5;
    public static final double mma = 10;
    public static final double mmj = 50;

    public static final double kp = 24;
    public static final double ks = 24;
    public static final double ka = 24;
    public static final double kv = 24;
  }

  public static class MaxVolConstants{
    public static final double peekRvol = -11.5;
    public static final double peekFvol = 11.5;

  }
}
