// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//TODO: add the butten with 
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSubsystem;

public class TrackTargetWithTurret extends Command {
  private final TurretSubsystem turret = TurretSubsystem.getInstance();
  private double degree;

  public TrackTargetWithTurret() {
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.degree = 0; // TODO: add the visoin subsystem
    turret.putTurretInPose(degree);
  }

  @Override
  public void end(boolean interrupted) {
    turret.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
