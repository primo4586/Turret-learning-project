// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants;
import frc.robot.Constants.TurrentShotConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurrentShot extends SubsystemBase {

  //Creating the motors
  private TalonFX shotMotor;
  private TalonFX enterBallMotor;

  /** Creates a new TurrentShot. */
  private TurrentShot() {
    this.shotMotor = new TalonFX(Constants.TurrentShotConstants.KshotMotorID);
    this.enterBallMotor = new TalonFX(Constants.TurrentShotConstants.KenterBallMotorID);

    TalonFXConfiguration configs = new TalonFXConfiguration();



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
