// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.interpolation.InterpolateUtil;

import static frc.robot.Constants.HoodConstants.*;

public class HoodSubsystem extends SubsystemBase {
  /** Creates a new Hood. */

  // created the motor and MotionMagic
  private TalonFX m_hoodMotor;
  private final MotionMagicVoltage motionMagic = new MotionMagicVoltage(0);

  // the instance
  private static HoodSubsystem instance;

  public static HoodSubsystem getInstance() {
    if (instance == null) {
      instance = new HoodSubsystem();
    }
    return instance;
  }

  // Constractor
  private HoodSubsystem() {
    this.m_hoodMotor = new TalonFX(HoodID);

    // creat the full MotionMagic
    TalonFXConfiguration configuration = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    mm.MotionMagicCruiseVelocity = mmCruise;
    mm.MotionMagicAcceleration = mmAcceleration;
    mm.MotionMagicJerk = mmJerk;
    configuration.MotionMagic = mm;

    configuration.Slot0.kP = kp;
    configuration.Slot0.kD = kd;
    configuration.Slot0.kS = ks;
    configuration.Slot0.kV = kv;
    configuration.Slot0.kS = ks;

    configuration.Voltage.PeakForwardVoltage = peekForwardVoltage;
    configuration.Voltage.PeakReverseVoltage = peekReverseVoltage;

    //forward and backword limits
    configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = forwardLimit;
    configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = backwordLimit;


    // set Ratio to 50:1
    configuration.Feedback.SensorToMechanismRatio = TICKS_PER_DEGREE;

    StatusCode statusCode = StatusCode.StatusCodeNotInitialized;

    for (int i = 0; i < 5; i++) {
      statusCode = m_hoodMotor.getConfigurator().apply(configuration);
      if (statusCode.isOK())
        break;
    }
    if (!statusCode.isOK())
      System.out.println("Could not apply config to HoodSubsystem, error code:" + statusCode.toString());

    m_hoodMotor.setPosition(0);
  }

  //moving function for the Hood
  public void moveHoodTo(double degrees){
    m_hoodMotor.setControl(motionMagic.withPosition(degrees));
  }

  //get function for the Hood pose
  public double getHoodPose() {
    return m_hoodMotor.getPosition().getValue();
  }
  
  //Checking the degree difference conditions
  public boolean isHoodReady() {
    return (Math.abs(m_hoodMotor.getClosedLoopError().getValue()) < minimumError);
  }

  //Gets the HoodPose from distance through HOOD_VISION_MAP
  public double PoseFromDis(double distance){
    return InterpolateUtil.interpolate(HOOD_VISION_MAP, distance);
  }
  public void HoodPoseReset(){
    m_hoodMotor.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
