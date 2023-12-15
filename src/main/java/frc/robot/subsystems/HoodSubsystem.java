// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ControlModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.HoodConstants.*;

public class HoodSubsystem extends SubsystemBase {
  /** Creates a new Hood. */

  // created the motor and MotionMagic
  private TalonFX m_hood;
  private double hoodDeg;//the degrees the hood needs to be
  private final MotionMagicVoltage motionMagic = new MotionMagicVoltage(0);

  // the instance
  public static HoodSubsystem instance;

  public static HoodSubsystem getInstance() {
    if (instance == null) {
      instance = new HoodSubsystem();
    }
    return instance;
  }

  // Constractor
  public HoodSubsystem() {
    this.m_hood = new TalonFX(HoodPort);
    hoodDeg = 0;

    // creat the full MotionMagic
    TalonFXConfiguration configuration = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    mm.MotionMagicCruiseVelocity = mmc;
    mm.MotionMagicAcceleration = mma;
    mm.MotionMagicJerk = mmj;
    configuration.MotionMagic = mm;

    configuration.Slot0.kP = kp;
    configuration.Slot0.kS = ks;
    configuration.Slot0.kV = kv;
    configuration.Slot0.kS = ks;

    configuration.Voltage.PeakForwardVoltage = peekFvol;
    configuration.Voltage.PeakReverseVoltage = peekRvol;

    // set Ratio to 50:1
    configuration.Feedback.SensorToMechanismRatio = TICKS_PER_DEGREE;

    StatusCode statusCode = StatusCode.StatusCodeNotInitialized;

    for (int i = 0; i < 5; i++) {
      statusCode = m_hood.getConfigurator().apply(configuration);
      if (statusCode.isOK())
        break;
    }
    if (!statusCode.isOK())
      System.out.println("Could not apply config to HoodSubsystem, error code:" + statusCode.toString());

    m_hood.setPosition(0);
  }

  //moving function for the Hood
  public void moveHoodTo(double degrees){
    this.hoodDeg = degrees;//Updates the required degrees

    m_hood.setControl(motionMagic.withPosition(degrees));
  }

  //get function for the Hood pose
  public double getHoodPose() {
    return m_hood.getPosition().getValue();
  }
  
  //Checking the degree difference conditions
  public boolean isHoodReady() {
    return Math.abs(getHoodPose() - hoodDeg) <= READY_DEG_TOLERANCE;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
