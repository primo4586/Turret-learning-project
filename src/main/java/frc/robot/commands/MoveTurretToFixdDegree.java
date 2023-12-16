// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSubsystem;

public class MoveTurretToFixdDegree extends Command {
  private final TurretSubsystem turret = TurretSubsystem.getInstance();
  private double degree;

  public MoveTurretToFixdDegree(double degree) {
    this.degree = degree;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turret.putTurretInPose(degree);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.manualTurnForTurret(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (turret.checkTurretPosition()){
      return true;
    }
    return false;
  }
}