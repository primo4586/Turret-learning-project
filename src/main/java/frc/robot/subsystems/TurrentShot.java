// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants.TurrentShotConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurrentShot extends SubsystemBase {

  // Creating the motors
  private TalonFX shotMotor;
  private TalonFX enterBallMotor;

  // Position voltage
  private final PositionVoltage m_voltagePosition = new PositionVoltage(0);
  // Motion magic
  private final MotionMagicVoltage motionMagic = new MotionMagicVoltage(0);

  /** Creates a new TurrentShot. */
  private TurrentShot() {

    // giving values to the motors
    this.shotMotor = new TalonFX(TurrentShotConstants.KshotMotorID);
    this.enterBallMotor = new TalonFX(TurrentShotConstants.KenterBallMotorID);

    //declaring Configs
    TalonFXConfiguration configs = new TalonFXConfiguration();
    MotionMagicConfigs mm = new MotionMagicConfigs();

    //giving motion magic values
    mm.MotionMagicCruiseVelocity = 5;
    mm.MotionMagicAcceleration = 10;
    mm.MotionMagicJerk = 50;
    configs.MotionMagic = mm;

    //giving PID values
    configs.Slot0.kP = 24;
    configs.Slot0.kD = 0.1;
    configs.Slot0.kV = 0.12;
    configs.Slot0.kS = 0.25;

    //max voltage for shotMotor
    configs.Voltage.PeakForwardVoltage = 11.5;
    configs.Voltage.PeakReverseVoltage = -11.5;

    configs.Feedback.SensorToMechanismRatio = 50;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
