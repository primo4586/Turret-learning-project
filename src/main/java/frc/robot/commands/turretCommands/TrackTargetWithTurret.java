// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//TODO: add the butten with 
package frc.robot.commands.turretCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.Vision;

public class TrackTargetWithTurret extends Command {
  private final TurretSubsystem turret = TurretSubsystem.getInstance();

  public TrackTargetWithTurret() {
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    turret.putTurretInAngle(Vision.GetAngleFromTarget().getDegrees());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
