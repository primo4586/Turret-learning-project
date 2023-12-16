// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of

package frc.robot;

import frc.robot.util.interpolation.InterpolationMap;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;
import frc.robot.util.Conversions;

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
    
    public static final int ShooterSpeed = 23023;
    public static final int FeederSpeed = 2000;
    public static final int ShooterIdleSpeed = 2342;
    public static final int MaxError = 13;
    public static final double MaxTime = 3;

    public static final InterpolationMap ShooterInterpolation = new InterpolationMap()
                .put(1, 9)
                .put(1.2, 9.2)
                .put(1.4, 9.4)
                .put(1.6, 9.6)
                .put(1.8, 9.8)
                .put(2, 10)
                .put(2.1, 10.2);

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
  
  public static class VisionConstants{
    public static final String FRONT_CAMERA_NAME = "FRONT CAMERA NAME";
    public static final AprilTagFieldLayout kTagLayout = AprilTagFields.kDefaultField.loadAprilTagLayoutField();
    public static final Transform3d kRobotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0, 0, 0));
    public static final Matrix<N3, N1> kSingleTagStdDevs = VecBuilder.fill(4, 4, 8);
    public static final Matrix<N3, N1> kMultiTagStdDevs = VecBuilder.fill(0.5, 0.5, 1);
    public static final Pose2d target = new Pose2d(1, 1, new Rotation2d(Units.degreesToRadians(0)));
  }
}
